package com.example.kaimou.cashmoney;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;

import com.example.kaimou.cashmoney.model.User;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import timber.log.Timber;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.emailEditText) EditText userEmail;
    @Bind(R.id.passwordEditText) EditText userPass;
    private SharedPreferences prefs;
    AsyncHttpClient client = new AsyncHttpClient();

    public static final String BASE_URL = "http://10.9.104.253:3000/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        prefs = getApplicationContext().getSharedPreferences("prefs", MODE_PRIVATE);

        if (prefs.getBoolean("saveLogin", false)) {
            userEmail.setText(prefs.getString("email", ""));
            userPass.setText(prefs.getString("pass", ""));
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




    }


    @OnClick(R.id.loginButton)
    public void login() {
        SharedPreferences.Editor editPrefs = prefs.edit();
        editPrefs.putBoolean("saveLogin", true);
        editPrefs.putString("email", userEmail.getText().toString());
        editPrefs.putString("pass", userPass.getText().toString());
        RequestParams params = new RequestParams();
        params.put("email", userEmail.getText().toString());
        params.put("password", userPass.getText().toString());
        //For testing purposes. Remove later.
        Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(startIntent);

        client.post(BASE_URL, params, new AsyncHttpResponseHandler() {
            User temp;

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gson = new Gson();
                String response = new String(responseBody);
                Log.d("async client", response);
                temp = gson.fromJson(response, User.class);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("async client", "Login failed");

            }

            public void onFinish() {
                User.setCurrentUser(temp);
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(startIntent);
            }
        });
        editPrefs.apply();





    }
}
