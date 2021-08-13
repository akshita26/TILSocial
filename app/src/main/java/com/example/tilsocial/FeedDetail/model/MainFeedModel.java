package com.example.tilsocial.FeedDetail.model;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.tilsocial.FeedDetail.api.ApiClient;
import com.example.tilsocial.FeedDetail.api.ApiInterface;
import com.example.tilsocial.FeedDetail.presentor.MainContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFeedModel implements MainContract.GetFeedList {

    ApiInterface apiInterface;
    private static final String TAG = "MainFeedModel";


    @Override
    public void getFeedList(OnFinishedListener onFinishedListener, int page, String filter, int empid, String type, ProgressBar loadingPB) {

        loadingPB.setVisibility(View.VISIBLE);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        Log.e(TAG, "recentt" + recent);
        Call<FeedContent> call = apiInterface.getPost(page,filter,empid,type);
        call.enqueue(new Callback<FeedContent>() {
            @Override
            public void onResponse(Call<FeedContent> call, Response<FeedContent> response) {
                Log.e(TAG, "onResponse: " +  response.body());
                loadingPB.setVisibility(View.GONE);
                onFinishedListener.onFinished(response.body().getModelPostList());
            }

            @Override
            public void onFailure(Call<FeedContent> call, Throwable t) {
                Log.e(TAG, "faliure" +  t.getMessage());
                onFinishedListener.onFailure(t);
            }
        });




        //make a retrofit call here
    }
}
