package com.example.tilsocial.signin.presentor;

import android.app.ProgressDialog;

import com.example.tilsocial.signin.model.SigninRequestParams;
import com.example.tilsocial.signin.model.UserData;

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

    @Override
    public void requestDataFromServer() {

    }

    @Override
    public void doSigninn(SigninRequestParams signinRequestParams, ProgressDialog mProgress) {
        if(signinRequestParams.getEmployeeid().length() > 0 && signinRequestParams.getEmployeeid().length() <=16)
        {
            signInModel.doSignin(signinRequestParams, this,mProgress);
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
        mainView.onResponseFailure(t);
    }

//    public interface SigninView {
//        void showError();
//    }

}
