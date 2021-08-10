package com.example.tilsocial.signin.model;

import com.google.gson.annotations.SerializedName;

public class SigninRequestParams {

    String emplId;

    public String getEmployeeid() {
        return emplId;
    }

    public void setEmployeeid(String empId) {
        this.emplId = empId;
    }
}
