package com.brunoaraujo.agendeaqui.service;

import com.brunoaraujo.agendeaqui.model.Appointments;
import com.brunoaraujo.agendeaqui.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AppointmentService {

    @GET("/appointments")
    Call<List<Appointments>> getAppointmentsList(@Header("Authorization") String token);

}
