package com.example.tilsocial.signin.model;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {
    @SerializedName("error")
    String error;
    @SerializedName("message")
    String message;
    @SerializedName("status")
    String status;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

