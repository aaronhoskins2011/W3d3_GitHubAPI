package com.example.aaron.w3d3_githubapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.aaron.w3d3_githubapi.model.GitHubProfile;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    static GitHubProfile gitHubProfile;
    public static final String GITHUB_USER_URL = "https://api.github.com/users/aaronhoskins2011";
    static String responseString = "";
    TextView tvName;
    TextView tvLogin;
    TextView tvId;
    TextView tvLocation;
    TextView tvNumOfRepos;
    TextView tvUrl;
    TextView tvCreated;
    TextView tvUpdated;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().url(GITHUB_USER_URL).build();
        tvCreated = (TextView)findViewById(R.id.tvCreated);
        tvId = (TextView)findViewById(R.id.tvId);
        tvLocation = (TextView)findViewById(R.id.tvLocation);
        tvLogin = (TextView)findViewById(R.id.tvLogin);
        tvName = (TextView)findViewById(R.id.tvName);
        tvNumOfRepos = (TextView)findViewById(R.id.tvNumOfRepos);
        tvUpdated = (TextView)findViewById(R.id.tvUpdated);
        tvUrl = (TextView)findViewById(R.id.tvUrl);
        try {



            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    responseString = response.body().string();
                    Gson gson = new Gson();
                    gitHubProfile = gson.fromJson(responseString,GitHubProfile.class);

                    Log.d("TAG", "onResponse: gitHubProfileLoaded for " + gitHubProfile.getName());


                    initTextVeiws();

                }
            });

        } catch(Exception e){
            e.printStackTrace();
        }
      //  while (gitHubProfile == null){}




    }

    public void initTextVeiws(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                    tvName.setText(gitHubProfile.getName());
                    tvUpdated.setText(gitHubProfile.getUpdatedAt());
                    tvUrl.setText(gitHubProfile.getUrl());
                    tvNumOfRepos.setText(gitHubProfile.getPublicRepos().toString());
                    tvLogin.setText(gitHubProfile.getLogin());
                    tvCreated.setText(gitHubProfile.getUpdatedAt());
                    tvLocation.setText(gitHubProfile.getLocation());
                    tvId.setText(gitHubProfile.getId().toString());
            }
        });
    }


    public void getRepoInfo(View view) {
        Intent intent = new Intent(this,RepoRecycleActivity.class);
        startActivity(intent);
    }
}
