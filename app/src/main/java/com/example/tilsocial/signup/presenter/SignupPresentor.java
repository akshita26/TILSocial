package com.example.tilsocial.signup.presenter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.tilsocial.signup.model.SignUpModel;
import com.example.tilsocial.signup.model.SignupRequestParams;
import com.example.tilsocial.signup.model.SpinnerRequestParams;
import com.example.tilsocial.signup.view.SignUpFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SignupPresentor {

    private static final String TAG = "123jdvsn";    SignupView signupView;    SignUpModel signUpModel;    String[] Department;    List<String> departmentList;    String[] Team;    List<String> TeamList;    String[] Designation;    List<String> DesignationList;    Context mainacontext;

    public SignupPresentor(SignupView signupView, Context s26) {
        this.signupView = signupView;
        this.signUpModel = new SignUpModel(s26, this);
        this.mainacontext=s26;
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

    public void getdetailsofspinnertoview() {
        signUpModel.getspinnerdetails();
    }

    public List<String> getSpinnerDetailinpresentor(@NonNull SpinnerRequestParams spinnerRequestParams) {

         Department = spinnerRequestParams.getDepartment();
         departmentList = new ArrayList<>(Arrays.asList(Department));
         Team = spinnerRequestParams.getTeam();
         TeamList = new ArrayList<>(Arrays.asList(Team));
         Designation = spinnerRequestParams.getDesignation();
         DesignationList = new ArrayList<>(Arrays.asList(Designation));
         signupView.departmentSpinner(departmentList,mainacontext);

         return departmentList;

    }

    public void doSignUp(SignupRequestParams signupRequestParams) {

        if (!validateInputs(signupRequestParams.getEmployeeid(), signupRequestParams.getName(), signupRequestParams.getBio(), signupRequestParams.getDepartment(), signupRequestParams.getTeam(), signupRequestParams.getDesignation())) {
            signUpModel.doSignup(signupRequestParams,mainacontext);
            signupView.nextfragment();
        }
    }

    public interface SignupView {
        void shownamevalidation();
        void showgetemployeevalidation();
        void showbiovalidation();
        void showdepartmentvalidation();
        void showteamvalidation();
        void designationvalidation();
        void nextfragment();
        void departmentSpinner(List<String> departmentList,Context c);

    }

}
