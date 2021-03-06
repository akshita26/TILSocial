package com.example.tilsocial.signin.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SigninAPIClient {
    private static final String BASE_URL = "http://til-social.herokuapp.com/tilsocialapp/profile/fetch/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
