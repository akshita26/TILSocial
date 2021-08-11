package com.example.tilsocial.signin.model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.transition.Visibility;

import com.example.tilsocial.signin.data.SigninAPIClient;
import com.example.tilsocial.signin.data.SigninAPIinterface;
import com.example.tilsocial.signin.presentor.ModeltoPresenter;

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
    public void doSignin(SigninRequestParams signinRequestParams, OnFinishedListener onFinishedListener) {
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
                }
                else
                {
                    Log.e("null1234", "onResponse: " + "nulll getting" );
                }

            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Log.e("Failure", "onResponse: " + t.getMessage() );

            }
        });
    }
}
