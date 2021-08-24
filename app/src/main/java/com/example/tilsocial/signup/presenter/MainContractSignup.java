package com.example.tilsocial.signup.presenter;

import android.net.Uri;

import androidx.fragment.app.FragmentActivity;

import com.example.tilsocial.signup.model.Departments;
import com.example.tilsocial.signup.model.SignupRequestParams;
import com.example.tilsocial.signup.model.SpinnerDetails;

import java.util.HashMap;
import java.util.List;

public interface MainContractSignup {

    interface presenter{

        void dosignup(SignupRequestParams signupRequestParams);
        void gotoprofile(SignupRequestParams signupRequestParams);
        void requestDataFromServerSpinner();
        void uploadFb(FragmentActivity activity, Uri imageUri);

    }

    interface MainView {

//        void setDataToSpinner(SpinnerDetails spinnerDetails);
        void shownamevalidation();
        void showgetemployeevalidation();
        void showbiovalidation();
        void showdepartmentvalidation();
        void showteamvalidation();
        void designationvalidation();
        void showinterestvalidation();
        void nextfragment();
        void nextfragmentprofile();
        void onResponseFailure(Throwable t);
        void SetSignupdata(SignupRequestParams signupRequestParams);
        void extractFb(String s);
        void getspinnerdata(HashMap<String,List<Departments>> map);
        void gettagsdata(List<String> tagss);
    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface Model {


        interface OnFinishedListener {
            void onFinished(SpinnerDetails spinnerDetails);
            void OnFinishedSignupdata(SignupRequestParams signupRequestParams);
            void onFailure(Throwable t);
        }
        void dosignup(SignupRequestParams signupRequestParams, OnFinishedListener onFinishedListener);
        void getSpinnerDetail(OnFinishedListener onFinishedListener);
    }




}
