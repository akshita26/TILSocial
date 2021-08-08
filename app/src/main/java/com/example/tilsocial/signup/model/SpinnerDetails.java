package com.example.tilsocial.signup.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SpinnerDetails {

    @SerializedName("departments")
    ArrayList<String> department;

    @SerializedName("teams")
    ArrayList<String> team;

    @SerializedName("designations")
    ArrayList<String> designation;

    public ArrayList<String> getDepartment() {
        return department;
    }

    public void setDepartment(ArrayList<String> department) {
        this.department = department;
    }

    public ArrayList<String> getTeam() {
        return team;
    }

    public void setTeam(ArrayList<String> team) {
        this.team = team;
    }

    public ArrayList<String> getDesignation() {
        return designation;
    }

    public void setDesignation(ArrayList<String> designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "SpinnerDetails{" +
                "department=" + department +
                ", team=" + team +
                ", designation=" + designation +
                '}';
    }
}
