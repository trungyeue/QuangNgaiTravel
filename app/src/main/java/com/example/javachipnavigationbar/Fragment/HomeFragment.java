package com.example.javachipnavigationbar.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.javachipnavigationbar.Adapter.SlidePageAdapter1;
import com.example.javachipnavigationbar.Adapter.SliderAdapterExample;
import com.example.javachipnavigationbar.Api.ApiInterface;
import com.example.javachipnavigationbar.Group.DuLich;
import com.example.javachipnavigationbar.Model.Slide;
import com.example.javachipnavigationbar.Page.LoginPage;
import com.example.javachipnavigationbar.R;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.javachipnavigationbar.Page.LoginPage.userId;
import static com.facebook.FacebookSdk.getApplicationContext;


public class HomeFragment extends Fragment {
    private SliderView sliderView;
    TextView onclick;
    SearchView searchView;

    RecyclerView recyclerView;
    ArrayList<Slide> arrayList = new ArrayList<>();
    private SlidePageAdapter1 slidePageAdapter;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        sliderView = root.findViewById(R.id.imageSlider);
        onclick = root.findViewById(R.id.onclick);
        progressBar = root.findViewById(R.id.progress);
        recyclerView = root.findViewById(R.id.recyclerView);
        sharedPreferences = getContext().getSharedPreferences("PrefsFile", Context.MODE_PRIVATE);
        String url = "http://sanphambanhang.000webhostapp.com/Name.php";
        PostData(url);
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
            public void onResponse(Call<List<Slide>> call, retrofit2.Response<List<Slide>> response) {
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
    private void PostData(final String url) {
        final RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(final String response) {
                Toast.makeText(getContext(), ""+response, Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(),sharedPreferences.getString("userId",userId) ,Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("giohang");
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject explrObject = jsonArray.getJSONObject(i);
                        onclick.setText(explrObject.getString("user_name"));
//                        emaidn.setText(explrObject.getString("email"));
//                        pass = explrObject.getString("password");
//                        diachigiaohang.setText(explrObject.getString("diachi" ) + explrObject.getString("tinh" ) + explrObject.getString("phuongxa" ) +
//                                explrObject.getString("quanhuyen" ) + explrObject.getString("sdt" ));
//                        Toast.makeText(CAPNHATTHONGTIN.this, ""+preferences.getString("userId",userId), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { // lau vc // log zo di t xem thu// chòe xíu, chừ lấy từng cái ra phải hân thì listview á
                Log.d("vvvv", "onErrorResponse: " + error); // mở phai php t xem thửu ok
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("user_id",sharedPreferences.getString("userId",userId));
                return params;
            }
        };
        requestQueue.add(stringRequest); // cái ni để làm chi qên r


    }
}