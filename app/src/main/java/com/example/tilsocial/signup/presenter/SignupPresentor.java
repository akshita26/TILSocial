package com.example.tilsocial.signup.presenter;

import android.util.Log;

import com.example.tilsocial.api.ApiClient;
import com.example.tilsocial.api.ApiInterface;
import com.example.tilsocial.signup.model.SignUpModel;
import com.example.tilsocial.signup.model.SignupRequestParams;
import com.example.tilsocial.signup.model.SpinnerDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupPresentor {

    private static final String TAG = "123jdvsn";
    SignupView signupView;
    SignUpModel signUpModel;
    SpinnerDetails spinnerDetails;
    ApiInterface apiInterface;
    SignupPresentor signupPresentor;



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

    public void spinnerdata() {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<SpinnerDetails> call = apiInterface.getspinnerDetails();
        call.enqueue(new Callback<SpinnerDetails>() {
            @Override
            public void onResponse(Call<SpinnerDetails> call, Response<SpinnerDetails> response) {

                if (response.isSuccessful()) {
                    Log.e(TAG, "onResponsespinner: " + response.body());
                    spinnerDetails = new SpinnerDetails();
                    spinnerDetails = response.body();
                    final List<String> departmentList = new ArrayList (Arrays.asList(spinnerDetails.getDepartment()));
                    Log.e(TAG, "onResponsespinner: " + departmentList);
                    signupView.departmentSpinner(departmentList);


//                    Log.e(TAG, "onResponsespinner123454: " + spinnerDetails.getDepartment());

                }
            }

            @Override
            public void onFailure(Call<SpinnerDetails> call, Throwable t) {
                Log.e(TAG, "onFailure343: " + t.getLocalizedMessage() );
            }
        });

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
