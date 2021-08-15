package com.example.tilsocial.comments.model;

import android.util.Log;

import com.example.tilsocial.comments.API.CommentAPIClient;
import com.example.tilsocial.comments.API.CommentAPIInterface;
import com.example.tilsocial.comments.presenter.MainContractComment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelComment implements MainContractComment.Model {
    CommentAPIInterface apiInterface;


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
