package com.example.tilsocial.likes.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LikeAPIClient {
    private static final String BASE_URL = "http://til-social.herokuapp.com/tilsocialapp/like/";
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
