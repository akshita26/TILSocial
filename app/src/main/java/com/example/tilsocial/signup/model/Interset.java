package com.example.tilsocial.signup.model;

import com.google.gson.annotations.SerializedName;

public class Interset {

    @SerializedName("id")
    String id;
    @SerializedName("name")
    String name;
    @SerializedName("img")
    String imgurl;

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

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    @Override
    public String toString() {
        return "Interset{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", imgurl='" + imgurl + '\'' +
                '}';
    }
}
