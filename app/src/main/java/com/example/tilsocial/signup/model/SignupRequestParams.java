package com.example.tilsocial.signup.model;

public class SignupRequestParams {


    String name,bio,department,team,designation;
    String[] Interset;
    Integer employeeid;

    public void setEmployeeid(Integer employeeid) {
        this.employeeid = employeeid;
    }

    public Integer getEmployeeid() {
        return employeeid;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

//    public String getEmployeeid() {
//        return employeeid;
//    }
//
//    public void setEmployeeid(String employeeid) {
//        this.employeeid = employeeid;
//    }

}
