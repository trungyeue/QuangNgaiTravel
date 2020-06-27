package com.example.javachipnavigationbar.Group;

import android.os.Bundle;

import com.example.javachipnavigationbar.Adapter.SlidePageAdapter;
import com.example.javachipnavigationbar.Adapter.SlidePageAdapter1;
import com.example.javachipnavigationbar.Adapter.ViewPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.TextView;

import com.example.javachipnavigationbar.R;
import com.ismaeldivita.chipnavigation.util.TextViewKt;

import static com.example.javachipnavigationbar.Adapter.SlidePageAdapter.slide1;
import static com.example.javachipnavigationbar.Adapter.SlidePageAdapter1.slidea;

public class ChiTiet extends AppCompatActivity {
TextView chitiet;
SlidePageAdapter slidePageAdapter;
SlidePageAdapter1 slidePageAdapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //
        ViewPager viewPager = findViewById(R.id.pager);
        chitiet=findViewById(R.id.chitiet);

            chitiet.setText(slide1.getName());

//        chitiet.setText(slidea.getName());
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), viewPager);
        viewPager.setAdapter(viewPagerAdapter);
    }
}
