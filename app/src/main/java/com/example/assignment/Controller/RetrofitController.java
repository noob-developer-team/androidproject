package com.example.assignment.Controller;

import com.example.assignment.Model.Schedule;
import com.example.assignment.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitController {

    @GET(value = "user")
    Call<List<User>> getUser(@Query("username") String username, @Query("password") String password);

    @GET(value = "schedule")
    Call<List<Schedule>> getSchedule(@Query("userId") int userId, @Query("date") String date);
}
