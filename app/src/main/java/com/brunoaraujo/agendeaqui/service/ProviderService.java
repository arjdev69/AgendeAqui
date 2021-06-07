package com.brunoaraujo.agendeaqui.service;

import com.brunoaraujo.agendeaqui.model.Appointments;
import com.brunoaraujo.agendeaqui.model.Provider;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ProviderService {

    @GET("/providers")
    Call<List<Provider>> getProviderList(@Header("Authorization") String token);

}
