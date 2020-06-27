package com.example.javachipnavigationbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javachipnavigationbar.Adapter.SlidePageAdapter;
import com.example.javachipnavigationbar.Adapter.SlidePageAdapter1;
import com.example.javachipnavigationbar.Adapter.SliderAdapterExample;
import com.example.javachipnavigationbar.Api.ApiInterface;
import com.example.javachipnavigationbar.Group.DuLich;
import com.example.javachipnavigationbar.Model.Slide;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomFragment extends Fragment {
    private SliderView sliderView;
    TextView onclick;
    SearchView searchView;

    RecyclerView recyclerView;
    ArrayList<Slide> arrayList = new ArrayList<>();
    private SlidePageAdapter1 slidePageAdapter;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_hom, container, false);
        //Ánh Xạ
        sliderView = root.findViewById(R.id.imageSlider);
        onclick = root.findViewById(R.id.onclick);
        progressBar = root.findViewById(R.id.progress);
        recyclerView = root.findViewById(R.id.recyclerView);
        // slide View
        final SliderAdapterExample adapter = new SliderAdapterExample(getContext());
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();
        sliderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  startActivity(new Intent(getContext(), ThongtinTk.class));
            }
        });

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.setCurrentPagePosition(position);
            }
        });
        //On Click
        onclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), DuLich.class));
            }
        });
        //Picasso
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        getSlideJson();
        onclick.setVisibility(View.GONE);
        sliderView.setVisibility(View.GONE);
        return root;

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
                onclick.setVisibility(View.VISIBLE);
                sliderView.setVisibility(View.VISIBLE);
                assert response.body() != null;
                arrayList = new ArrayList<Slide>(response.body());
                slidePageAdapter = new SlidePageAdapter1(getContext(), arrayList);
                recyclerView.setAdapter(slidePageAdapter);

            }

            @Override
            public void onFailure(Call<List<Slide>> call, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
//    @SuppressLint("StaticFieldLeak")
//    public static class AsyncTaskLoadImage1 extends AsyncTask<String, String, Bitmap> {
//        private final static String TAG = "AsyncTaskLoadImage";
//        private ImageView imageView;
//
//        AsyncTaskLoadImage1(ImageView imageView) {
//            this.imageView = imageView;
//        }
//
//        @Override
//        protected Bitmap doInBackground(String... params) {
//            Bitmap bitmap = null;
//            try {
//                URL url = new URL(params[0]);
//                bitmap = BitmapFactory.decodeStream((InputStream) url.getContent());
//            } catch (IOException e) {
//                Log.e(TAG, Objects.requireNonNull(e.getMessage()));
//            }
//            return bitmap;
//        }
//
//        @Override
//        protected void onPostExecute(Bitmap bitmap) {
//            imageView.setImageBitmap(bitmap);
//        }
//    }
}
