package com.example.javachipnavigationbar.Page;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.javachipnavigationbar.Model.Account;
import com.example.javachipnavigationbar.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = Registration.class.getSimpleName();
    private RelativeLayout rlayout;
    private Animation animation;
    Button signup;
    EditText username1, edtemail, edtpassword, edtpasscofil;
    private String user_name, email, pass_word, pass_cofil;
    public static final String REGISTER_URL = "https://sanphambanhang.000webhostapp.com/Dangki.php";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = findViewById(R.id.bgHeader);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        rlayout = findViewById(R.id.rlayout);
        username1 = findViewById(R.id.username);
        edtemail = findViewById(R.id.edtEmailRegister);
        edtpassword = findViewById(R.id.edtPassword);
        edtpasscofil = findViewById(R.id.edtPasswordRegister);
        signup = findViewById(R.id.signup);
        animation = AnimationUtils.loadAnimation(this, R.anim.uptodowndiagonal);
        rlayout.setAnimation(animation);
        signup.setOnClickListener(this);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void registerUser(final String user_name, final String pass_word, final String email) {
        if (checkEditText(username1) && checkEditText(edtpassword) && checkabout(edtpassword) && checkEditText(edtpasscofil) && checkEditText(edtemail) && isValidEmail(email)) {

            StringRequest registerRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            String message = "";
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.getInt("success") == 1) {
                                    Account account = new Account();
                                    account.setUsername(jsonObject.getString("user_name"));
                                    account.setEmail(jsonObject.getString("email"));
                                    message = jsonObject.getString("message");
                                    Toast.makeText(Registration.this, message, Toast.LENGTH_LONG).show();
                                    //Start LoginActivity

                                    Intent intent = new Intent(Registration.this, LoginPage.class);
                                    startActivity(intent);


                                } else {
                                    message = jsonObject.getString("message");
                                    Toast.makeText(Registration.this, message, Toast.LENGTH_LONG).show();
                                }

                            } catch (JSONException error) {
                                VolleyLog.d(TAG, "Error: " + error.getMessage());
                            }
                        }

                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Registration.this, "Bạn bị mất kết nối !", Toast.LENGTH_SHORT).show();
                            VolleyLog.d(TAG, "Error: " + error.getMessage());
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put(KEY_USERNAME, user_name);
                    params.put(KEY_PASSWORD, pass_word);
                    params.put(KEY_EMAIL, email);
                    return params;
                }

            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(registerRequest);
        }
    }

    private boolean checkEditText(EditText editText) {
        if (editText.getText().toString().trim().length() > 0)
            return true;
        {
            editText.setError("Vui lòng nhập dữ liệu!");
        }

        return false;
    }

    private boolean checkabout(EditText edtpassword) {

        if (edtpassword.getText().toString().trim().length() >= 8)
            return true;
        {
            Toast.makeText(this, "Bạn phải nhập 8 kí tự trở lên", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    /**
     * Check Email
     */

    private boolean isValidEmail(String target) {
        if (target.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+.[a-z]"))
            return true;
        else {
            edtemail.setError("Email sai định dạng!");
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v == signup) {
            user_name = username1.getText().toString().trim();
            pass_word = edtpassword.getText().toString().trim();
            pass_cofil = edtpasscofil.getText().toString().trim();
            email = edtemail.getText().toString().trim();
            registerUser(user_name, pass_word, email);
        }
    }

}
