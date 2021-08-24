package com.example.tilsocial.signup.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Team {

    @SerializedName("team")
    String teamm;
    @SerializedName("departments")
    List<Departments> departmentsList;


    public String getTeamm() {
        return teamm;
    }

    public void setTeamm(String teamm) {
        this.teamm = teamm;
    }

    public List<Departments> getDepartmentsList() {
        return departmentsList;
    }

    public void setDepartmentsList(List<Departments> departmentsList) {
        this.departmentsList = departmentsList;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamm='" + teamm + '\'' +
                ", departmentsList=" + departmentsList +
                '}';
    }
}
