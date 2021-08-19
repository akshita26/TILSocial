package com.example.tilsocial.FeedDetail.api;

import com.example.tilsocial.FeedDetail.model.FeedContent;
import com.example.tilsocial.signup.model.SpinnerDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("post/fetch")
    Call<FeedContent> getPost(
            @Query("page") Integer page,
            @Query("sortBy") String sortBy,
            @Query("empId") Integer empId,
            @Query("type") String type
    );

    @GET("common/fetch")
    Call<SpinnerDetails> gettagsdetails();

//    Call<List<ModelPost>> getPost(Map<String, String> parameters);
}
