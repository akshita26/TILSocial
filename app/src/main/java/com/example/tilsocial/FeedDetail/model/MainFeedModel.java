package com.example.tilsocial.FeedDetail.model;

import android.util.Log;

import com.example.tilsocial.FeedDetail.api.ApiClient;
import com.example.tilsocial.FeedDetail.api.ApiInterface;
import com.example.tilsocial.FeedDetail.presentor.MainContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFeedModel implements MainContract.GetFeedList {

    ApiInterface apiInterface;
    private static final String TAG = "MainFeedModel";


    @Override
    public void getFeedList(OnFinishedListener onFinishedListener, String recent) {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);


        Call<List<ModelPost>> call = apiInterface.getPost(1,recent);
        call.enqueue(new Callback<List<ModelPost>>() {
            @Override
            public void onResponse(Call<List<ModelPost>> call, Response<List<ModelPost>> response) {
                Log.e(TAG, "onResponse: " +  response.body());
                onFinishedListener.onFinished(response.body());
            }

            @Override
            public void onFailure(Call<List<ModelPost>> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });




        //make a retrofit call here
    }
}
