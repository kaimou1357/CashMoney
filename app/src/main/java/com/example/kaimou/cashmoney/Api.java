package com.example.kaimou.cashmoney;

import com.example.kaimou.cashmoney.model.Loan;
import com.example.kaimou.cashmoney.model.User;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by redacted on 12/5/15 at 5:27 PM.
 */
public interface Api {

    @GET("/users/")
    Call<List<User>> getUser();

    @POST("")
    void login(@Body User user);
}
