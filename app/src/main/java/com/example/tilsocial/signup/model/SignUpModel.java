package com.example.tilsocial.signup.model;

import android.util.Log;

import com.example.tilsocial.api.ApiClient;
import com.example.tilsocial.api.ApiInterface;
import com.example.tilsocial.signup.api.ApiClientSpinner;
import com.example.tilsocial.signup.api.ApiInterfaceSpinner;
import com.example.tilsocial.signup.presenter.MainContractSignup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpModel implements MainContractSignup.Model
{
    private static final String TAG = "SignupPost1234";
    ApiInterface apiInterface;
    ApiInterfaceSpinner apiInterfaceSpinner;




    public void dosignup(SignupRequestParams signupRequestParams)
    {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<SignupRequestParams> PostCall = apiInterface.postSignUp(signupRequestParams);
        PostCall.enqueue(new Callback<SignupRequestParams>() {
            @Override
            public void onResponse(Call<SignupRequestParams> call, Response<SignupRequestParams> response) {
                Log.e(TAG, "onResponse: " + response.code() );
                if(response!=null)
                {
                    Log.e(TAG, "onResponseasasxa: " + response.body().getEmpId());
                }
                else
                {
                    Log.e("null1234", "onResponse: " + "nulll getting" );
                }
            }
            @Override
            public void onFailure(Call<SignupRequestParams> call, Throwable t) {
                Log.e(TAG, "onResponsesignuppfail: " + t.getMessage() );
            }
        });
    }

    @Override
    public void getSpinnerDetail(OnFinishedListener onFinishedListener) {

        apiInterfaceSpinner = ApiClientSpinner.getClient().create(ApiInterfaceSpinner.class);
        Call<SpinnerDetails> call = apiInterfaceSpinner.getspinnerDetails();
        call.enqueue(new Callback<SpinnerDetails>() {
            @Override
            public void onResponse(Call<SpinnerDetails> call, Response<SpinnerDetails> response) {

                Log.e(TAG, "onResponsesignupmodel: " +  response.body());
                onFinishedListener.onFinished(response.body());
            }

            @Override
            public void onFailure(Call<SpinnerDetails> call, Throwable t) {
                onFinishedListener.onFailure(t);
                Log.e(TAG, "onResponsesignupmodel: " +  t.getMessage());
            }
        });

    }


}
