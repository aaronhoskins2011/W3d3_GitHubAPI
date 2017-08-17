package com.example.aaron.w3d3_githubapi;

import android.support.constraint.solver.SolverVariable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.aaron.w3d3_githubapi.model.GitHubRepoProfile;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RepoRecycleActivity extends AppCompatActivity {
    public static final String GITHUB_USER_URL = "https://api.github.com/users/aaronhoskins2011/repos";
    static String responseString = "";
    List<GitHubRepoProfile> gitHubRepoProfile;
    RecyclerView rvRepoList;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    RepoListAdaptor repoListAdaptor;
    //List<GitHubRepoProfile> repoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_recycle);


        rvRepoList = (RecyclerView)findViewById(R.id.rvRepoList);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        itemAnimator = new DefaultItemAnimator();
        rvRepoList.setLayoutManager(layoutManager);
        rvRepoList.setItemAnimator(itemAnimator);
        repoListAdaptor = new RepoListAdaptor(gitHubRepoProfile);

        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().url(GITHUB_USER_URL).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Type list = new TypeToken<List<GitHubRepoProfile>>(){}.getType();

                Gson gson = new Gson();
                responseString = response.body().string();

                gitHubRepoProfile = gson.fromJson(responseString, list);
                Log.d("ACTIVITY2", "onResponse: " + gitHubRepoProfile.toString());
                initRepoList();
            }
        });

    }

    private void initRepoList(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {


                //initRepoList();
                rvRepoList = (RecyclerView)findViewById(R.id.rvRepoList);
                layoutManager = new LinearLayoutManager(getApplicationContext());
                itemAnimator = new DefaultItemAnimator();
                rvRepoList.setLayoutManager(layoutManager);
                rvRepoList.setItemAnimator(itemAnimator);
                repoListAdaptor = new RepoListAdaptor(gitHubRepoProfile);
                rvRepoList.setAdapter(repoListAdaptor);
                repoListAdaptor.notifyDataSetChanged();
            }
        });

    }
}
