package com.example.tilsocial.FeedDetail.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeedContent {

    @SerializedName("content")
    List<ModelPost> modelPostList;
    @SerializedName("pageable")
    PageDetails pageDetails;

    public List<ModelPost> getModelPostList() {
        return modelPostList;
    }

    public void setModelPostList(List<ModelPost> modelPostList) {
        this.modelPostList = modelPostList;
    }

    public PageDetails getPageDetails() {
        return pageDetails;
    }

    public void setPageDetails(PageDetails pageDetails) {
        this.pageDetails = pageDetails;
    }

    @Override
    public String toString() {
        return "FeedContent{" +
                "modelPostList=" + modelPostList +
                ", pageDetails=" + pageDetails +
                '}';
    }
}
