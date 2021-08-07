package com.example.tilsocial.signin.presentor;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.FragmentActivity;

import com.example.tilsocial.DashboardActivity;
import com.example.tilsocial.signin.model.SignInModel;
import com.example.tilsocial.signin.model.SigninRequestParams;
import com.example.tilsocial.signup.model.SignupRequestParams;

public class SigninPresentor {

    SignInModel signInModel;
    SigninView signinView;
    Context context;

    public SigninPresentor(SigninView signinView, SignInModel signInModel){
        this.signinView=signinView;
        this.signInModel=signInModel;
    }

    public int doSignin(SigninRequestParams signinRequestParams, FragmentActivity activity)
    {

        if(signinRequestParams.getEmployeeid().length() > 0 && signinRequestParams.getEmployeeid().length() <=16 && signinRequestParams.getEmployeeid().startsWith("1450"))
        {
            signInModel.doSignin(signinRequestParams,context);
            return 1;
        }
        else
        {
            signinView.showError();
            return 2;

        }

    }

    public interface SigninView {
        void showError();
    }

}
