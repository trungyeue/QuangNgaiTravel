package com.example.javachipnavigationbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.example.javachipnavigationbar.Fragment.AccountFragment;
import com.example.javachipnavigationbar.Fragment.BreakfastFragment;
import com.example.javachipnavigationbar.Fragment.DiscoventFrgament;
import com.example.javachipnavigationbar.Fragment.HomeFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    ChipNavigationBar bootomNav;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bootomNav = findViewById(R.id.bottomv);
        if (savedInstanceState==null){
            bootomNav.setItemSelected(R.id.home,true);
            fragmentManager=getSupportFragmentManager();
            HomeFragment homFragment=new HomeFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container,homFragment).commit();
        }
        bootomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i) {
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.discover:
                        fragment = new DiscoventFrgament();
                        break;
                    case R.id.breakfast:
                        fragment = new BreakfastFragment();
                        break;
                    case R.id.account:
                        fragment = new AccountFragment();
                        break;
                }
                if (fragment != null) {
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
                } else {
                    Log.e(TAG, "ERROR IN CREATING FRAGMENT");
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

}
