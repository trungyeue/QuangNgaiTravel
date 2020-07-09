package com.example.javachipnavigationbar.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.javachipnavigationbar.Page.LoginPage;
import com.example.javachipnavigationbar.R;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment implements View.OnClickListener {

    public AccountFragment() {
        // Required empty public constructor
    }
    private TextView name, email;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Button thoat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account, container, false);
        FacebookSdk.sdkInitialize(getApplicationContext());
        name = v.findViewById(R.id.name);
        email = v.findViewById(R.id.emailFace);
        thoat = v.findViewById(R.id.thoat);
        preferences = getContext().getSharedPreferences("PrefsFile", Context.MODE_PRIVATE);
        editor = preferences.edit();
        thoat.setOnClickListener(this);
        email.setText(preferences.getString("pref_email","not_found"));
        name.setText(preferences.getString("NameFace","not_found"));


        return v;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.thoat:
               editor.clear().apply();
                LoginManager.getInstance().logOut();
                startActivity(new Intent(getContext(), LoginPage.class));
                getActivity().finish();
                break;
        }
    }
}
