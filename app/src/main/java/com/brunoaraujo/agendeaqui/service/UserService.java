package com.brunoaraujo.agendeaqui.service;

import com.brunoaraujo.agendeaqui.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("/users")
    Call<User> registerUserService(@Body User user);

}
