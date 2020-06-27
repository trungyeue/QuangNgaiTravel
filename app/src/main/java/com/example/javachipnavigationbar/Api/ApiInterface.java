package com.example.javachipnavigationbar.Api;

import com.example.javachipnavigationbar.Model.Slide;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("File1.php")
   Call<List<Slide>>getJson();
}
