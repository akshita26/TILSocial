package com.example.tilsocial.likes.model;

public class PostLike {
    String postId;
//    Boolean hasLiked;
    Integer empId;
//    private Integer likesCount;

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
    //    public Boolean getHasLiked() {
//        return hasLiked;
//    }

//    public void setHasLiked(Boolean hasLiked) {
//        this.hasLiked = hasLiked;
//    }


//    public Integer getLikesCount() {
//        return likesCount;
//    }

//    public void setLikesCount(Integer likesCount) {
//        this.likesCount = likesCount;
//    }

    @Override
    public String toString() {
        return "PostLike{" +
                "postId='" + postId + '\'' +
//                ", hasLiked='" + hasLiked + '\'' +
//                ", likesCount='" + likesCount + '\'' +
                ", empId=" + empId +
                '}';
    }
}
