package com.example.tilsocial.signup.model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class SpinnerDetails {

    @SerializedName("team")
    String [] team;
    @SerializedName("interest")
    List<Interset> intersetList;
    @SerializedName("department")
    List<Department> departmentList;

    public String[] getTeam() {
        return team;
    }

    public void setTeam(String[] team) {
        this.team = team;
    }

    public List<Interset> getIntersetList() {
        return intersetList;
    }

    public void setIntersetList(List<Interset> intersetList) {
        this.intersetList = intersetList;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    @Override
    public String toString() {
        return "SpinnerDetails{" +
                "team=" + Arrays.toString(team) +
                ", intersetList=" + intersetList +
                ", departmentList=" + departmentList +
                '}';
    }
}
