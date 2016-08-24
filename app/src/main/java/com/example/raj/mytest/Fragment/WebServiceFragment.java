package com.example.raj.mytest.Fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.raj.mytest.Adapter.AndroidVersionAdapter;
import com.example.raj.mytest.Adapter.DataAdapter;
import com.example.raj.mytest.Interface.RequestInterface;
import com.example.raj.mytest.Model.AndroidModel;
import com.example.raj.mytest.Model.AndroidVersion;
import com.example.raj.mytest.Model.GitHubModel;
import com.example.raj.mytest.Model.GroupModel;
import com.example.raj.mytest.Model.Result;
import com.example.raj.mytest.R;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Raj on 8/23/2016.
 */
public class WebServiceFragment extends android.support.v4.app.Fragment {

    private View progressOverlay;
    private RecyclerView recyclerView;
    private ArrayList<Result> mData;
    private DataAdapter adapter;
    private ArrayList<AndroidVersion> data;
    private Activity activity;
    private SwipeRefreshLayout mySwipeRefreshLayout;

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

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_web_service, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        setListner();
        prepareView();
    }

    private void prepareView() {
        animateView(progressOverlay, View.VISIBLE, 0, 200);
        sampleJSON();
    }

    private void initViews() {
        progressOverlay = getView().findViewById(R.id.progress_overlay);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView) getView().findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        mySwipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swiperefresh);
    }

    private void setListner() {
        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        mySwipeRefreshLayout.setRefreshing(false);
                        Log.i("Rajesh", "onRefresh called from SwipeRefreshLayout");
                        sampleJSON();

                    }
                }
        );
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
                data = new ArrayList<>(Arrays.asList(androidModel.getAndroid()));
                AndroidVersionAdapter androidVersionAdapter = new AndroidVersionAdapter(data);
                Log.e("sampleJSON", "onResponse: " + call.request().url());
                recyclerView.setAdapter(androidVersionAdapter);
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        animateView(progressOverlay, View.GONE, 0, 200);
                    }
                });

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
