package com.example.tilsocial.signup.presenter;

import android.util.Log;

import com.example.tilsocial.signup.model.SignUpModel;
import com.example.tilsocial.signup.model.SignupRequestParams;

public class SignupPresentor {

    SignupView signupView;
    SignUpModel signUpModel;

    public SignupPresentor(SignupView signupView , SignUpModel signUpModel)
    {
        this.signupView = signupView;
        this.signUpModel = signUpModel;


    }
    public void doSignUp(SignupRequestParams signupRequestParams)
    {


           signUpModel.doSignup(signupRequestParams);


    }

    public  interface SignupView
    {
        void showError () ;

    }
}
