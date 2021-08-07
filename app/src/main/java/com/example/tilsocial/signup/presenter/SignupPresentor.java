package com.example.tilsocial.signup.presenter;

import android.util.Log;

import com.example.tilsocial.signup.model.SignUpModel;
import com.example.tilsocial.signup.model.SignupRequestParams;
import com.example.tilsocial.signup.model.SpinnerDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SignupPresentor {

    private static final String TAG = "123jdvsn";
    SignupView signupView;
    SignUpModel signUpModel;
    SpinnerDetails spinnerDetails;

    public SignupPresentor(SignupView signupView, SignUpModel signUpModel) {
        this.signupView = signupView;
        this.signUpModel = signUpModel;
    }

    public SignupPresentor() {

    }

    private boolean validateInputs(String employeeid, String name, String bio, String deprtment, String teamm, String desgniationn) {

        if (name.isEmpty()) {
            signupView.shownamevalidation();
            return true;
        }
        if (employeeid.isEmpty()) {
            signupView.showgetemployeevalidation();
            return true;
        }
        if (bio.isEmpty()) {
           signupView.showbiovalidation();
            return true;
        }
        if (deprtment.equals("Select department...")) {
           signupView.showdepartmentvalidation();
            return true;
        }
        if (teamm.equals("Select Team...")) {
            signupView.showteamvalidation();
            return true;
        }
        if (desgniationn.equals("Select Designation...")) {
           signupView.designationvalidation();
            return true;
        }
        return false;

    }




    public void departmentSpinnerdetail() {

        String[] Department = new String[]{
                "Select department...",
                "department 1",
                "department 2",
                "department 3",
                "department 3"
        };
        final List<String> departmentList = new ArrayList<>(Arrays.asList(Department));
        signupView.departmentSpinner(departmentList);
    }

    public void TeamSpinnerDetail() {
        String[] Team = new String[]{
                "Select Team...",
                "Team 1",
                "Team 2",
                "Team 3",
                "Team 3"
        };
        final List<String> TeamList = new ArrayList<>(Arrays.asList(Team));
        signupView.teamSpinner(TeamList);

    }

    public void DesignationSpinnerDetail() {

        String[] Designation = new String[]{
                "Select Designation...",
                "Designation 1",
                "Designation 2",
                "Designation 3",
                "Designation 3"
        };
        final List<String> DesignationList = new ArrayList<>(Arrays.asList(Designation));
        signupView.designationSpinner(DesignationList);

    }

    public void doSignUp(SignupRequestParams signupRequestParams) {
        if (!validateInputs(signupRequestParams.getEmployeeid(), signupRequestParams.getName(), signupRequestParams.getBio(), signupRequestParams.getDepartment(), signupRequestParams.getTeam(), signupRequestParams.getDesignation())) {

            signUpModel.doSignup(signupRequestParams);
            signupView.nextfragment();
        }

    }
    public void spinnerdata()
    {
        spinnerDetails = new SpinnerDetails();
        spinnerDetails =  signUpModel.getspinnerdetails();
        Log.e(TAG, "detailofspinnerinpresentor: " + spinnerDetails);
        //getting null value here why??


    }

    public interface SignupView {

        void shownamevalidation();
        void showgetemployeevalidation();
        void showbiovalidation();
        void showdepartmentvalidation();
        void showteamvalidation();
        void designationvalidation();
        void nextfragment();
        void departmentSpinner(List<String> departmentList);
        void teamSpinner(List<String> TeamList);
        void designationSpinner(List<String> DesignationList);

    }


}
