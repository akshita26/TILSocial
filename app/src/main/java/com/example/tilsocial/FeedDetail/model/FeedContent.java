package com.example.tilsocial.FeedDetail.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeedContent {

    @SerializedName("content")
    List<ModelPost> modelPostList;

    public List<ModelPost> getModelPostList() {
        return modelPostList;
    }

    public void setModelPostList(List<ModelPost> modelPostList) {
        this.modelPostList = modelPostList;
    }

    @Override
    public String toString() {
        return "FeedContent{" +
                "modelPostList=" + modelPostList +
                '}';
    }
}
