package com.example.tilsocial.FeedDetail.api;

import com.example.tilsocial.FeedDetail.model.ModelPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {


    @GET("/userpost")
    Call<List<ModelPost>> getPost();



}
