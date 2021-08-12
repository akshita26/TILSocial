package com.example.tilsocial.signup.model;

import com.google.gson.annotations.SerializedName;

public class SignupRequestParams {

    @SerializedName("name")
    String name;
    @SerializedName("bio")
    String bio;
    @SerializedName("dept")
    String dept;
    @SerializedName("team")
    String team;
    @SerializedName("designation")
    String designation;
    @SerializedName("Interset")
    String[] Interset;
    @SerializedName("empId")
    Integer empId;

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String[] getInterset() {
        return Interset;
    }

    public void setInterset(String[] interset) {
        Interset = interset;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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



}
