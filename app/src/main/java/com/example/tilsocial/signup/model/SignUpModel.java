package com.example.tilsocial.signup.model;

import android.util.Log;

import com.example.tilsocial.api.ApiClient;
import com.example.tilsocial.api.ApiInterface;
import com.example.tilsocial.signup.presenter.SignupPresentor;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpModel
{
    private static final String TAG = "SignupPost1234";
    ApiInterface apiInterface;
    SignupPresentor signupPresentor;
    SpinnerDetails spinnerDetails;



    public void doSignup(SignupRequestParams signupRequestParams)
    {
        signupPresentor = new SignupPresentor();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<SignupRequestParams> PostCall = apiInterface.postSignUp(signupRequestParams);
        PostCall.enqueue(new Callback<SignupRequestParams>() {
            @Override
            public void onResponse(Call<SignupRequestParams> call, Response<SignupRequestParams> response) {
                Log.e(TAG, "onResponse: " + response.code() );
                if(response!=null)
                {
                    Log.e(TAG, "onResponseasasxa: " + response.body().getBio() );
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


    interface Signupmodellgetspinnerdetails {

        interface OnFinishedListener {
            void onFinished(ArrayList<SpinnerDetails> noticeArrayList);
            void onFailure(Throwable t);
        }

        void getNoticeArrayList(OnFinishedListener onFinishedListener);
    }




}
