package com.example.javachipnavigationbar.Group;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.javachipnavigationbar.Adapter.SlidePageAdapter;
import com.example.javachipnavigationbar.Api.ApiInterface;
import com.example.javachipnavigationbar.Model.Slide;
import com.example.javachipnavigationbar.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DuLich extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageButton back;
    ArrayList<Slide> arrayList = new ArrayList<>();
    private SlidePageAdapter slidePageAdapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_du_lich);
        // Ánh Xạ
        recyclerView = findViewById(R.id.recView);
        progressBar = findViewById(R.id.progress);
        back = findViewById(R.id.back);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        arrayList = new ArrayList<>();
        progressBar.setVisibility(View.VISIBLE);
        //
        getSlideJson();

    }

    private void getSlideJson() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sanphambanhang.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<Slide>> call = apiInterface.getJson();
        call.enqueue(new Callback<List<Slide>>() {
            @Override
            public void onResponse(Call<List<Slide>> call, Response<List<Slide>> response) {
                progressBar.setVisibility(View.GONE);
                assert response.body() != null;
                arrayList = new ArrayList<Slide>(response.body());
                slidePageAdapter = new SlidePageAdapter(DuLich.this, arrayList);
                recyclerView.setAdapter(slidePageAdapter);
            }

            @Override
            public void onFailure(Call<List<Slide>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(DuLich.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
//        if (retrofit==null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
    }

    public void back(View view) {
        finish();
    }
}
