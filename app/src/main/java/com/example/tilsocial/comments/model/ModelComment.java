package com.example.tilsocial.comments.model;

import android.util.Log;

import com.example.tilsocial.comments.API.CommentAPIClient;
import com.example.tilsocial.comments.API.CommentAPIInterface;
import com.example.tilsocial.comments.API.SaveCommentAPI;
import com.example.tilsocial.comments.presenter.CommentPresenter;
import com.example.tilsocial.comments.presenter.MainContractComment;
import com.example.tilsocial.signup.model.SignupRequestParams;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelComment implements MainContractComment.Model {
    CommentAPIInterface apiInterface;
    SaveCommentAPI saveCommentAPI;


    @Override
    public void postcomment(PostComment postComment, OnFinishedListener onFinishedListener) {
        saveCommentAPI=CommentAPIClient.saveComment().create(SaveCommentAPI.class);
        Call<PostComment> PostCall= saveCommentAPI.postcommnt(postComment);
        PostCall.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                if(response!=null)
                {
                    onFinishedListener.OnFinishedSaveComment(response.body());
                    Log.e("NewCommnt", "Comment: " + response.body());
                }
                else
                {
                    Log.e("null1234", "onResponse: " + "nulll getting" );
                }
            }
            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {
                Log.e("TAG", "onResponsesignuppfail: " + t.getMessage() );
                onFinishedListener.onFailure(t);
            }
        });
    }

    @Override
    public void getComments(OnFinishedListener onFinishedListener, String postId) {
        apiInterface = CommentAPIClient.getClient().create(CommentAPIInterface.class);

        Log.d("postiddd", "getComments: "+postId);
        Call<List<CommentModel>> GetCall = apiInterface.getcomment(postId);

        GetCall.enqueue(new Callback<List<CommentModel>>() {
            @Override
            public void onResponse(Call<List<CommentModel>> call, Response<List<CommentModel>> response) {
                if(response.isSuccessful())
                {
//                    Log.e("Employeeid", "onResponse: " + response.body().get(0).getComment());
                    onFinishedListener.onFinished(response.body());
                }
                else
                {
                    Log.d("Errorhandling", "onResponse: ");

                }

            }

            @Override
            public void onFailure(Call<List<CommentModel>> call, Throwable t) {
                Log.e("Failure", "onResponse: " + t.getMessage() );
                onFinishedListener.onFailure(t);
            }
        });
    }
}
