package com.example.aaron.w3d3_githubapi;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aaron.w3d3_githubapi.model.GitHubRepoProfile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 8/16/17.
 */

public class RepoListAdaptor extends RecyclerView.Adapter<RepoListAdaptor.ViewHolder> {
    public RepoListAdaptor(List<GitHubRepoProfile> repoList) {
        this.repoList = repoList;
    }

    List<GitHubRepoProfile> repoList = new ArrayList<>();
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvCreated;
        TextView tvpushed;
        TextView tvLanguage;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView)itemView.findViewById(R.id.tvName);
            tvCreated = (TextView)itemView.findViewById(R.id.tvCreated);
            tvpushed = (TextView)itemView.findViewById(R.id.tvPushed);
            tvLanguage = (TextView)itemView.findViewById(R.id.tvLanguage);

        }
    }
    @Override
    public RepoListAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepoListAdaptor.ViewHolder holder, int position) {
        final GitHubRepoProfile repoObject = repoList.get(position);
        holder.tvCreated.setText(repoObject.getCreatedAt());
        holder.tvLanguage.setText(repoObject.getLanguage());
        holder.tvName.setText(repoObject.getName());
        holder.tvpushed.setText(repoObject.getPushedAt());
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }
}
