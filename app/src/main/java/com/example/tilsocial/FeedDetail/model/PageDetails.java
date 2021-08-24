package com.example.tilsocial.FeedDetail.model;

import com.google.gson.annotations.SerializedName;

public class PageDetails {

    @SerializedName("pageNumber")
    Integer pageNumber;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }
}
