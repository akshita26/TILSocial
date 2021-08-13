package com.example.tilsocial.signin.presentor;

import android.app.ProgressDialog;

import com.example.tilsocial.signin.model.SigninRequestParams;
import com.example.tilsocial.signin.model.UserData;

public interface ModeltoPresenter {
    interface presenter{
        void requestDataFromServer();
        void doSigninn(SigninRequestParams signinRequestParams, ProgressDialog mProgress);


    }
    interface MainView {
        void setDataToRecyclerView(UserData userData);
        void onResponseFailure(Throwable t);
        void showError();
        void nextActivity();
    }

    interface SignInModel {
        interface OnFinishedListener {
            void onFinished(UserData userData);
            void onFailure(Throwable t);
        }

//        void getUserData(OnFinishedListener onFinishedListener);
        void doSignin(SigninRequestParams signinRequestParams, OnFinishedListener onFinishedListener, ProgressDialog mProgress);
    }

}
