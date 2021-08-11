package com.example.tilsocial.signin.presentor;

import android.content.Context;

import com.example.tilsocial.FeedDetail.model.ModelPost;
import com.example.tilsocial.FeedDetail.presentor.MainContract;
import com.example.tilsocial.UserPosts;
import com.example.tilsocial.signin.model.SigninRequestParams;
import com.example.tilsocial.signin.model.UserData;

import java.util.List;

public interface ModeltoPresenter {
    interface presenter{
        void requestDataFromServer();
        void doSigninn(SigninRequestParams signinRequestParams);


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
        void doSignin(SigninRequestParams signinRequestParams, OnFinishedListener onFinishedListener);
    }

}
