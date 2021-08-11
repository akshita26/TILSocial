package com.example.tilsocial.signin.presentor;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.FragmentActivity;

import com.example.tilsocial.DashboardActivity;
import com.example.tilsocial.signin.model.SignInModel;
import com.example.tilsocial.signin.model.SigninRequestParams;
import com.example.tilsocial.signin.model.UserData;
import com.example.tilsocial.signup.model.SignupRequestParams;

import java.util.List;

public class SigninPresentor implements ModeltoPresenter.presenter, ModeltoPresenter.SignInModel.OnFinishedListener {

//    SignInModel signInModel;
//    SigninView signinView;
//    Context context;
    private ModeltoPresenter.MainView mainView;
    private ModeltoPresenter.SignInModel signInModel;

//    public SigninPresentor(SigninView signinView, SignInModel signInModel){
//        this.signinView=signinView;
//        this.signInModel=signInModel;
//    }

    public SigninPresentor(ModeltoPresenter.MainView mainView, ModeltoPresenter.SignInModel signInModel) {
        this.mainView = mainView;
        this.signInModel = signInModel;
    }

//    public int doSignin(SigninRequestParams signinRequestParams)
//    {
//
//        if(signinRequestParams.getEmployeeid().length() > 0 && signinRequestParams.getEmployeeid().length() <=16 && signinRequestParams.getEmployeeid().startsWith("1450"))
//        {
//            signInModel.doSignin(signinRequestParams,context);
//            return 1;
//        }
//        else
//        {
//            signinView.showError();
//            return 2;
//
//        }
//
//    }

    @Override
    public void requestDataFromServer() {

    }

    @Override
    public void doSigninn(SigninRequestParams signinRequestParams) {
        if(signinRequestParams.getEmployeeid().length() > 0 && signinRequestParams.getEmployeeid().length() <=16)
        {
            signInModel.doSignin(signinRequestParams, this);
            mainView.nextActivity();
        }
        else
        {
            mainView.showError();

        }
    }

    @Override
    public void onFinished(UserData UserDataList) {
        if(mainView != null){
            mainView.setDataToRecyclerView(UserDataList);
        }
    }

    @Override
    public void onFailure(Throwable t) {

    }

//    public interface SigninView {
//        void showError();
//    }

}
