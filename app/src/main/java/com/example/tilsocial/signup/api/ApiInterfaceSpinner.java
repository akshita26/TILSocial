package com.example.tilsocial.signup.api;

import com.example.tilsocial.signup.model.SpinnerDetails;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterfaceSpinner {

    @GET("Signupspinner")
    Call<SpinnerDetails> getspinnerDetails();




}
