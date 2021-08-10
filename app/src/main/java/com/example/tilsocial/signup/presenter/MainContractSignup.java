package com.example.tilsocial.signup.presenter;

import com.example.tilsocial.FeedDetail.model.ModelPost;
import com.example.tilsocial.FeedDetail.presentor.MainContract;
import com.example.tilsocial.signup.model.SignupRequestParams;

import java.util.List;

public interface MainContractSignup {

    interface presenter{

        void dosignup(SignupRequestParams signupRequestParams);
        void requestDataFromServerSpinner();
        void departmentSpinnerdetail();
        void TeamSpinnerDetail();
        void DesignationSpinnerDetail();
    }

    interface MainView {

        void setDataToSpinner(List<ModelPost> ModalPostList);
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
        void onResponseFailure(Throwable t);



    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface Model {

        interface OnFinishedListener {
            void onFinished(List<ModelPost> ModalPostList);
            void onFailure(Throwable t);
        }
        void dosignup(SignupRequestParams signupRequestParams);

        void getSpinnerDetail(MainContract.GetFeedList.OnFinishedListener onFinishedListener);
    }




}
