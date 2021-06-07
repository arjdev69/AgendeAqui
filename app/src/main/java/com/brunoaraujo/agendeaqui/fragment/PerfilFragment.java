package com.brunoaraujo.agendeaqui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.brunoaraujo.agendeaqui.R;
import com.brunoaraujo.agendeaqui.adapter.AdapterProviders;
import com.brunoaraujo.agendeaqui.adapter.AdapterSchedules;
import com.brunoaraujo.agendeaqui.model.Appointments;
import com.brunoaraujo.agendeaqui.model.Provider;
import com.brunoaraujo.agendeaqui.service.AppointmentService;
import com.brunoaraujo.agendeaqui.service.ProviderService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PerfilFragment extends Fragment {

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

}