package com.example.kaimou.cashmoney;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.example.kaimou.cashmoney.model.User;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

    public static final String BASE_URL = "http://10.9.104.253:3000/api/";

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

        OkHttpClient client = new OkHttpClient();
        client.setReadTimeout(60, TimeUnit.SECONDS);
        client.setConnectTimeout(60, TimeUnit.SECONDS);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.interceptors().add(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Timber.i("starting retrofit");
        Call<List<User>> call = api.getUser();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Response<List<User>> response, Retrofit retrofit) {
                Timber.i(response.toString());
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }


    @OnClick(R.id.loginButton)
    public void login() {
        SharedPreferences.Editor editPrefs = prefs.edit();
        editPrefs.putBoolean("saveLogin", true);
        editPrefs.putString("email", userEmail.getText().toString());
        editPrefs.putString("pass", userPass.getText().toString());
        editPrefs.apply();
        Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(startIntent);
    }
}
