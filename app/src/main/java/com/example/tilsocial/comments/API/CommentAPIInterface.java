package com.example.tilsocial.comments.API;

import com.example.tilsocial.comments.model.CommentModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CommentAPIInterface {
    @GET("fetch/{postId}")
    Call<List<CommentModel>> getcomment(@Path("postId") String postId);
}

