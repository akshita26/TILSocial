package com.example.tilsocial.signup.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpinnerDetails {

   @SerializedName("tags")
    List<String> tagslist;
   @SerializedName("teams")
    List<Team> teamslist;

    public List<String> getTagslist() {
        return tagslist;
    }

    public void setTagslist(List<String> tagslist) {
        this.tagslist = tagslist;
    }

    public List<Team> getTeamslist() {
        return teamslist;
    }

    public void setTeamslist(List<Team> teamslist) {
        this.teamslist = teamslist;
    }

    @Override
    public String toString() {
        return "SpinnerDetails{" +
                "tagslist=" + tagslist +
                ", teamslist=" + teamslist +
                '}';
    }
}
