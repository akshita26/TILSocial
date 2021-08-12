package com.example.tilsocial.signup.presenter;

import com.example.tilsocial.signup.model.SignupRequestParams;
import com.example.tilsocial.signup.model.SpinnerDetails;

import java.util.List;

public interface MainContractSignup {

    interface presenter{

        void dosignup(SignupRequestParams signupRequestParams);
        void gotoprofile(SignupRequestParams signupRequestParams);
        void requestDataFromServerSpinner();
        void departmentSpinnerdetail();
        void DesignationSpinnerDetail();
    }

    interface MainView {

//        void setDataToSpinner(SpinnerDetails spinnerDetails);
        void shownamevalidation();
        void showgetemployeevalidation();
        void showbiovalidation();
        void showdepartmentvalidation();
        void showteamvalidation();
        void designationvalidation();
        void nextfragment();
        void nextfragmentprofile();
        void departmentSpinner(List<String> departmentList);
        void teamSpinner(List<String> TeamList);
        void designationSpinner(List<String> DesignationList);
        void onResponseFailure(Throwable t);



    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface Model {


        interface OnFinishedListener {
            void onFinished(SpinnerDetails spinnerDetails);
            void onFailure(Throwable t);
        }
        void dosignup(SignupRequestParams signupRequestParams);
        void getSpinnerDetail(OnFinishedListener onFinishedListener);
    }




}
