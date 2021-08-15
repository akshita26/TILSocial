package com.example.tilsocial.FeedDetail.model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class ModelPost {

    public ModelPost() {
    }
    @SerializedName("empId")
    private  String empId;

    @SerializedName("name")
    private String name;

    @SerializedName("likesCount")
    private String likesCount;

    @SerializedName("commentsCount")
    private String commentsCount;

    @SerializedName("hasLiked")
    private String hasLiked;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("updatedAt")
    private String updatedAt;

    @SerializedName("content")
    private String content;

    @SerializedName("tags")
    private String [] tags;

    @SerializedName("imgurl")
    private String imgurl;

    @SerializedName("postId")
    private  String postId;

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(String likesCount) {
        this.likesCount = likesCount;
    }

    public String getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(String commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getHasLiked() {
        return hasLiked;
    }

    public void setHasLiked(String hasLiked) {
        this.hasLiked = hasLiked;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "ModelPost{" +
                "empId='" + empId + '\'' +
                ", name='" + name + '\'' +
                ", likesCount='" + likesCount + '\'' +
                ", commentsCount='" + commentsCount + '\'' +
                ", hasLiked='" + hasLiked + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", content='" + content + '\'' +
                ", tags=" + Arrays.toString(tags) +
                ", imgurl='" + imgurl + '\'' +
                ", postId='" + postId + '\'' +
                '}';
    }
}
