package com.example.githubrepositorieslist.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserRepos {
    @SerializedName("info")
    public ArrayList<UserRepos> info;
}
