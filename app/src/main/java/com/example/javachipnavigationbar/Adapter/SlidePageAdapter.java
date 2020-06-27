package com.example.javachipnavigationbar.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javachipnavigationbar.Api.ApiInterface;
import com.example.javachipnavigationbar.Group.ChiTiet;
import com.example.javachipnavigationbar.Model.Slide;
import com.example.javachipnavigationbar.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SlidePageAdapter extends RecyclerView.Adapter<SlidePageAdapter.MyViewHolder> {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private List<Slide> slides;
    public  static  Slide slide1;
    public SlidePageAdapter( Context context,List<Slide> slides) {
        this.context=context;
        this.slides = slides;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemsanpham, null);

        // create ViewHolder

        return new MyViewHolder(itemLayoutView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Slide slide = slides.get(position);
        holder.name.setText(slide.getName());
        holder.ratingBar.setRating(slide.getRatingBar());
        holder.vitri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ChiTiet.class);
                slide1=slides.get(position);
                context.startActivity(intent);
            }
        });
        new AsyncTaskLoadImage1(holder.image).execute(slide.getImage());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sanphambanhang.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<Slide>> call = apiInterface.getJson();
        call.enqueue(new Callback<List<Slide>>() {
            @Override
            public void onResponse(Call<List<Slide>> call, Response<List<Slide>> response) {
//                Picasso.get().load(slide1.getImage()).into(image0);

            }

            @Override
            public void onFailure(Call<List<Slide>> call, Throwable t) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return slides.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        RatingBar ratingBar;
        LinearLayout vitri;


        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image1);
            name = itemView.findViewById(R.id.name);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            vitri=itemView.findViewById(R.id.vitri);
//            Animation animation;
//            animation = AnimationUtils.loadAnimation(context, R.anim.listviewani);
//            itemView.setAnimation(animation);
        }



    }
    @SuppressLint("StaticFieldLeak")
    public static class AsyncTaskLoadImage1 extends AsyncTask<String, String, Bitmap> {
        private final static String TAG = "AsyncTaskLoadImage";
        private ImageView imageView;

        AsyncTaskLoadImage1(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            try {
                URL url = new URL(params[0]);
                bitmap = BitmapFactory.decodeStream((InputStream) url.getContent());
            } catch (IOException e) {
                Log.e(TAG, Objects.requireNonNull(e.getMessage()));
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }
}
