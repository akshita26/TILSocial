package com.example.tilsocial.addpost.model;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class AddPostAPI {
    private static final String url="https://demo1192777.mockable.io/";
    public static PostService postService=null;
    public static PostService getService(){
        if(postService==null){
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            postService=retrofit.create(PostService.class);
        }
        return postService;
    }

    public interface PostService{
        @POST("addpost")
        Call<AddPostModelList> getPostList(@Body String body);
    }
}
