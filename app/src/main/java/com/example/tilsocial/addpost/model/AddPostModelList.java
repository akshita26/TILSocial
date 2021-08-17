
package com.example.tilsocial.addpost.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddPostModelList {

    @SerializedName("postId")
    @Expose
    private String postId;
    @SerializedName("empId")
    @Expose
    private Integer empId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("likesCount")
    @Expose
    private Integer likesCount;
    @SerializedName("commentsCount")
    @Expose
    private Integer commentsCount;
    @SerializedName("hasLiked")
    @Expose
    private Boolean hasLiked;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("empImgUrl")
    @Expose
    private String empImgUrl;
    @SerializedName("tags")
    @Expose
    private String[] tags = null;
    @SerializedName("images")
    @Expose
    private List<String> images = null;
    @SerializedName("videos")
    @Expose
    private List<String> videos = null;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
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

    public String getEmpImgUrl() {
        return empImgUrl;
    }

    public void setEmpImgUrl(String empImgUrl) {
        this.empImgUrl = empImgUrl;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }

    public String Details(){
        return "AddPost{" +
                "empId='" + empId + '\'' +
                "postId='" + postId + '\'' +
                ", name='" + name + '\'' +
                ", likesCount='" + likesCount + '\'' +
                ", designation='" + designation + '\'' +
                ", commentsCount='" + commentsCount + '\'' +
                ", hasLiked='" + hasLiked + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", content='" + content + '\'' +
                ", images='" + images + '\'' +
                ", videos='" + videos + '\'' +
                ", tags=" + tags +
                '}';
    }


}
