package com.example.kaimou.cashmoney;

import com.example.kaimou.cashmoney.model.Loan;
import com.example.kaimou.cashmoney.model.User;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by redacted on 12/5/15 at 5:27 PM.
 */
public interface Api {

    @GET("/users/{username}")
    Call<Loan> getUser(@Path("username") String username);

    @POST("")
    void login(@Body User user);
}
