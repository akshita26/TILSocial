package com.example.tilsocial.likes.API;

import com.example.tilsocial.comments.model.CommentModel;
import com.example.tilsocial.comments.model.PostComment;
import com.example.tilsocial.likes.model.LikeModel;
import com.example.tilsocial.likes.model.LikeModelResponse;
import com.example.tilsocial.likes.model.PostLike;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LikeAPI {
    @POST("save")
    Call<LikeModelResponse> postlikeresponse(@Body PostLike postLike);
}
