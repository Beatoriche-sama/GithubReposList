package com.example.githubrepositorieslist.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.githubrepositorieslist.R;
import com.example.githubrepositorieslist.SharedModel;
import com.example.githubrepositorieslist.adapter.ReposAdapter;
import com.example.githubrepositorieslist.api.ApiClient;
import com.example.githubrepositorieslist.api.ApiInterface;
import com.example.githubrepositorieslist.api.Repo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private SharedModel sharedModel;

    public static void main(String[] args) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = getApplicationContext();
        setContentView(R.layout.main_layout);

        ListView listView = findViewById(R.id.repos_listview);
        ReposAdapter adapter = new ReposAdapter(context,
                R.layout.repos_listview, new ArrayList<>());
        listView.setAdapter(adapter);

        sharedModel = new ViewModelProvider(this).get(SharedModel.class);
        sharedModel.getCurrentRepos().observe(this, adapter::setRepos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                sharedModel.clearAll();
                search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    private void search(String query) {
        Retrofit apiClient = ApiClient.getClient();
        ApiInterface apiInterface = apiClient.create(ApiInterface.class);

        Call<ArrayList<Repo>> repoInfoCall = apiInterface
                .getAllRepos(query);
        repoInfoCall.enqueue(new Callback<ArrayList<Repo>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Repo>> call,
                                   @NonNull Response<ArrayList<Repo>> response) {
                ArrayList<Repo> userRepos = response.body();
                assert userRepos != null;
                sharedModel.setCurrentRepos(userRepos);
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Repo>> call,
                                  @NonNull Throwable throwable) {
            }
        });
    }
}
