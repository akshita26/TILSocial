package com.example.tilsocial.addpost.model;

import android.util.Log;

import org.json.JSONException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPostModel {
    public  void doPost(AddPostRequestParams addPostRequestParams) throws JSONException {
        addPostRequestParams.setEmpId(7303);

        //retrofit call
        Call<AddPostModelList> addPostModelList=AddPostAPI.getService().getPostList(addPostRequestParams);
        addPostModelList.enqueue(new Callback<AddPostModelList>() {

            @Override
            public void onResponse(Call<AddPostModelList> call, Response<AddPostModelList> response) {
                AddPostModelList list=response.body();
                if (list!=null){
                    Log.e("postId", "postId-: " + response.body().getPostId() );
                    Log.e("name", "name-: " + response.body().getName() );
                    Log.e("Response", "hasLiked-: " + response.body().getHasLiked());
                    Log.e("Response", "EmpId-: " + response.body().getEmpId() );
//                    Log.e("Response", "CreatedAt-: " + response.body().getCreatedAt());
//                    Log.e("Response", "Tags: " + response.body().getTags()[0]);
//                    Log.e("Response", "=-: " + response.body().Details());
//                    Log.e("Response", "Tags-: " + Arrays.toString(response.body().getTags()));

                }
                else {
                    Log.e("Response", "My Response is null " + response.body() );
                }

            }

            @Override
            public void onFailure(Call<AddPostModelList> call, Throwable t) {
                Log.e("Fail", "Failure message: " + t.getMessage() );
            }
        });

    }
}
