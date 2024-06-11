package com.example.githubrepositorieslist.api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("/users/{userName}/repos")
    Call<ArrayList<Repo>> getAllRepos(@Path("userName") String userName);
}
