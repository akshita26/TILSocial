package com.example.tilsocial.signup.model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class SpinnerRequestParams {

    @SerializedName("departments")
    String [] department;

    @SerializedName("teams")
    String [] team;

    @SerializedName("designations")
    String [] designation;

    public String[] getDepartment() {
        return department;
    }

    public void setDepartment(String[] department) {
        this.department = department;
    }

    public String[] getTeam() {
        return team;
    }

    public void setTeam(String[] team) {
        this.team = team;
    }

    public String[] getDesignation() {
        return designation;
    }

    public void setDesignation(String[] designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "SpinnerRequestParams{" +
                "department=" + Arrays.toString(department) +
                ", team=" + Arrays.toString(team) +
                ", designation=" + Arrays.toString(designation) +
                '}';
    }
}
