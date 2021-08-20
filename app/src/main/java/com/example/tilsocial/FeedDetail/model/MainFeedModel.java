package com.example.tilsocial.FeedDetail.model;

import android.content.Context;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.tilsocial.FeedDetail.api.ApiClient;
import com.example.tilsocial.FeedDetail.api.ApiInterface;
import com.example.tilsocial.FeedDetail.presentor.MainContract;
import com.example.tilsocial.signin.model.ErrorResponse;
import com.example.tilsocial.signin.model.ErrorUtils;
import com.example.tilsocial.signup.model.SpinnerDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFeedModel implements MainContract.GetFeedList {

    ApiInterface apiInterface;
    private static final String TAG = "MainFeedModel";


    @Override
    public void getFeedList(OnFinishedListener onFinishedListener, int page, String filter, int empid, String type, ProgressBar loadingPB) {

     //loadingPB.setVisibility(View.VISIBLE);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        Log.e(TAG, "recentt" + recent);
        Call<FeedContent> call = apiInterface.getPost(page,filter,empid,type);
        call.enqueue(new Callback<FeedContent>() {
            @Override
            public void onResponse(Call<FeedContent> call, Response<FeedContent> response) {
                if(response.isSuccessful()){
                Log.e(TAG, "onResponse: " +  response.body());
               //loadingPB.setVisibility(View.GONE);
                FeedContent feedContent = response.body();
                onFinishedListener.onFinished(response.body().getModelPostList(),feedContent);}
                else{
                    ErrorResponse errorResponse = ErrorUtils.parseError(response);
                    Log.d("Errorhandling", "onResponse: "+errorResponse.getError());

                    Log.d(TAG, "onResponse: errorrrr ");
                }
            }

            @Override
            public void onFailure(Call<FeedContent> call, Throwable t) {
                Log.e(TAG, "faliure" +  t.getMessage());
                onFinishedListener.onFailure(t);
            }
        });

    }

    @Override
    public void getUserfeed(OnFinishedListener onFinishedListener, int page, String filter, int empid, String type) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        Log.e(TAG, "recentt" + recent);
        Call<FeedContent> call = apiInterface.getPost(page,filter,empid,type);
        call.enqueue(new Callback<FeedContent>() {
            @Override
            public void onResponse(Call<FeedContent> call, Response<FeedContent> response) {
                Log.e(TAG, "onResponse: " +  response.body());
                FeedContent feedContent = response.body();
                onFinishedListener.onFinished(response.body().getModelPostList(),feedContent);
            }

            @Override
            public void onFailure(Call<FeedContent> call, Throwable t) {
                Log.e(TAG, "faliure" +  t.getMessage());
                onFinishedListener.onFailure(t);
            }
        });
    }

    @Override
    public void settaggdata(OnFinishedListener onFinishedListener, Integer empidinterger, List<String> interestList, Context context) {
        Log.e(TAG, "onResponsesignupmodel34333546: " +   empidinterger);
        Log.e(TAG, "onResponsesignupmodel34333546: " +   interestList.toString());
        TagDetails tagDetails = new TagDetails();
        tagDetails.setEmpId(empidinterger);
        tagDetails.setTagglist(interestList);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<TagDetails> call = apiInterface.settagssdetails(tagDetails);
        call.enqueue(new Callback<TagDetails>() {
            @Override
            public void onResponse(Call<TagDetails> call, Response<TagDetails> response) {

                Log.e(TAG, "onResponsesignupmodelcheck: " +  response.body());
                onFinishedListener.onFinishedgettag(response.body(),context);


            }

            @Override
            public void onFailure(Call<TagDetails> call, Throwable t) {
                onFinishedListener.onFailure(t);
                Log.e(TAG, "onResponsesignupmodel: " +  t.getMessage());
            }
        });

    }

    public void gettagsdetails(OnFinishedListener onFinishedListener)
    {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<SpinnerDetails> call = apiInterface.gettagsdetails();
        call.enqueue(new Callback<SpinnerDetails>() {
            @Override
            public void onResponse(Call<SpinnerDetails> call, Response<SpinnerDetails> response) {

                Log.e(TAG, "onResponsesignupmodel3433: " +  response.body());
                onFinishedListener.onFinishedtag(response.body());


            }

            @Override
            public void onFailure(Call<SpinnerDetails> call, Throwable t) {
                onFinishedListener.onFailure(t);
                Log.e(TAG, "onResponsesignupmodel: " +  t.getMessage());
            }
        });

    }

}


