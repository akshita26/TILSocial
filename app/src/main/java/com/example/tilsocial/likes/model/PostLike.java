package com.example.tilsocial.likes.model;

public class PostLike {
    String postId;
    Boolean hasLiked;
    String empId;

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

    @Override
    public String toString() {
        return "PostComment{" +
                "postId='" + postId + '\'' +
                ", hasLiked='" + hasLiked + '\'' +
                ", empId=" + empId +
                '}';
    }
}
