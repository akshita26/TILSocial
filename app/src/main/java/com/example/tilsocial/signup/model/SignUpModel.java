package com.example.tilsocial.signup.model;

import android.util.Log;

import com.example.tilsocial.signin.model.ErrorResponse;
import com.example.tilsocial.signin.model.ErrorUtils;
import com.example.tilsocial.signup.api.ApiInterfaceSpinner;
import com.example.tilsocial.signup.presenter.MainContractSignup;
import com.example.tilsocial.signup.signupApi.ApiClient;
import com.example.tilsocial.signup.signupApi.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpModel implements MainContractSignup.Model
{
    private static final String TAG = "SignupPost1234";
    ApiInterface apiInterface;
    ApiInterfaceSpinner apiInterfaceSpinner;

    public void dosignup(SignupRequestParams signupRequestParams, OnFinishedListener onFinishedListener)
    {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<SignupRequestParams> PostCall = apiInterface.postSignUp(signupRequestParams);
        PostCall.enqueue(new Callback<SignupRequestParams>() {
            @Override
            public void onResponse(Call<SignupRequestParams> call, Response<SignupRequestParams> response) {
   if(response.isSuccessful()){
                Log.e(TAG, "onResponse: " + response.code() );

                    onFinishedListener.OnFinishedSignupdata(response.body());

                    Log.e(TAG, "onResponseasasxa: " + response.body());}
   else {

       ErrorResponse errorResponse = ErrorUtils.parseError(response);
       Log.d("Errorhandling", "onResponse: " + errorResponse.getError());

       Log.d(TAG, "onResponse: errorrrr ");
   }

            }
            @Override
            public void onFailure(Call<SignupRequestParams> call, Throwable t) {
                Log.e(TAG, "onResponsesignuppfail: " + t.getMessage() );
                onFinishedListener.onFailure(t);
            }
        });
    }



    @Override
    public void getSpinnerDetail(OnFinishedListener onFinishedListener) {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<SpinnerDetails> call = apiInterface.getspinnerDetails();
        call.enqueue(new Callback<SpinnerDetails>() {
            @Override
            public void onResponse(Call<SpinnerDetails> call, Response<SpinnerDetails> response) {
                if(response.isSuccessful()){

                Log.e(TAG, "onResponsesignupmodel3433: " +  response.body());


                    onFinishedListener.onFinished(response.body());}
                else {


                    ErrorResponse errorResponse = ErrorUtils.parseError(response);
                    Log.d("Errorhandling", "onResponse: " + errorResponse.getError());

                    Log.d(TAG, "onResponse: errorrrr ");
                }


            }

            @Override
            public void onFailure(Call<SpinnerDetails> call, Throwable t) {
                onFinishedListener.onFailure(t);
                Log.e(TAG, "onResponsesignupmodel: " +  t.getMessage());
            }
        });

    }


}
