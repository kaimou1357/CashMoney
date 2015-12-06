package com.example.kaimou.cashmoney;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kaimou.cashmoney.model.User;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.Bind;
import butterknife.BindDimen;
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

        Timber.i("onCreate");



        prefs = getApplicationContext().getSharedPreferences("prefs", MODE_PRIVATE);
        if (prefs.getBoolean("saveLogin", false)) {
            userEmail.setText(prefs.getString("email", ""));
            userPass.setText(prefs.getString("pass", ""));
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Timber.i("starting retrofit");
        Api apiService =
                retrofit.create(Api.class);
        Call<List<User>> call = apiService.getUser();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Response<List<User>> response, Retrofit retrofit) {
                Timber.i(response.toString());
            }

            @Override
            public void onFailure(Throwable t) {
                Timber.i("failure");
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
