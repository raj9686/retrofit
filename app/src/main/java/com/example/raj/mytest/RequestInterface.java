package com.example.raj.mytest;

import com.example.raj.mytest.Model.GitHubModel;
import com.example.raj.mytest.Model.GroupModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Raj on 8/18/2016.
 */
public interface RequestInterface {

    @GET("android/jsonandroid")
    Call<AndroidModel> getJSON();

    @GET("/citiesJSON")
    Call<GeoModel> getJSONwithPara(@Query("north") String north,
                                   @Query("south") String south,
                                   @Query("east") String east,
                                   @Query("west") String west,
                                   @Query("lang") String lang,
                                   @Query("username") String demo);


    @GET("country/get/all")
    Call<GroupModel> getCountry();

    @GET("country/get/iso2code/{alpha2_code}")
    Call<GroupModel> getCountryISO(@Path("alpha2_code") String a);

    @GET("/users/{user}")
    Call<GitHubModel> getGit(@Path("user") String a);



}
