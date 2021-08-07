package com.example.tilsocial.signup.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;

public class SpinnerDetails {

    @SerializedName("departments")
    ArrayList department;

    @SerializedName("teams")
    ArrayList team;

    @SerializedName("designations")
    ArrayList[] designation;

    public ArrayList getDepartment() {
        return department;
    }

    public void setDepartment(ArrayList department) {
        this.department = department;
    }

    public ArrayList getTeam() {
        return team;
    }

    public void setTeam(ArrayList team) {
        this.team = team;
    }

    public ArrayList[] getDesignation() {
        return designation;
    }

    public void setDesignation(ArrayList[] designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "SpinnerDetails{" +
                "department=" + department +
                ", team=" + team +
                ", designation=" + Arrays.toString(designation) +
                '}';
    }
}
