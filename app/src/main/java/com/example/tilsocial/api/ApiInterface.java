package com.example.tilsocial.api;

import com.example.tilsocial.signup.model.SignupRequestParams;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {



    @POST("save")
    Call<SignupRequestParams> postSignUp(@Body SignupRequestParams signupRequestParams);


}
