package com.example.tilsocial.signin.model;

import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Toast;

import com.example.tilsocial.MainActivity;
import com.example.tilsocial.signin.data.SigninAPIClient;
import com.example.tilsocial.signin.data.SigninAPIinterface;
import com.example.tilsocial.signin.presentor.ModeltoPresenter;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInModel implements ModeltoPresenter.SignInModel {
    SigninAPIinterface apiInterface;

//    public void doSignin(SigninRequestParams signinRequestParams, Context c)
//    {
//        apiInterface = SigninAPIClient.getClient().create(SigninAPIinterface.class);
//
//        int EmployeeId=Integer.parseInt(signinRequestParams.getEmployeeid());
//
//        Call<UserData> GetCall = apiInterface.getSignIn(EmployeeId);
//
//        GetCall.enqueue(new Callback<UserData>() {
//            @Override
//            public void onResponse(Call<UserData> call, Response<UserData> response) {
//                if(response.isSuccessful())
//                {
//                    Log.e("Employeeid", "onResponse: " + response.body().getEmpId());
//                    Log.e("Employeename", "onResponse: " + response.body().getName());
//                }
//                else
//                {
//                    Log.e("null1234", "onResponse: " + "nulll getting" );
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<UserData> call, Throwable t) {
//                Log.e("Failure", "onResponse: " + t.getMessage() );
//
//            }
//        });
//
//
//    }
    @Override
    public void doSignin(SigninRequestParams signinRequestParams, OnFinishedListener onFinishedListener, ProgressDialog mProgress) {
        apiInterface = SigninAPIClient.getClient().create(SigninAPIinterface.class);

        int EmployeeId=Integer.parseInt(signinRequestParams.getEmployeeid());

        Call<UserData> GetCall = apiInterface.getSignIn(EmployeeId);

        GetCall.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                if(response.isSuccessful())
                {
                    Log.e("Employeeid", "onResponse: " + response.body().getEmpId());
                    Log.e("Employeename", "onResponse: " + response.body().getName());
                    onFinishedListener.onFinished(response.body());
                    mProgress.dismiss();
                }
                else
                {
                    mProgress.dismiss();
                    ErrorResponse errorResponse = ErrorUtils.parseError(response);
                    Log.d("Errorhandling", "onResponse: "+errorResponse.getError());
                    onFinishedListener.userNotExist(errorResponse.getMessage());
                }

            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Log.e("Failure", "onResponse: " + t.getMessage() );
                onFinishedListener.onFailure(t);
                mProgress.dismiss();
            }
        });
    }
}
