package com.example.tilsocial.signup.presenter;


import com.example.tilsocial.FeedDetail.presentor.MainContract;
import com.example.tilsocial.signup.model.SignupRequestParams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SignupPresentor implements MainContractSignup.presenter ,MainContractSignup.Model {

    private MainContractSignup.MainView mainView;
    private MainContractSignup.Model model;

    public SignupPresentor(MainContractSignup.MainView mainView, MainContractSignup.Model model) {
        this.mainView = mainView;
        this.model = model;
    }

    private boolean validateInputs(String employeeid, String name, String bio, String deprtment, String teamm, String desgniationn) {

        if (name.isEmpty()) {
            mainView.shownamevalidation();
            return true;
        }
        if (employeeid.isEmpty()) {
            mainView.showgetemployeevalidation();
            return true;
        }
        if (bio.isEmpty()) {
            mainView.showbiovalidation();
            return true;
        }
        if (deprtment.equals("Select department...")) {
            mainView.showdepartmentvalidation();
            return true;
        }
        if (teamm.equals("Select Team...")) {
            mainView.showteamvalidation();
            return true;
        }
        if (desgniationn.equals("Select Designation...")) {
            mainView.designationvalidation();
            return true;
        }
        return false;

    }


    @Override
    public void dosignup(SignupRequestParams signupRequestParams) {

        if (!validateInputs(signupRequestParams.getEmployeeid(), signupRequestParams.getName(), signupRequestParams.getBio(), signupRequestParams.getDepartment(), signupRequestParams.getTeam(), signupRequestParams.getDesignation())) {

            model.dosignup(signupRequestParams);
            mainView.nextfragment();
        }

    }

    @Override
    public void requestDataFromServerSpinner() {

    }

    @Override
    public void departmentSpinnerdetail() {
        String[] Department = new String[]{
                "Select department...",
                "department 1",
                "department 2",
                "department 3",
                "department 3"
        };
        final List<String> departmentList = new ArrayList<>(Arrays.asList(Department));
        mainView.departmentSpinner(departmentList);

    }

    @Override
    public void TeamSpinnerDetail() {
        String[] Team = new String[]{
                "Select Team...",
                "Team 1",
                "Team 2",
                "Team 3",
                "Team 3"
        };
        final List<String> TeamList = new ArrayList<>(Arrays.asList(Team));
        mainView.teamSpinner(TeamList);

    }

    @Override
    public void DesignationSpinnerDetail() {
        String[] Designation = new String[]{
                "Select Designation...",
                "Designation 1",
                "Designation 2",
                "Designation 3",
                "Designation 3"
        };
        final List<String> DesignationList = new ArrayList<>(Arrays.asList(Designation));
        mainView.designationSpinner(DesignationList);

    }


    @Override
    public void getSpinnerDetail(MainContract.GetFeedList.OnFinishedListener onFinishedListener) {



    }
}
