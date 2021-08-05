package com.example.tilsocial.api;

import com.example.tilsocial.signup.model.SignupRequestParams;
import com.example.tilsocial.signup.model.SpinnerRequestParams;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("dropdown")
    Call<SpinnerRequestParams> getSpinnerDetails();

    @POST("signup")
    Call<SignupRequestParams> postSignUp(@Body SignupRequestParams signupRequestParams);




}
