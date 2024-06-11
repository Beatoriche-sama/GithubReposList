package com.example.githubrepositorieslist.api;

import com.google.gson.annotations.SerializedName;

public class Repo {
    @SerializedName("name")
    public String name;
    @SerializedName("description")
    public String description;
}
