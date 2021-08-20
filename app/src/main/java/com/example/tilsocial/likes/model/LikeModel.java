package com.example.tilsocial.likes.model;

import android.util.Log;

import com.example.tilsocial.FeedDetail.view.AdapterPosts;
import com.example.tilsocial.comments.API.CommentAPIClient;
import com.example.tilsocial.comments.API.SaveCommentAPI;
import com.example.tilsocial.comments.model.CommentModel;
import com.example.tilsocial.likes.API.LikeAPI;
import com.example.tilsocial.likes.API.LikeAPIClient;
import com.example.tilsocial.likes.presenter.LikePresenter;
import com.example.tilsocial.likes.presenter.MainContractLike;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LikeModel implements MainContractLike.Model {
    LikeAPI likeAPI;

    LikePresenter likePresenter=new LikePresenter(this);
    @Override
    public void postlike(PostLike postLike) {
        likeAPI= LikeAPIClient.getClient().create(LikeAPI.class);
        Call<LikeModelResponse> PostCall= likeAPI.postlikeresponse(postLike);
        PostCall.enqueue(new Callback<LikeModelResponse>() {
            @Override
            public void onResponse(Call<LikeModelResponse> call, Response<LikeModelResponse> response) {
                if(response!=null)
                {   Log.e("LikePost", "response: " + response);
                    if(response.body()!=null)
                    {
                        Log.e("LikePost", "empId: " + response.body().getEmpId());
                        Log.e("LikePost", "postId: " + response.body().getPostId());
                        Log.e("LikePost", "likeCount: " + response.body().getLikesCount());
                        Log.e("LikePost", "hasLiked: " + response.body().getHasLiked());
                        postLike.setHasLiked(response.body().getHasLiked());
                        postLike.setLikesCount(response.body().getLikesCount());
                        Log.e("awwwwww", "1- " + postLike);
//                        likePresenter.likeresponse(postLike);
                    }
                    else{
                        Log.e("LikePostError", "error: response body is null " );
                    }

                }
                else
                {
                    Log.e("nulllike", "onResponse: " + "null from like" );
                }
            }
            @Override
            public void onFailure(Call<LikeModelResponse> call, Throwable t) {
                Log.e("TAG", "onResponsesignuppfail: " + t.getMessage() );
            }
        });
    }
}
