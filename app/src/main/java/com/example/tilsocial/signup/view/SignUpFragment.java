package com.example.tilsocial.signup.view;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tilsocial.DashboardActivity;
import com.example.tilsocial.R;
import com.example.tilsocial.signup.model.Departments;
import com.example.tilsocial.signup.model.SignUpModel;
import com.example.tilsocial.signup.model.SignupRequestParams;
import com.example.tilsocial.signup.model.SpinnerDetails;
import com.example.tilsocial.signup.presenter.MainContractSignup;
import com.example.tilsocial.signup.presenter.SignupPresentor;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class SignUpFragment extends Fragment implements MainContractSignup.MainView {


    Spinner department, team, designation;
    View view;
    EditText employeeidd, namee, bioo;
    Button signuppbtn;
    ChipGroup chipGroup;
    Chip chip;
    ImageView add, userprofile;
    Uri imageUri;
    Uri selectedImage;
    String imageurl;
    List<String> imageList = new ArrayList<>();
    List<String> interestList = new ArrayList<>();
    ArrayList<String> genres = new ArrayList<>();
    SpinnerDetails spinnerDetails;
    MainContractSignup.presenter presenter;

    FirebaseStorage storage;
    StorageReference storageReference;
    SharedPreferences sharedPreferences, preferences;
    SharedPreferences.Editor editor, editr;


    public SignUpFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SignupPresentor(this, new SignUpModel());

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
        spinnerDetails = new SpinnerDetails();
        presenter.requestDataFromServerSpinner();

        Bundle bundle=getArguments();
        employeeidd.setText(bundle.getString("emp"));
        employeeidd.setEnabled(false);
        employeeidd.setTextColor(Color.DKGRAY);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        userprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();
            }
        });

        signuppbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getActivity(), "clicked ", Toast.LENGTH_SHORT).show();
                int empid = Integer.parseInt(employeeidd.getText().toString().isEmpty() ? "0" : employeeidd.getText().toString());
                SignupRequestParams signupRequestParams = new SignupRequestParams();
               // Log.e("checking data3242", "onResponse: " +namee.getText().toString());
                signupRequestParams.setEmpId(empid);
                signupRequestParams.setName(namee.getText().toString());
                signupRequestParams.setBio(bioo.getText().toString());
                signupRequestParams.setDept(department.getSelectedItem().toString());
                signupRequestParams.setTeam(team.getSelectedItem().toString());
                signupRequestParams.setDesignation(designation.getSelectedItem().toString());
                signupRequestParams.setInterests((ArrayList) interestList);
                signupRequestParams.setImgUrl(imageurl);
                Log.e("checking data3242", "onResponse: " + signupRequestParams.toString());
                presenter.dosignup(signupRequestParams);
            }
        });

        return view;
    }


    @Override
    public void gettagsdata(List<String> tagss) {
        preferences = getActivity().getSharedPreferences("tags", 0);
        editr = preferences.edit();
        HashSet<String> set = new HashSet(tagss);
        editr.putStringSet("interests", set);
        editr.commit();
        for (int i = 0; i < tagss.size(); i++) {
            chip = new Chip(getActivity());
            chip.setText(tagss.get(i));
            chip.setChipBackgroundColor(getResources().getColorStateList(R.color.color_state_chip_outline));
            chip.setCheckable(true);
            chip.setChipStrokeColor(getResources().getColorStateList(R.color.grey_500));
            chip.setChipStrokeWidth(1);
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
//                    Toast.makeText(getActivity(), "-" + interestList, Toast.LENGTH_LONG).show();
                }
            });
        }

    }



    private void selectImage() {

        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 0);
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }

    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case 0:

                if (resultCode == RESULT_OK) {
                    Bitmap photo = (Bitmap) imageReturnedIntent.getExtras().get("data");
                    userprofile.setImageBitmap(photo);
                    try {
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
                        File mFileTemp = null;
                        mFileTemp = File.createTempFile("ab" + timeStamp, ".jpg", getActivity().getCacheDir());
                        FileOutputStream fout;
                        fout = new FileOutputStream(mFileTemp);
                        photo.compress(Bitmap.CompressFormat.PNG, 70, fout);
                        fout.flush();
                        imageUri = Uri.fromFile(mFileTemp);
                        presenter.uploadFb(getActivity(), imageUri);
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "" + e, Toast.LENGTH_LONG).show();
                    }
                }

                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    selectedImage = imageReturnedIntent.getData();
                    userprofile.setImageURI(selectedImage);
                    presenter.uploadFb(getActivity(), selectedImage);
                }
                break;
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
    public void onResponseFailure(Throwable t) {
        Toast.makeText(getActivity(),
                "Something went wrong...Error message: " + t.getMessage(),
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void SetSignupdata(SignupRequestParams signupRequestParams) {

        Log.e("Signuppdataa", "data" + signupRequestParams);
        sharedPreferences = getActivity().getSharedPreferences("details", 0);
        editor = sharedPreferences.edit();
        editor.putString("empid", signupRequestParams.getEmpId().toString());
        editor.putString("name", signupRequestParams.getName());
        editor.putString("dept", signupRequestParams.getDept());
        editor.putString("bio", signupRequestParams.getBio());
        editor.putString("desig", signupRequestParams.getDesignation());
        HashSet<String> set = new HashSet(signupRequestParams.getInterests());
        editor.putStringSet("inter", set);
        editor.putString("team", signupRequestParams.getTeam());
        editor.putString("imgurl", signupRequestParams.getImgUrl());
        Log.d("DPcheck ", "SetSignupdata: "+signupRequestParams.getImgUrl());
        editor.commit();

    }

    @Override
    public void extractFb(String s) {
        Log.e("Signupimagee", "onResponse: " + s);
        imageurl = s;
    }

    @Override
    public void getspinnerdata(HashMap<String, List<Departments>> map) {
        List<String> TeamList = new ArrayList<>();
        HashMap<String,List<String>> map2 = new HashMap<>();
        //Log.e("Signuppresentor", "onResponse: " + map.size());
        for (Map.Entry<String, List<Departments>> set :
                map.entrySet()) {
            TeamList.add(set.getKey());
        }


        //Log.e("Signuppresentor", "onResponse: " + TeamList.toString());

            final ArrayAdapter<String> TeamArrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinnneritem, TeamList);
        Log.d("TeamListtt", "getspinnerdata: "+TeamList);
            TeamArrayAdapter.setDropDownViewResource(R.layout.spinnneritem);
            team.setAdapter(TeamArrayAdapter);
            team.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    List<Departments> DepartmentList = new ArrayList();
                   // Log.e("Signuppresentorparent", "onResponse: " + parent.getItemAtPosition(position));

                        DepartmentList = map.get(parent.getItemAtPosition(position));
                        List<String> departmentlist = new ArrayList<>();
                        for (int i = 0; i < DepartmentList.size(); i++) {
                            departmentlist.add(DepartmentList.get(i).getName());
                            Log.e("Signuppresentorparentt", "onResponse: " + DepartmentList.get(i).getDesignationslist().toString());
                            map2.put(DepartmentList.get(i).getName(),DepartmentList.get(i).getDesignationslist());
                        }
                        //Log.e("Signuppresentormap2size", "onResponse: " + map2.size());

                        final ArrayAdapter<String> DepartmentArrayAdapter = new ArrayAdapter<String>(
                                getActivity(), R.layout.spinnneritem, departmentlist);
                        DepartmentArrayAdapter.setDropDownViewResource(R.layout.spinnneritem);
                        department.setAdapter(DepartmentArrayAdapter);

                        department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                List<String> designationlist = new ArrayList<>();
                                designationlist.addAll(map2.get(parent.getItemAtPosition(position)));


                                   final ArrayAdapter<String> DesignationArrayAdapter = new ArrayAdapter<String>(
                                           getActivity(), R.layout.spinnneritem, designationlist);
                                   DesignationArrayAdapter.setDropDownViewResource(R.layout.spinnneritem);
                                   designation.setAdapter(DesignationArrayAdapter);

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }


                @Override
                public void onNothingSelected(AdapterView<?> parent) {


                }
            });
        }


}










