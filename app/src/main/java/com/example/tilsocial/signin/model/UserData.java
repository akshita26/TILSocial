package com.example.tilsocial.signin.model;

import com.google.gson.annotations.SerializedName;

public class UserData {
    @SerializedName("empId")
    Integer empId;
    @SerializedName("name")
    String name;
    @SerializedName("dept")
    String dept;
    @SerializedName("bio")
    String bio;
    @SerializedName("team")
    String team;
    @SerializedName("designation")
    String designation;
//    @SerializedName("interests")
//    String interests;

    public Integer getEmpId() {
        return empId;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "empId=" + empId +
                ", name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", bio='" + bio + '\'' +
                ", team='" + team + '\'' +
                ", designation='" + designation + '\'' +
                '}';
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
//
//    public String getInterests() {
//        return interests;
//    }
//
//    public void setInterests(String interests) {
//        this.interests = interests;
//    }
}
