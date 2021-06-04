package com.brunoaraujo.agendeaqui.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    private Retrofit retrofit;

    public Retrofit StartService(){

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.10:3333")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}
