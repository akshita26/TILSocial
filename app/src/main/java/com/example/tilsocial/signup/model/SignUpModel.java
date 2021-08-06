package com.example.tilsocial.signup.model;

import android.content.Context;
import android.util.Log;



import com.example.tilsocial.api.ApiClient;
import com.example.tilsocial.api.ApiInterface;

import com.example.tilsocial.signup.presenter.SignupPresentor;
import com.example.tilsocial.signup.view.SignUpFragment;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpModel
{

    private static final String TAG = "SignupPost1234";
    ApiInterface apiInterface;
    SpinnerRequestParams spinnerRequestParams;
    SignupPresentor signupPresentor;
    Context mc;
    Context msc;
    ArrayList<String> d;
    public SignUpModel(Context mc, SignupPresentor signupPresentor) {
        this.msc = mc;
        this.signupPresentor = signupPresentor;

    }

    public void doSignup(SignupRequestParams signupRequestParams, Context mainacontext)
    {
        //this.mc=mainacontext;
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

    }
    public List<String> getspinnerdetails()
    {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<SpinnerRequestParams> call = apiInterface.getSpinnerDetails();
        call.enqueue(new Callback<SpinnerRequestParams>() {
            @Override
            public void onResponse(Call<SpinnerRequestParams> call, Response<SpinnerRequestParams> response) {
                    Log.e(TAG, "onResponsespinner: " +  response.body());
                    spinnerRequestParams = new SpinnerRequestParams();
                    spinnerRequestParams.setDepartment(response.body().department);
                    spinnerRequestParams.setTeam(response.body().team);
                    spinnerRequestParams.setDesignation(response.body().designation);
                    Log.e(TAG, "onResponsespinnersignparams: " +  spinnerRequestParams);


                 signupPresentor.getSpinnerDetailinpresentor(spinnerRequestParams);


            }

            @Override
            public void onFailure(Call<SpinnerRequestParams> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage() );
            }
        });
        return d;
    }
}
