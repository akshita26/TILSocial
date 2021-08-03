package com.example.tilsocial.signup.model;

import android.util.Log;

import com.example.tilsocial.signup.data.ApiClient;
import com.example.tilsocial.signup.data.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpModel {

    private static final String TAG = "SignupPost1234";
    ApiInterface apiInterface;


    public void doSignup(SignupRequestParams signupRequestParams)
    {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);


        Call<SignupRequestParams> PostCall = apiInterface.postSignUp(signupRequestParams);

        PostCall.enqueue(new Callback<SignupRequestParams>() {
            @Override
            public void onResponse(Call<SignupRequestParams> call, Response<SignupRequestParams> response) {
                Log.e(TAG, "onResponse: " + response.code() );
                if(response!=null)
                {
                    Log.e(TAG, "onResponse: " + response.body().getBio() );
              }
                else
                {
                    Log.e("null1234", "onResponse: " + "nulll getting" );
                }

            }

            @Override
            public void onFailure(Call<SignupRequestParams> call, Throwable t) {
                Log.e(TAG, "onResponse: " + t.getMessage() );

            }
        });



        //retrofit call signup api

    }

}
