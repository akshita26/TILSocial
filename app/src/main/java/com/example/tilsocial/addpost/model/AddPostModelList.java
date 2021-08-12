
package com.example.tilsocial.addpost.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddPostModelList {

    @SerializedName("content")
    @Expose
    private String content;

    public String getContent() {
        return content;
    }


}
