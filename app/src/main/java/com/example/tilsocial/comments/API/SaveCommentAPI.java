package com.example.tilsocial.comments.API;

import com.example.tilsocial.comments.model.CommentModel;
import com.example.tilsocial.comments.model.PostComment;
import com.example.tilsocial.comments.model.PostCommentResponse;
import com.example.tilsocial.signup.model.SignupRequestParams;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SaveCommentAPI {
    @POST("save")
    Call<CommentModel> postcommntresponse(@Body PostComment postComment);
}
