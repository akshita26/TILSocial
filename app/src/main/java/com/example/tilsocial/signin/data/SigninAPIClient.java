package com.example.tilsocial.signin.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SigninAPIClient {
    private static final String BASE_URL = "https://demo1192777.mockable.io";
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
