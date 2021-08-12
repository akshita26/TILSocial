package com.example.tilsocial.signup.model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class Department {

    @SerializedName("id")
    String id;
    @SerializedName("name")
    String name;
    @SerializedName("designation")
    String [] designation;

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

    public String[] getDesignation() {
        return designation;
    }

    public void setDesignation(String[] designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", designation=" + Arrays.toString(designation) +
                '}';
    }
}
