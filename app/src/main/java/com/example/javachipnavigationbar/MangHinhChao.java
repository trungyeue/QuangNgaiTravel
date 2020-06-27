package com.example.javachipnavigationbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.javachipnavigationbar.Adapter.SlidePageAdapter;
import com.example.javachipnavigationbar.Api.ApiInterface;
import com.example.javachipnavigationbar.Group.DuLich;
import com.example.javachipnavigationbar.Model.Slide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MangHinhChao extends AppCompatActivity {
    Button chuyen;
    ArrayList<Slide> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mang_hinh_chao);
        /// ánh xạ
        chuyen=findViewById(R.id.chuyen);
        chuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MangHinhChao.this,MainActivity.class));
            }
        });
        //getSlideJson();

    }
    private void getSlideJson() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://sanphambanhang.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);
        Call<List<Slide>> call=apiInterface.getJson();
        call.enqueue(new Callback<List<Slide>>() {
            @Override
            public void onResponse(Call<List<Slide>> call, Response<List<Slide>> response) {
                assert response.body() != null;
                arrayList=new ArrayList<Slide>(response.body());

            }

            @Override
            public void onFailure(Call<List<Slide>> call, Throwable t) {
                Toast.makeText(MangHinhChao.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });}
}
