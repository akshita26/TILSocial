package com.example.tilsocial.signup.view;


import android.content.Context;
import android.content.Intent;
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

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tilsocial.DashboardActivity;
import com.example.tilsocial.R;
import com.example.tilsocial.signup.model.SignUpModel;
import com.example.tilsocial.signup.model.SignupRequestParams;
import com.example.tilsocial.signup.presenter.SignupPresentor;

import java.util.List;

public class SignUpFragment extends Fragment implements SignupPresentor.SignupView {

    SignupPresentor signupPresentor;
    Spinner department;
    private static final String TAG = "123jdvsn";

    Spinner team;
    Spinner designation;
    View view;
    EditText employeeidd;
    Button signuppbtn;
    EditText namee;
    EditText bioo;
    ArrayAdapter departmentAdapter;

    Context s26;

    public SignUpFragment() {

    }

    public SignUpFragment(Context s25) {
        this.s26=s25;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signupPresentor = new SignupPresentor(this, new SignUpModel());

      //  s26=getActivity();
        Log.d(TAG, "4234324235gdfg: "+
                "c1 is" +s26

        );
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
//        signupPresentor.getdetailsofspinnertoview();

        signupPresentor.departmentSpinnerdetail();
        signupPresentor.TeamSpinnerDetail();
        signupPresentor.DesignationSpinnerDetail();

        signuppbtn.setOnClickListener(v -> {

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
        });
        return view;
    }


    @Override
    public void shownamevalidation() {

        namee.setError("Name Required");
        namee.requestFocus();
    }

    @Override
    public void showgetemployeevalidation() {
        employeeidd.setError("Employee id Required");
        employeeidd.requestFocus();
    }

    @Override
    public void showbiovalidation() {
        bioo.setError("Bio Required");
        bioo.requestFocus();
    }

    @Override
    public void showdepartmentvalidation() {
        Toast.makeText(getActivity(), "Department is Required", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showteamvalidation() {
        Toast.makeText(getActivity(), "Team is Required", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void designationvalidation() {
        Toast.makeText(getActivity(), "Designation is Required", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void nextfragment() {

        Intent intent = new Intent(getActivity(), DashboardActivity.class);
        startActivity(intent);
    }



    @Override
    public void departmentSpinner(List<String> departmentList) {


        Log.e("getting detailsss", "onResponsespinner4242department: " + departmentList);
//        departmentAdapter = new ArrayAdapter(getActivity(), android.R.layout.spinnneritem, departmentList);
//        Log.e("getting detailsss", "departmentadapter1334 " + departmentAdapter.getCount());
//        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        department.setAdapter(departmentAdapter);

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



//        final ArrayAdapter<String> DepartmentArrayAdapter = new ArrayAdapter<String>(
//                getActivity(),R.layout.spinnneritem, departmentList){
//            @Override
//            public boolean isEnabled(int position){
//                if(position == 0)
//                {
//                    // Disable the first item from Spinner
//                    // First item will be use for hint
//                    return false;
//                }
//                else
//                {
//                    return true;
//                }
//            }
//            @Override
//            public View getDropDownView(int position, View convertView,
//                                        ViewGroup parent) {
//                View view = super.getDropDownView(position, convertView, parent);
//                TextView tv = (TextView) view;
//                if(position == 0){
//                    // Set the hint text color gray
//                    tv.setTextColor(Color.GRAY);
//                }
//                else {
//                    tv.setTextColor(Color.BLACK);
//                }
//                return view;
//            }
//        };
//        DepartmentArrayAdapter.setDropDownViewResource(R.layout.spinnneritem);
//        department.setAdapter(DepartmentArrayAdapter);
    }


  /*  @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            s23 =(Activity) context;
        }
    }*/
    @Override
    public void teamSpinner(List<String> TeamList){


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

    }
    @Override
    public void designationSpinner(List<String> DesignationList) {
        Log.e("getting detailsss", "onResponsespinner4242designation: " + DesignationList);
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


      }
}