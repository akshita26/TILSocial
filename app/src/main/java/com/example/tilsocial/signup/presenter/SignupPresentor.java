package com.example.tilsocial.signup.presenter;

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

        if(signupRequestParams.getEmployeeid().length() > 0 && signupRequestParams.getEmployeeid().length() <=16 )
        {
           signUpModel.doSignup(signupRequestParams);
         }
        else
        {
           signupView.showError();
       }

    }

    public  interface SignupView
    {
        void showError () ;

    }
}
