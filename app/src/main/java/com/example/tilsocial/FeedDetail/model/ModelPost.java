package com.example.tilsocial.FeedDetail.model;

public class ModelPost {

    public ModelPost() {
    }

    String description;
    String name;
    String uimage;
    String ulike;
    String utime;
    String ucomment;
    String utitle;


    public ModelPost(String description, String name, String uimage, String ulike, String utime, String ucomment, String utitle) {
        this.description = description;
        this.name = name;
        this.uimage = uimage;
        this.ulike = ulike;
        this.utime = utime;
        this.ucomment = ucomment;
        this.utitle = utitle;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getUimage() {
        return uimage;
    }

    public String getUlike() {
        return ulike;
    }

    public String getUtime() {
        return utime;
    }

    public String getUcomment() {
        return ucomment;
    }

    public String getUtitle() {
        return utitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUimage(String uimage) {
        this.uimage = uimage;
    }

    public void setUlike(String ulike) {
        this.ulike = ulike;
    }

    public void setUtime(String utime) {
        this.utime = utime;
    }

    public void setUcomment(String ucomment) {
        this.ucomment = ucomment;
    }

    public void setUtitle(String utitle) {
        this.utitle = utitle;
    }
}
