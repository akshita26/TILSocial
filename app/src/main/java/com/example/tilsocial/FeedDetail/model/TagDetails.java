package com.example.tilsocial.FeedDetail.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TagDetails {

    @SerializedName("empId")
    Integer empId;
    @SerializedName("tags")
    List<String> tagglist;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public List<String> getTagglist() {
        return tagglist;
    }

    public void setTagglist(List<String> tagglist) {
        this.tagglist = tagglist;
    }

    @Override
    public String toString() {
        return "TagDetails{" +
                "empId=" + empId +
                ", tagglist=" + tagglist +
                '}';
    }
}
