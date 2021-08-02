package com.example.tilsocial;

import com.example.tilsocial.signup.model.SignupRequestParams;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("signup")
    Call<SignupRequestParams> postSignUp(@Body SignupRequestParams signupRequestParams);


}
