package com.example.javachipnavigationbar.Page;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.javachipnavigationbar.MainActivity;
import com.example.javachipnavigationbar.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.facebook.FacebookSdk.getApplicationContext;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btRegister;
    private TextView tvLogin;
    private static Button login;
   public static  LoginButton loginFacebook;
    private EditText email, password;
    private String u, p, c, d, e;
    private ProgressBar progress;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static String userId = null;
    private static final String PREFS_NAME = "PrefsFile";
    public static String edtUserLogin1, edtPassLogin2, email1;
    String TAG = "LoginApp";
    String url = "https://sanphambanhang.000webhostapp.com/LoginQ.php";
    String url1 = "http://sanphambanhang.000webhostapp.com/Name.php";
    CallbackManager callbackManager;
    private CheckBox mCheckRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        btRegister = findViewById(R.id.btRegister);
        loginFacebook=findViewById(R.id.loginFacebook);
        tvLogin = findViewById(R.id.tvLogin);
        progress = findViewById(R.id.progress1);
        login = findViewById(R.id.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        mCheckRemember = findViewById(R.id.checkBox);
        login.setOnClickListener(this);
        btRegister.setOnClickListener(this);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn) {
            startActivity(new Intent(LoginPage.this, MainActivity.class));
            finish();
        }
       
        loginFacebook.setReadPermissions(Arrays.asList("public_profile","email"));
        loginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                result();
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
                if (isLoggedIn) {
                    startActivity(new Intent(LoginPage.this, MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String mUserId = sharedPreferences.getString("userId", "");
        if (mUserId != "") {
            startActivity(new Intent(LoginPage.this, MainActivity.class));
            finish();
        }

        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration


    }

    @Override
    protected void onStart() {
        super.onStart();
        LoginManager.getInstance().logOut();
    }

    private void result() {
        GraphRequest graphRequest=GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
           Log.d("JSON",response.getJSONObject().toString());
                try {
                    editor.putString("EmailFace", object.getString("email"));
                    editor.putString("NameFace", object.getString("name"));
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
                editor.apply();
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,first_name, birthday");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btRegister:
                Intent intent = new Intent(LoginPage.this, Registration.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(tvLogin, "tvLogin");
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(LoginPage.this, pairs);
                startActivity(intent, activityOptions.toBundle());
                break;
            case R.id.login:
                if (checkEditText(email) && checkEditText(password)) {
                    Login(url);
                }
//                PostData(url1);
                boolean aBoolean = mCheckRemember.isChecked();
                editor.putString("pref_email", email.getText().toString());
                editor.putString("pref_pass", password.getText().toString());
                editor.putBoolean("pref_check", aBoolean);
                break;
            case R.id.loginFacebook:

        }
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn) {
            startActivity(new Intent(LoginPage.this, MainActivity.class));
            finish();
        }

        if (sharedPreferences.contains("pref_email")) {
            u = sharedPreferences.getString("pref_name", "not_found");
            email.setText(u);
        }
        if (sharedPreferences.contains("pref_pass")) {
            p = sharedPreferences.getString("pref_pass", "not_found");
            password.setText(p);
        }
        if (sharedPreferences.contains("id_user")) {
            c = sharedPreferences.getString("id_user", "not_found");
        }
        if (sharedPreferences.contains("pref_check")) {
            boolean b = sharedPreferences.getBoolean("pref_check", false);
            mCheckRemember.setChecked(b);
        }
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void Login(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                userId = response;
                if (!response.isEmpty()) {
                    Toast.makeText(LoginPage.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    editor.putString("userId", userId);
                    editor.apply();
                    email1 = email.getText().toString().trim();
                    edtPassLogin2 = password.getText().toString().trim();
                    startActivity(new Intent(LoginPage.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginPage.this, "Đăng nhập  thất bại", Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginPage.this, "Kiểm tra  Email và Password", Toast.LENGTH_SHORT).show();

                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginPage.this, "Vui lòng kiểm tra lại mạng !!", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", email.getText().toString().trim());
                params.put("password", password.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private boolean checkEditText(EditText editText) {
        if (editText.getText().toString().trim().length() > 0)
            return true;
        else {
            editText.setError("Vui lòng nhập dữ liệu!");
        }
        return false;
    }

//    private void PostData(final String url) {
//        final RequestQueue requestQueue = Volley.newRequestQueue(this);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onResponse(final String response) {
//                Toast.makeText(LoginPage.this, "" + response, Toast.LENGTH_SHORT).show();
//                Toast.makeText(LoginPage.this, sharedPreferences.getString("userId", userId), Toast.LENGTH_SHORT).show();
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    JSONArray jsonArray = jsonObject.getJSONArray("giohang");
//                    for (int i = 0; i < response.length(); i++) {
//                        JSONObject explrObject = jsonArray.getJSONObject(i);
////                        onclick.setText(explrObject.getString("user_name"));
////                        emaidn.setText(explrObject.getString("email"));
////                        pass = explrObject.getString("password");
////                        diachigiaohang.setText(explrObject.getString("diachi" ) + explrObject.getString("tinh" ) + explrObject.getString("phuongxa" ) +
////                                explrObject.getString("quanhuyen" ) + explrObject.getString("sdt" ));
////                        Toast.makeText(CAPNHATTHONGTIN.this, ""+preferences.getString("userId",userId), Toast.LENGTH_SHORT).show();
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) { // lau vc // log zo di t xem thu// chòe xíu, chừ lấy từng cái ra phải hân thì listview á
//                Log.d("vvvv", "onErrorResponse: " + error); // mở phai php t xem thửu ok
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//                params.put("user_id", sharedPreferences.getString("userId", userId));
//                return params;
//            }
//        };
//        requestQueue.add(stringRequest); // cái ni để làm chi qên r
//
//
//    }
}
