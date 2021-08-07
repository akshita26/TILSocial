package com.example.tilsocial.signin.model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.tilsocial.signin.data.SigninAPIClient;
import com.example.tilsocial.signin.data.SigninAPIinterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInModel {
    SigninAPIinterface apiInterface;

    public void doSignin(SigninRequestParams signinRequestParams, Context c)
    {
        apiInterface = SigninAPIClient.getClient().create(SigninAPIinterface.class);

        Call<SigninRequestParams> GetCall = apiInterface.postSignIn(signinRequestParams.empId);

        GetCall.enqueue(new Callback<SigninRequestParams>() {
            @Override
            public void onResponse(Call<SigninRequestParams> call, Response<SigninRequestParams> response) {
                if(response.isSuccessful())
                {
                    Log.e("Employeeid", "onResponse: " + response.body().getEmployeeid());
                }
                else
                {
                    Log.e("null1234", "onResponse: " + "nulll getting" );
                }

            }

            @Override
            public void onFailure(Call<SigninRequestParams> call, Throwable t) {
//                Log.e(TAG, "onResponse: " + t.getMessage() );

            }
        });


    }
}
