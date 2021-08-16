package com.example.tilsocial.comments.model;

public class CommentModel {

    String commentId;
    String postId;
    String name;
    Integer empId;
    String comment;
    String createdAt;

    public CommentModel()
    {

    }

    public CommentModel(String commentId, String postId, String name, Integer empId, String comment, String createdAt) {
        this.commentId = commentId;
        this.postId = postId;
        this.name = name;
        this.empId = empId;
        this.comment = comment;
        this.createdAt = createdAt;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "CommentModel{" +
                "commentId='" + commentId + '\'' +
                ", postId='" + postId + '\'' +
                ", name='" + name + '\'' +
                ", empId=" + empId +
                ", comment='" + comment + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}