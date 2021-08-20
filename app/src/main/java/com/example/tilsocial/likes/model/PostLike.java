package com.example.tilsocial.likes.model;

public class PostLike {
    String postId;
    Boolean hasLiked;
    String empId;
    private Integer likesCount;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Boolean getHasLiked() {
        return hasLiked;
    }

    public void setHasLiked(Boolean hasLiked) {
        this.hasLiked = hasLiked;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }
    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    @Override
    public String toString() {
        return "PostLike{" +
                "postId='" + postId + '\'' +
                ", hasLiked='" + hasLiked + '\'' +
                ", likesCount='" + likesCount + '\'' +
                ", empId=" + empId +
                '}';
    }
}
