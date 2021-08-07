package com.example.tilsocial.signin.data;

import com.example.tilsocial.signin.model.SigninRequestParams;
import com.example.tilsocial.signup.model.SignupRequestParams;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface SigninAPIinterface {
    @GET("signin/{empId}")
    Call<SigninRequestParams> postSignIn(@Path("empId") String empId);
}
