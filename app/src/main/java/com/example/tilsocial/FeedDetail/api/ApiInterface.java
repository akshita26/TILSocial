package com.example.tilsocial.FeedDetail.api;

import com.example.tilsocial.FeedDetail.model.FeedContent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("fetch")
    Call<FeedContent> getPost(
            @Query("page") Integer page,
            @Query("sortBy") String sortBy,
            @Query("empId") Integer empId,
            @Query("type") String type
    );


//    Call<List<ModelPost>> getPost(Map<String, String> parameters);
}
