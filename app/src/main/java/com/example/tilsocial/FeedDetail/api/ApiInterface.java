package com.example.tilsocial.FeedDetail.api;

import com.example.tilsocial.FeedDetail.model.ModelPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("posts")
    Call<List<ModelPost>> getPost(
            @Query("empId") Integer empId,
            @Query("_sort") String sort
    );


//    Call<List<ModelPost>> getPost(Map<String, String> parameters);
}
