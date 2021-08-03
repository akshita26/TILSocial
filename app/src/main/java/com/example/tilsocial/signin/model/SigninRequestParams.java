package com.example.tilsocial.signin.model;

import com.google.gson.annotations.SerializedName;

public class SigninRequestParams {
    @SerializedName("empId")
    String empId;

    public String getEmployeeid() {
        return empId;
    }

    public void setEmployeeid(String empId) {
        this.empId = empId;
    }
}
