package com.example.tilsocial.signup.view;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tilsocial.DashboardActivity;
import com.example.tilsocial.R;
import com.example.tilsocial.signup.model.SignUpModel;
import com.example.tilsocial.signup.model.SignupRequestParams;
import com.example.tilsocial.signup.model.SpinnerDetails;
import com.example.tilsocial.signup.presenter.MainContractSignup;
import com.example.tilsocial.signup.presenter.SignupPresentor;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class SignUpFragment extends Fragment implements MainContractSignup.MainView {


    Spinner department,team,designation;
    View view;
    EditText employeeidd,namee,bioo;
    Button signuppbtn,upload;
    ChipGroup chipGroup;
    Chip chip;
    ImageView add,userprofile;
    Uri imageUri;
    Uri selectedImage;
    String simage;
    List<String> imageList = new ArrayList<>();
    List<String> interestList = new ArrayList<>();
    ArrayList<String> genres = new ArrayList<>();
    SpinnerDetails spinnerDetails;
    MainContractSignup.presenter presenter;

    FirebaseStorage storage;
    StorageReference storageReference;



    public SignUpFragment()
    {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SignupPresentor(this,new SignUpModel());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_siggnuppfragment, container, false);
        department = view.findViewById(R.id.spinner4);
        team = view.findViewById(R.id.spinner5);
        designation = view.findViewById(R.id.spinner6);
        employeeidd = view.findViewById(R.id.editTextTextPersonName2);
        namee = view.findViewById(R.id.editTextTextPersonName);
        signuppbtn = view.findViewById(R.id.buttonsignup);
        bioo = view.findViewById(R.id.editTextTextPersonName3);
        add = view.findViewById(R.id.add);
        chipGroup = view.findViewById(R.id.chip_group);
        userprofile = view.findViewById(R.id.userprofilee);
        spinnerDetails =new SpinnerDetails();
        upload=view.findViewById(R.id.button3);
//        presenter.requestDataFromServerSpinner();
        presenter.departmentSpinnerdetail();
        presenter.TeamSpinnerDetail();
        presenter.DesignationSpinnerDetail();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        //Interest in chips
        genres.add("Mobile Application Development");
        genres.add("Android");
        genres.add("iOS");
        genres.add("System Design");
        for(int i = 0 ; i<genres.size(); i++) {

            chip = new Chip(getActivity());
            chip.setText(genres.get(i));
            chip.setChipBackgroundColor(getResources().getColorStateList(R.color.color_state_chip_outline));
            chip.setCheckable(true);
            chipGroup.addView(chip);
        }

        Integer c = chipGroup.getChildCount();
        for (int j = 0; j < c; j++) {
            Chip chip = (Chip) chipGroup.getChildAt(j);
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (chip.isChecked()) {
                        interestList.add(chip.getText().toString());
                    } else {
                        interestList.remove(chip.getText());
                    }
                    Toast.makeText(getActivity(), "-" + interestList, Toast.LENGTH_LONG).show();
                }
            });
        }

        userprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();
            }
        });

        signuppbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int empid = Integer.parseInt(employeeidd.getText().toString().isEmpty() ? "0":employeeidd.getText().toString());
                SignupRequestParams signupRequestParams = new SignupRequestParams();
                signupRequestParams.setEmpId(empid);
                signupRequestParams.setName(namee.getText().toString());
                signupRequestParams.setBio(bioo.getText().toString());
                signupRequestParams.setDept(department.getSelectedItem().toString());
                signupRequestParams.setTeam(team.getSelectedItem().toString());
                signupRequestParams.setDesignation(designation.getSelectedItem().toString());
                signupRequestParams.setInterests((ArrayList) interestList);
                presenter.dosignup(signupRequestParams);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
        return view;
    }

    private void selectImage() {

        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 0);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }

    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:

                if(resultCode == RESULT_OK){
                    Bitmap photo = (Bitmap) imageReturnedIntent.getExtras().get("data");
                    userprofile.setImageBitmap(photo);
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                    String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), photo, "Title", null);
                    selectedImage=Uri.parse(path);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    selectedImage = imageReturnedIntent.getData();
                    userprofile.setImageURI(selectedImage);
                }
                break;
        }
    }

    public void uploadImage() {
        if (selectedImage != null) {

            StorageReference ref = storageReference.child("UserProfile/"+UUID.randomUUID().toString());

            ref.putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                {
                    Toast.makeText(getActivity(), "Image Uploaded!!", Toast.LENGTH_SHORT).show();
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            Toast.makeText(getActivity(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("TAG", "onFailure: "+e.getMessage());
                        }
                    });
        }
    }



    @Override
    public void shownamevalidation() {
        namee.requestFocus();
        namee.setError("FIELD CANNOT BE EMPTY");
    }

    @Override
    public void showgetemployeevalidation() {
        employeeidd.requestFocus();
        employeeidd.setError("FIELD CANNOT BE EMPTY");

    }

    @Override
    public void showbiovalidation() {
        bioo.requestFocus();
        bioo.setError("FIELD CANNOT BE EMPTY");

    }

    @Override
    public void showdepartmentvalidation() {
        Toast.makeText(getActivity(), "Department Required", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showteamvalidation() {
        Toast.makeText(getActivity(), "Team Required", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void designationvalidation() {
        Toast.makeText(getActivity(), "Designation Required", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showinterestvalidation() {
        Toast.makeText(getActivity(), "Please select minimum 1 Interest", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void nextfragment() {
        Intent intent = new Intent(getActivity(), DashboardActivity.class);
        startActivity(intent);
    }

    @Override
    public void nextfragmentprofile() {

    }

    @Override
    public void departmentSpinner(List<String> departmentList) {
//        departmentList.add(0, "Select department...");
        final ArrayAdapter<String> DepartmentArrayAdapter = new ArrayAdapter<String>(
                getActivity(),R.layout.spinnneritem, departmentList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        DepartmentArrayAdapter.setDropDownViewResource(R.layout.spinnneritem);
        department.setAdapter(DepartmentArrayAdapter);
    }

    @Override
    public void teamSpinner(List<String> TeamList) {
//        TeamList.add(0, "Select Team...");
        final ArrayAdapter<String> TeamArrayAdapter = new ArrayAdapter<String>(
                getActivity(),R.layout.spinnneritem, TeamList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        TeamArrayAdapter.setDropDownViewResource(R.layout.spinnneritem);
        team.setAdapter(TeamArrayAdapter);

    }

    @Override
    public void designationSpinner(List<String> DesignationList) {
        final ArrayAdapter<String> DesignationArrayAdapter = new ArrayAdapter<String>(
                getActivity(),R.layout.spinnneritem, DesignationList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        DesignationArrayAdapter.setDropDownViewResource(R.layout.spinnneritem);
        designation.setAdapter(DesignationArrayAdapter);
    }

    @Override
    public void onResponseFailure(Throwable t) {
        Toast.makeText(getActivity(),
                "Something went wrong...Error message: " + t.getMessage(),
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void SetSignupdata(SignupRequestParams signupRequestParams) {

        Log.e("Signupp","data"+signupRequestParams);

    }










}






