package com.example.githubrepositorieslist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.githubrepositorieslist.api.Repo;

import java.util.ArrayList;

public class SharedModel extends AndroidViewModel {
    private final MutableLiveData<ArrayList<Repo>> currentRepos;
    public SharedModel(@NonNull Application application) {
        super(application);
        this.currentRepos = new MutableLiveData<>(new ArrayList<>());
    }

    public void clearAll(){
        currentRepos.setValue(null);
    }


    public void setCurrentRepos(ArrayList<Repo> currentRepos){
        this.currentRepos.setValue(currentRepos);
    }

    public MutableLiveData<ArrayList<Repo>> getCurrentRepos() {
        return currentRepos;
    }
}
