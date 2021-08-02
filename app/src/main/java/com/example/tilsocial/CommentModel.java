package com.example.tilsocial;

public class CommentModel {

    String comment;
    String userimage;
    String username;
    String time;

    public CommentModel()
    {

    }

    public CommentModel(String comment, String userimage, String username, String time) {
        this.comment = comment;
        this.userimage = userimage;
        this.username = username;
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
