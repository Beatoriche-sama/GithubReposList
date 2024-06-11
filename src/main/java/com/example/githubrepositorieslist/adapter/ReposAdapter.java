package com.example.githubrepositorieslist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.githubrepositorieslist.R;
import com.example.githubrepositorieslist.api.Repo;

import java.util.ArrayList;

public class ReposAdapter extends ArrayAdapter<Repo> {
    private ArrayList<Repo> repos;

    public ReposAdapter(@NonNull Context context, int resource,
                        ArrayList<Repo> repos) {
        super(context, resource);
        this.repos = repos;
    }

    public void setRepos(ArrayList<Repo> repos) {
        this.repos = repos;
        clear();
        if (repos != null) addAll(repos);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            row = inflater.inflate(R.layout.repos_listview, parent,
                    false);
        } else {
            row = convertView;
        }
        Repo repo = repos.get(position);
        TextView name = row.findViewById(R.id.repo_name);
        name.setText(repo.name);
        TextView description = row.findViewById(R.id.repo_description);
        description.setText(repo.description);

        return row;
    }
}
