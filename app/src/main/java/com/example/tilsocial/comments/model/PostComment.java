package com.example.tilsocial.comments.model;

public class PostComment {
    String postId;
    String comment;
    Integer empId;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    @Override
    public String toString() {
        return "PostComment{" +
                "postId='" + postId + '\'' +
                ", comment='" + comment + '\'' +
                ", empId=" + empId +
                '}';
    }
}
