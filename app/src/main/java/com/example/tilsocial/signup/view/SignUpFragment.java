package com.example.tilsocial.signup.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tilsocial.DashboardActivity;
import com.example.tilsocial.R;
import com.example.tilsocial.signup.model.SignUpModel;
import com.example.tilsocial.signup.model.SignupRequestParams;
import com.example.tilsocial.signup.presenter.SignupPresentor;

import java.util.ArrayList;
import java.util.List;

public class SignUpFragment extends Fragment implements SignupPresentor.SignupView {

    SignupPresentor signupPresentor;    Spinner department;
    private static final String TAG = "123jdvsn";
    Spinner team;    Spinner designation;    View view;
    EditText employeeidd;    Button signuppbtn;    EditText namee;
    EditText bioo;    ArrayAdapter departmentAdapter;    Context maincontext;
    Context attachcontext;

    View holderl;

    ArrayList<String>  dummylist;
    ArrayAdapter dummyadapter;

    public SignUpFragment(Context s25) {
        this.maincontext=s25;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signupPresentor = new SignupPresentor(SignUpFragment.this,getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,   Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_siggnuppfragment, container, false);
        department = view.findViewById(R.id.spinner4);
        team = view.findViewById(R.id.spinner5);
        designation = view.findViewById(R.id.spinner6);
        employeeidd = view.findViewById(R.id.editTextTextPersonName2);
        namee = view.findViewById(R.id.editTextTextPersonName);
        signuppbtn = view.findViewById(R.id.buttonsignup);
        bioo = view.findViewById(R.id.editTextTextPersonName3);
        signupPresentor.getdetailsofspinnertoview();

        dummylist = new ArrayList<>();
        dummylist.add("wrffr");
        dummylist.add("wrffr");
        dummylist.add("wrffr");
        dummyadapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,dummylist);
        // department.setAdapter(dummyadapter);

        this.holderl=view;

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
    public void nextfragment() {

        Intent intent = new Intent(getActivity(), DashboardActivity.class);
        startActivity(intent);
    }

    @Override
    public void departmentSpinner(List<String> departmentList,Context c) {
            if (c!=null){
                departmentAdapter=new ArrayAdapter(c,android.R.layout.simple_spinner_item,departmentList);
               departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
               department.setAdapter(departmentAdapter);
            } else {
                Log.d(TAG, "departmentSpinner: " + "context is null");
            }
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }
}