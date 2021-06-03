package com.brunoaraujo.agendeaqui.service;

import com.brunoaraujo.agendeaqui.model.Login;
import com.brunoaraujo.agendeaqui.model.Session;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    @POST("/sessions")
    Call<Session> loginService(@Body Login login);
}
