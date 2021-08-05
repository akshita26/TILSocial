package com.example.tilsocial.signup.presenter;

import androidx.annotation.NonNull;

import com.example.tilsocial.signup.model.SignUpModel;
import com.example.tilsocial.signup.model.SignupRequestParams;
import com.example.tilsocial.signup.model.SpinnerRequestParams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SignupPresentor {

    private static final String TAG = "123jdvsn";
    SignupView signupView;
    SignUpModel signUpModel;
//    String[] Department;
//    List<String> departmentList;
//    String[] Team;
//    List<String> TeamList;
//    String[] Designation;
//    List<String> DesignationList;
//    Context c;





    public SignupPresentor(SignupView signupView, SignUpModel signUpModel) {
        this.signupView = signupView;
        this.signUpModel = signUpModel;


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

    public void getSpinnerDetailinpresentor(@NonNull SpinnerRequestParams spinnerRequestParams) {


//        Log.e("getting detailsss", "onResponsespinner4242: " + spinnerRequestParams);
//         Department = spinnerRequestParams.getDepartment();
//         Log.e("getting detailsss", "onResponsespinner4242: " + Department);
//         departmentList = new ArrayList<>(Arrays.asList(Department));
//         Log.e("getting detailsss", "onResponsespinnerdepartmnetlistt " + departmentList);
//         Team = spinnerRequestParams.getTeam();
//         TeamList = new ArrayList<>(Arrays.asList(Team));
//         Designation = spinnerRequestParams.getDesignation();
//         DesignationList = new ArrayList<>(Arrays.asList(Designation));
//         signupView.departmentSpinner(departmentList);


//         signupView.teamSpinner(TeamList);
//         signupView.designationSpinner(DesignationList);


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
//        if (!validateInputs(signupRequestParams.getEmployeeid(), signupRequestParams.getName(), signupRequestParams.getBio(), signupRequestParams.getDepartment(), signupRequestParams.getTeam(), signupRequestParams.getDesignation())) {

//            signupView.nextfragment();
//        }
        signUpModel.doSignup(signupRequestParams);
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
