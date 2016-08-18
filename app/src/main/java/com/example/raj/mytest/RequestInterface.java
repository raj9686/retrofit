package com.example.raj.mytest;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Raj on 8/18/2016.
 */
public interface RequestInterface {
    @GET("android/jsonandroid")
    Call<JSONResponse> getJSON();
}
