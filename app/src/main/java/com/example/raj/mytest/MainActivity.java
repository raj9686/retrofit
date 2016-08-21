package com.example.raj.mytest;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.raj.mytest.Model.GitHubModel;
import com.example.raj.mytest.Model.GroupModel;
import com.example.raj.mytest.Model.Result;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView txtUserName, txtpassword;
    Button btnLogin, btnCancel;
    private RecyclerView recyclerView;
    private ArrayList<AndroidVersion> data;
    private ArrayList<Result> mData;
    private DataAdapter adapter;
    private View progressOverlay;

    public static void animateView(final View view, final int toVisibility, float toAlpha, int duration) {
        boolean show = toVisibility == View.VISIBLE;
        if (show) {
            view.setAlpha(0);
        }
        view.setVisibility(View.VISIBLE);
        view.animate()
                .setDuration(duration)
                .alpha(show ? toAlpha : 0)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(toVisibility);
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    private void initViews() {
        progressOverlay = findViewById(R.id.progress_overlay);
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateView(progressOverlay, View.VISIBLE, 0.4f, 200);
                gitHub();
            }
        });
    }

    public void inPathJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://services.groupkt.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<GroupModel> call = request.getCountryISO("IN");
        call.enqueue(new Callback<GroupModel>() {
            @Override
            public void onResponse(Call<GroupModel> call, Response<GroupModel> response) {
                GroupModel groupModel = response.body();
                mData = new ArrayList<>(Arrays.asList(groupModel.getRestResponse().getResult()));
                adapter = new DataAdapter(mData);
                recyclerView.setAdapter(adapter);
                Log.e("parametersJSON", "onResponse: " + call.request().url());
                animateView(progressOverlay, View.GONE, 0, 200);
            }

            @Override
            public void onFailure(Call<GroupModel> call, Throwable t) {
                Log.e("parametersJSON", "onResponse: " + call.request().body().toString());
            }
        });
    }

    private void sampleJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.learn2crack.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<AndroidModel> call = request.getJSON();
        call.enqueue(new Callback<AndroidModel>() {
            @Override
            public void onResponse(Call<AndroidModel> call, Response<AndroidModel> response) {
                AndroidModel androidModel = response.body();
/*                data = new ArrayList<>(Arrays.asList(jsonResponse.getAndroid()));
                adapter = new DataAdapter(data);*/
                Log.e("sampleJSON", "onResponse: " + call.request().url());
                recyclerView.setAdapter(adapter);
                animateView(progressOverlay, View.GONE, 0, 200);
            }
            @Override
            public void onFailure(Call<AndroidModel> call, Throwable t) {
                animateView(progressOverlay, View.GONE, 0, 200);
                Log.d("Error", t.getMessage());
            }
        });
    }

    public void parametersJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://services.groupkt.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<GroupModel> call = request.getCountry();
        call.enqueue(new Callback<GroupModel>() {
            @Override
            public void onResponse(Call<GroupModel> call, Response<GroupModel> response) {
                GroupModel groupModel = response.body();
                mData = new ArrayList<>(Arrays.asList(groupModel.getRestResponse().getResult()));
                adapter = new DataAdapter(mData);
                recyclerView.setAdapter(adapter);
                Log.e("parametersJSON", "onResponse: " + call.request().url());
                animateView(progressOverlay, View.GONE, 0, 200);
            }

            @Override
            public void onFailure(Call<GroupModel> call, Throwable t) {
                Log.e("parametersJSON", "onResponse: " + call.request().body().toString());
            }
        });
    }

    private void gitHub() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<GitHubModel> call = request.getGit("raj9686");
        call.enqueue(new Callback<GitHubModel>() {
            @Override
            public void onResponse(Call<GitHubModel> call, Response<GitHubModel> response) {
                GitHubModel gitHubModel = response.body();
/*                data = new ArrayList<>(Arrays.asList(jsonResponse.getAndroid()));
                adapter = new DataAdapter(data);*/
//                recyclerView.setAdapter(adapter);
                animateView(progressOverlay, View.GONE, 0, 200);
                Log.e("sampleJSON", "onResponse: " + call.request().url());
            }

            @Override
            public void onFailure(Call<GitHubModel> call, Throwable t) {
                animateView(progressOverlay, View.GONE, 0, 200);
                Log.d("Error", t.getMessage());
            }
        });
    }
}

