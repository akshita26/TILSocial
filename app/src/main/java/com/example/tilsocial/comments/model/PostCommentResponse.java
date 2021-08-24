package com.example.tilsocial.comments.model;

import com.google.gson.annotations.SerializedName;

public class PostCommentResponse {
    @SerializedName("commentId")
    String commentId;
    @SerializedName("postId")
    String postId;
    @SerializedName("name")
    String name;
    @SerializedName("empId")
    Integer empId;
    @SerializedName("comment")
    String comment;
    @SerializedName("empImgUrl")
    String empImgUrl;
    @SerializedName("createdAt")
    String createdAt;
    @SerializedName("designation")
    String designation;

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEmpImgUrl() {
        return empImgUrl;
    }

    public void setEmpImgUrl(String empImgUrl) {
        this.empImgUrl = empImgUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "PostCommentResponse{" +
                "commentId='" + commentId + '\'' +
                ", postId='" + postId + '\'' +
                ", name='" + name + '\'' +
                ", empId=" + empId +
                ", comment='" + comment + '\'' +
                ", empImgUrl='" + empImgUrl + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", designation='" + designation + '\'' +
                '}';
    }
}
