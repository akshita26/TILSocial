package com.example.tilsocial.addpost.model;

import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tilsocial.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPostModel {
//    String title;
    String desc;
    List<String> imageList,hashtagList;
    public  void doPost(AddPostRequestParams addPostRequestParams) throws JSONException {
        JSONObject request_body = new JSONObject();
//        title=addPostRequestParams.getTitle();
        desc=addPostRequestParams.getDescription();
        imageList=addPostRequestParams.getImage();
        hashtagList=addPostRequestParams.getHashtag();
        request_body.put("id","1002");
        request_body.put("empId",123);
        request_body.put("name","Sourav");
        request_body.put("empImgUrl","tilsocialapp/media/fetch?empId=234");
        request_body.put("likesCount","5");
        request_body.put("commentsCount","4");
        request_body.put("hasLiked",true);
        request_body.put("createdAt","2021-07-21 18:07:00");
        request_body.put("updatedAt","2021-07-21 18:07:00");
        request_body.put("images",imageList);
        request_body.put("videos","");
        request_body.put("content",desc);
        request_body.put("tags",hashtagList);

        //retrofit call
        Call<AddPostModelList> addPostModelList=AddPostAPI.getService().getPostList(request_body.toString());
        addPostModelList.enqueue(new Callback<AddPostModelList>() {

            @Override
            public void onResponse(Call<AddPostModelList> call, Response<AddPostModelList> response) {
                AddPostModelList list=response.body();
                if (list!=null){
                    Log.e("Response", "My Response-: " + response.body().getMsg() );
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
