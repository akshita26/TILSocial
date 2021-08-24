package com.example.tilsocial.FeedDetail.api;

import com.example.tilsocial.FeedDetail.model.FeedContent;
import com.example.tilsocial.FeedDetail.model.TagDetails;
import com.example.tilsocial.signin.model.UserData;
import com.example.tilsocial.signup.model.SpinnerDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
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


    @POST("profile/tag/save")
    Call<TagDetails> settagssdetails(@Body TagDetails tagDetails);

    @GET("profile/fetch/{empId}")
    Call<UserData> getColleagueProf(@Path("empId") Integer empId);

    @GET("recommend/fetch/{empId}")
    Call<List<UserData>> getRecommendation(@Path("empId") Integer empId);
}
