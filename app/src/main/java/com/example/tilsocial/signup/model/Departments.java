package com.example.tilsocial.signup.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Departments {

    @SerializedName("name")
    String name;
    @SerializedName("designations")
    List<String> designationslist;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDesignationslist() {
        return designationslist;
    }

    public void setDesignationslist(List<String> designationslist) {
        this.designationslist = designationslist;
    }

    @Override
    public String toString() {
        return "Departments{" +
                "name='" + name + '\'' +
                ", designationslist=" + designationslist +
                '}';
    }
}
