package com.example.tilsocial.signup.view;

import static android.app.Activity.RESULT_OK;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
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
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.tilsocial.FeedDetail.view.HomeFragment;
import com.example.tilsocial.R;
import com.example.tilsocial.UserProfile;
import com.example.tilsocial.signup.model.SignUpModel;
import com.example.tilsocial.signup.model.SignupRequestParams;
import com.example.tilsocial.signup.model.SpinnerDetails;
import com.example.tilsocial.signup.presenter.MainContractSignup;
import com.example.tilsocial.signup.presenter.SignupPresentor;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class EditProfile extends Fragment implements MainContractSignup.MainView{
    ActionBar actionBar;
    Spinner department,team,designation;
    EditText namee,bioo;
    Button updatebtn;
    ChipGroup chipGroup;
    Chip chip;
    ArrayList<String> genres = new ArrayList<>();
    SpinnerDetails spinnerDetails;
    MainContractSignup.presenter presenter;
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferencessignup;
    ArrayList interestList= new ArrayList();
    String dept, desig, empid, teamm, imageurl;;
    ImageView userprofile;
    Uri imageUri, selectedImage;


    public EditProfile() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SignupPresentor(this,new SignUpModel());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_edit_profile, container, false);

        department = view.findViewById(R.id.spinner4);
        team = view.findViewById(R.id.spinner5);
        designation = view.findViewById(R.id.spinner6);
        namee = view.findViewById(R.id.editTextTextPersonName);
        updatebtn = view.findViewById(R.id.buttonupdate);
        bioo = view.findViewById(R.id.editTextTextPersonName3);
        chipGroup = view.findViewById(R.id.chip_group);
        userprofile=view.findViewById(R.id.user);
        spinnerDetails =new SpinnerDetails();
        presenter.requestDataFromServerSpinner();
//        presenter.departmentSpinnerdetail();
////        presenter.TeamSpinnerDetail();
        presenter.DesignationSpinnerDetail();

//        empid = getArguments().getString("key");
//        Log.d("EditProfId", "onCreateView: "+empid);

        sharedPreferences= getActivity().getSharedPreferences("details",0);

            namee.setText(sharedPreferences.getString("name",""));
            dept =sharedPreferences.getString("dept","");
            bioo.setText(sharedPreferences.getString("bio",""));
            desig= sharedPreferences.getString("desig","");
            empid =sharedPreferences.getString("empid", "");
            teamm=sharedPreferences.getString("team","");

            Glide.with(getActivity()).load(sharedPreferences.getString("imgurl",""))
                .error(R.drawable.ic_error_outline)
                .into(userprofile);

            HashSet set = (HashSet<String>) sharedPreferences.getStringSet("inter", null);
            ArrayList tags = new ArrayList(set);
            Log.e("editprofilee", "onResponse133: " + tags.toString());



        //Interests
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

//        for(int k=0;k<tags.size();k++){
//            if(genres.contains(tags.get(k))) {
//                chip.setChecked(true);
//            }
//        }


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
                    Toast.makeText(getActivity(), "" + interestList, Toast.LENGTH_LONG).show();
                }
            });
        }

        userprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();
            }
        });

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int empidd = Integer.parseInt(empid.isEmpty() ? "0":empid);
                SignupRequestParams signupRequestParams = new SignupRequestParams();
                signupRequestParams.setName(namee.getText().toString());
                signupRequestParams.setEmpId(empidd);
                signupRequestParams.setBio(bioo.getText().toString());
                signupRequestParams.setDept(department.getSelectedItem().toString());
                signupRequestParams.setTeam(team.getSelectedItem().toString());
                signupRequestParams.setDesignation(designation.getSelectedItem().toString());
                signupRequestParams.setInterests(genres);
                signupRequestParams.setImgUrl(imageurl);
                presenter.gotoprofile(signupRequestParams);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                UserProfile userProfile=new UserProfile();
                ft.replace(R.id.dashboard, userProfile);
                ft.addToBackStack(null);
                ft.commit();
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
                    try {
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
                        File mFileTemp = null;
                        mFileTemp = File.createTempFile("ab" + timeStamp, ".jpg", getActivity().getCacheDir());
                        FileOutputStream fout;
                        fout = new FileOutputStream(mFileTemp);
                        photo.compress(Bitmap.CompressFormat.PNG, 70, fout);
                        fout.flush();
                        imageUri = Uri.fromFile(mFileTemp);
                        presenter.uploadFb(getActivity(),imageUri);
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "" + e, Toast.LENGTH_LONG).show();
                    }
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    selectedImage = imageReturnedIntent.getData();
                    userprofile.setImageURI(selectedImage);
                    presenter.uploadFb(getActivity(),selectedImage);
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
        Toast.makeText(getActivity(), "Please select minimum 1 interests", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void nextfragment() {
    }

    @Override
    public void nextfragmentprofile() {

    }

    @Override
    public void departmentSpinner(List<String> departmentList) {
        final ArrayAdapter<String> DepartmentArrayAdapter = new ArrayAdapter<String>(
                getActivity(),R.layout.spinnneritem, departmentList);
        DepartmentArrayAdapter.setDropDownViewResource(R.layout.spinnneritem);
        int spinnerPosition = DepartmentArrayAdapter.getPosition(dept);
        department.setSelection(spinnerPosition);
        department.setAdapter(DepartmentArrayAdapter);
    }

    @Override
    public void teamSpinner(List<String> TeamList) {
        final ArrayAdapter<String> TeamArrayAdapter = new ArrayAdapter<String>(
                getActivity(),R.layout.spinnneritem, TeamList);
        TeamArrayAdapter.setDropDownViewResource(R.layout.spinnneritem);
        int spinnerPosition = TeamArrayAdapter.getPosition(teamm);
        team.setSelection(spinnerPosition);
        team.setAdapter(TeamArrayAdapter);
    }

    @Override
    public void designationSpinner(List<String> DesignationList) {
        final ArrayAdapter<String> DesignationArrayAdapter = new ArrayAdapter<String>(
                getActivity(),R.layout.spinnneritem, DesignationList);
        DesignationArrayAdapter.setDropDownViewResource(R.layout.spinnneritem);
        int spinnerPosition = DesignationArrayAdapter.getPosition(desig);
        designation.setSelection(spinnerPosition);
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

    }

    @Override
    public void extractFb(String s) {
        imageurl=s;
    }
}