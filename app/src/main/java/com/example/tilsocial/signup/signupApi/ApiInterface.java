package com.example.tilsocial.signup.signupApi;

import com.example.tilsocial.signup.model.SignupRequestParams;
import com.example.tilsocial.signup.model.SpinnerDetails;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {



    @POST("profile/save")
    Call<SignupRequestParams> postSignUp(@Body SignupRequestParams signupRequestParams);

    @GET("common/fetch")
    Call<SpinnerDetails> getspinnerDetails();


}
