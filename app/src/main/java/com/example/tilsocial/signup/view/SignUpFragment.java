package com.example.tilsocial.signup.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tilsocial.DashboardActivity;
import com.example.tilsocial.R;
import com.example.tilsocial.signup.model.SignUpModel;
import com.example.tilsocial.signup.model.SignupRequestParams;
import com.example.tilsocial.signup.model.SpinnerDetails;
import com.example.tilsocial.signup.presenter.SignupPresentor;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class SignUpFragment extends Fragment implements SignupPresentor.SignupView {

    SignupPresentor signupPresentor;
    Spinner department, team, designation;
    View view;
    EditText employeeidd, namee, bioo;
    Button signuppbtn;
    ChipGroup chipGroup;
    Chip chip;
    ImageView add, userprofile;
    Uri imageUri;
    String simage;
    List<String> imageList = new ArrayList<>();
    List<String> interestList = new ArrayList<>();
    ArrayList<String> genres = new ArrayList<>();
    SpinnerDetails spinnerDetails;
    SignupPresentor.SignupView signupView;


    public SignUpFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signupPresentor = new SignupPresentor(this, new SignUpModel());

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
        signupPresentor.spinnerdata();

//        signupPresentor.departmentSpinnerdetail();
        signupPresentor.TeamSpinnerDetail();
        signupPresentor.DesignationSpinnerDetail();

        //Interest in chips
        genres.add("Mobile Application Development");
        genres.add("Android");
        genres.add("iOS");
        genres.add("System Design");
        for (int i = 0; i < genres.size(); i++) {
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

                String[] interest = {"Ani", "Sam", " Joe"};

                SignupRequestParams signupRequestParams = new SignupRequestParams();
                signupRequestParams.setEmployeeid(employeeidd.getText().toString());
                signupRequestParams.setName(namee.getText().toString());
                signupRequestParams.setBio(bioo.getText().toString());
                signupRequestParams.setDepartment(department.getSelectedItem().toString());
                signupRequestParams.setTeam(team.getSelectedItem().toString());
                signupRequestParams.setDesignation(designation.getSelectedItem().toString());
                signupRequestParams.setInterset(interest);
                signupPresentor.doSignUp(signupRequestParams);
            }
        });
        return view;
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
                    startActivityForResult(intent, 26);
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 27);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 26) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            userprofile.setImageBitmap(bitmap);
            userprofile.getLayoutParams().height = 200;
            userprofile.getLayoutParams().width = 200;
            try {
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
                File mFileTemp = null;
                mFileTemp = File.createTempFile("ab" + timeStamp, ".jpg", getActivity().getCacheDir());
                FileOutputStream fout;
                fout = new FileOutputStream(mFileTemp);
                bitmap.compress(Bitmap.CompressFormat.PNG, 70, fout);
                fout.flush();
                imageUri = Uri.fromFile(mFileTemp);
                simage = imageUri.toString();
                imageList.add(simage);
            } catch (Exception e) {
                Toast.makeText(getActivity(), "" + e, Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == 27) {
            imageUri = data.getData();
            simage = imageUri.toString();
            imageList.add(simage);
            Uri selectedImageUri = data.getData();
            if (null != selectedImageUri) {
                userprofile.setImageURI(selectedImageUri);
                userprofile.getLayoutParams().height = 200;
                userprofile.getLayoutParams().width = 200;
            }
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

//        Toast toast = new Toast(getActivity());
//        toast.setDuration(Toast.LENGTH_LONG);
//
//        //inflate view
//        View custom_view = getLayoutInflater().inflate(R.layout.toast_icon_text, null);
//        ((TextView) custom_view.findViewById(R.id.message)).setText("Department Required");
//        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_error_outline);
//        ((CardView) custom_view.findViewById(R.id.parent_view)).setCardBackgroundColor(getResources().getColor(R.color.blue_500));
//
//        toast.setView(custom_view);
//        toast.show();

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
    public void nextfragment() {
        Intent intent = new Intent(getActivity(), DashboardActivity.class);
        startActivity(intent);
    }

    @Override
    public void departmentSpinner(List<String> departmentList) {
        final ArrayAdapter<String> DepartmentArrayAdapter = new ArrayAdapter<String>(
                getActivity(), R.layout.spinnneritem, departmentList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
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
        final ArrayAdapter<String> TeamArrayAdapter = new ArrayAdapter<String>(
                getActivity(), R.layout.spinnneritem, TeamList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
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
                getActivity(), R.layout.spinnneritem, DesignationList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        DesignationArrayAdapter.setDropDownViewResource(R.layout.spinnneritem);
        designation.setAdapter(DesignationArrayAdapter);
    }


}






