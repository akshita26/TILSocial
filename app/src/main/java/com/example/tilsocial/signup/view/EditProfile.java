package com.example.tilsocial.signup.view;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tilsocial.FeedDetail.view.HomeFragment;
import com.example.tilsocial.R;
import com.example.tilsocial.signup.model.SignUpModel;
import com.example.tilsocial.signup.model.SignupRequestParams;
import com.example.tilsocial.signup.model.SpinnerDetails;
import com.example.tilsocial.signup.presenter.MainContractSignup;
import com.example.tilsocial.signup.presenter.SignupPresentor;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class EditProfile extends Fragment implements MainContractSignup.MainView{
    ActionBar actionBar;
    Spinner department,team,designation;
    EditText namee,bioo;
    Button updatebtn;
    ChipGroup chipGroup;
    Chip chip;
    String empid;
    ArrayList<String> genres = new ArrayList<>();
    SpinnerDetails spinnerDetails;
    MainContractSignup.presenter presenter;


    public EditProfile() {
        // Required empty public constructor
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
        spinnerDetails =new SpinnerDetails();
        presenter.requestDataFromServerSpinner();
//        presenter.departmentSpinnerdetail();
////        presenter.TeamSpinnerDetail();
        presenter.DesignationSpinnerDetail();

        empid = getArguments().getString("key");
        Log.d("EditProfId", "onCreateView: "+empid);

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
                presenter.gotoprofile(signupRequestParams);
            }
        });
        return view;
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
    public void nextfragment() {
    }

    @Override
    public void nextfragmentprofile() {
        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.add(R.id.dashboard, homeFragment);
        ft.commit();
    }

    @Override
    public void departmentSpinner(List<String> departmentList) {
        departmentList.add(0, "Select department...");
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
        TeamList.add(0, "Select Team...");
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
}