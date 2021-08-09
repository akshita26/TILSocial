package com.example.tilsocial.FeedDetail.model;

import com.google.gson.annotations.SerializedName;

public class ModelPost {

    public ModelPost() {
    }
    @SerializedName("id")
    private  String id;


    @SerializedName("name")
    private String name;

    @SerializedName("content")
    private String description;

    @SerializedName("empImgUrl")
    private String uimage;

    @SerializedName("likesCount")
    private String ulike;

    @SerializedName("commentsCount")
    private String ucomment;

    @SerializedName("createdAt")
    private String utime;

    @Override
    public String toString() {
        return "ModelPost{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", uimage='" + uimage + '\'' +
                ", ulike='" + ulike + '\'' +
                ", ucomment='" + ucomment + '\'' +
                ", utime='" + utime + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUimage() {
        return uimage;
    }

    public void setUimage(String uimage) {
        this.uimage = uimage;
    }

    public String getUlike() {
        return ulike;
    }

    public void setUlike(String ulike) {
        this.ulike = ulike;
    }

    public String getUcomment() {
        return ucomment;
    }

    public void setUcomment(String ucomment) {
        this.ucomment = ucomment;
    }

    public String getUtime() {
        return utime;
    }

    public void setUtime(String utime) {
        this.utime = utime;
    }
}
