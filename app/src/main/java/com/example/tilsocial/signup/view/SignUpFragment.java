package com.example.tilsocial.signup.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tilsocial.DashboardActivity;
import com.example.tilsocial.R;
import com.example.tilsocial.signup.model.SignUpModel;
import com.example.tilsocial.signup.model.SignupRequestParams;
import com.example.tilsocial.signup.presenter.SignupPresentor;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SignUpFragment extends Fragment implements SignupPresentor.SignupView {

    SignupPresentor signupPresentor;
    Spinner department;
    Spinner team;
    Spinner designation;
    View view;
    EditText employeeidd;
    Button signuppbtn;
    EditText namee;
    EditText bioo;
    ChipGroup chipGroup;
    String s;
    ImageView add;
    List<String> addList = new ArrayList<>();


    public SignUpFragment()
    {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signupPresentor = new SignupPresentor(this,new SignUpModel());

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

                Intent intent = new Intent(getActivity(), DashboardActivity.class);
                startActivity(intent);


            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout = view.findViewById(R.id.linearlayout);
                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                EditText editText = new EditText(getActivity());
                editText.setLayoutParams(p);
                linearLayout.addView(editText);

                editText.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if ((keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_SPACE)) {
                            try {
                                s = editText.getText().toString();
                                editText.setVisibility(View.GONE);
                                Chip chip = new Chip(getActivity());
                                chip.setText(s);
                                addList.add(s);
                                chip.setCloseIconVisible(true);
                                chip.setTextColor(getResources().getColor(R.color.grey_60));
                                chip.setTextAppearance(R.style.TextAppearance_AppCompat_Body2);
                                chipGroup.addView(chip);
                                chip.setOnCloseIconClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        chipGroup.removeView(chip);
                                    }
                                });
                                return true;
                            } catch (Exception e) {
                                Toast.makeText(getActivity(), "Re--" + e, Toast.LENGTH_LONG).show();
                            }
                        }
                        return false;
                    }
                });

            }
        });









        //department Spinnner

        String[] Department = new String[]{
                "Select department...",
                "department 1",
                "department 2",
                "department 3",
                "department 3"
        };

        final List<String> departmentList = new ArrayList<>(Arrays.asList(Department));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> DepartmentArrayAdapter = new ArrayAdapter<String>(
                getActivity(),R.layout.spinnneritem, departmentList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
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



        //Team Spinnner

        String[] Team = new String[]{
                "Select Team...",
                "Team 1",
                "Team 2",
                "Team 3",
                "Team 3"
        };

        final List<String> TeamList = new ArrayList<>(Arrays.asList(Team));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> TeamArrayAdapter = new ArrayAdapter<String>(
                getActivity(),R.layout.spinnneritem, TeamList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
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

        //designation Spinnner

        String[] Designation = new String[]{
                "Select Designation...",
                "Designation 1",
                "Designation 2",
                "Designation 3",
                "Designation 3"
        };

        final List<String> DesignationList = new ArrayList<>(Arrays.asList(Designation ));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> DesignationArrayAdapter = new ArrayAdapter<String>(
                getActivity(),R.layout.spinnneritem, DesignationList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
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
        return view;
    }

    @Override
    public void showError() {

        Toast.makeText(getActivity(), "Required Fields", Toast.LENGTH_SHORT).show();

    }
}






