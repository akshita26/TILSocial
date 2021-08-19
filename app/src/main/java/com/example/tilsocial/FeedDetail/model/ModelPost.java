package com.example.tilsocial.FeedDetail.model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

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
    private Boolean hasLiked;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("updatedAt")
    private String updatedAt;

    @SerializedName("content")
    private String content;

    @SerializedName("tags")
    private String [] tags;
    @SerializedName("images")
    private List<String> images;

    @SerializedName("postId")
    private String postId;

    @SerializedName("empImgUrl")
    private String empImgUrl;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
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

    public Boolean getHasLiked() {
        return hasLiked;
    }

    public void setHasLiked(Boolean hasLiked) {
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

    public String getEmpImgUrl() {
        return empImgUrl;
    }

    public void setEmpImgUrl(String empImgUrl) {
        this.empImgUrl = empImgUrl;
    }

    @Override
    public String toString() {
        return "ModelPost{" +
                "empId='" + empId + '\'' +
                ", name='" + name + '\'' +
                ", likesCount='" + likesCount + '\'' +
                ", commentsCount='" + commentsCount + '\'' +
                ", hasLiked=" + hasLiked +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", content='" + content + '\'' +
                ", tags=" + Arrays.toString(tags) +
                ", images=" + images +
                ", postId='" + postId + '\'' +
                ", empImgUrl='" + empImgUrl + '\'' +
                '}';
    }
}
