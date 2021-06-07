package com.brunoaraujo.agendeaqui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.brunoaraujo.agendeaqui.R;
import com.brunoaraujo.agendeaqui.activities.SchedulesActivity;
import com.brunoaraujo.agendeaqui.adapter.AdapterSchedules;
import com.brunoaraujo.agendeaqui.model.Appointments;
import com.brunoaraujo.agendeaqui.service.AppointmentService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardFragment extends Fragment {

    private RecyclerView recyclerView;
    public List<Appointments> appointments = new ArrayList<>();

    private Retrofit retrofit;
    private AppointmentService appointmentService;

    private String token = "";

    private AdapterSchedules adpSchedules;


    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

       // Log.d("", appointments.get(0).getId().toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);


        this.getUserInfo(container.getContext());
        this.StartService();
        this.getAppointmentsList(container.getContext());


        recyclerView = view.findViewById(R.id.listViewDashboard);

        Log.d(" - ", appointments.size()+"");

        adpSchedules = new AdapterSchedules(container.getContext(), appointments);

        //Setup recyclerView
        final LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adpSchedules);

        return view;
    }

    // Services
    public void StartService(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.10:3333")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        appointmentService = retrofit.create(AppointmentService.class);
    }

    public void getAppointmentsList(Context cont){
        Call<List<Appointments>> call = appointmentService.getAppointmentsList("Bearer " + token);

        call.enqueue(new Callback<List<Appointments>>() {
            @Override
            public void onResponse(Call<List<Appointments>> call, Response<List<Appointments>> response) {
                if(response.isSuccessful()){
                    appointments = response.body();
                    adpSchedules.adiciona(appointments);
                    Toast.makeText( cont,"Agendamentos listados", Toast.LENGTH_SHORT).show();
                }else{
                    Log.d("Resp: ", " -> " + response.message());
                    Toast.makeText(cont," - ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Appointments>> call, Throwable t) {
                Toast.makeText(cont,"Falha na requisição", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Actions
    public void getUserInfo(Context cont){
        SharedPreferences preferences = cont.getSharedPreferences("com.brunoaraujo.agendeaqui", Context.MODE_PRIVATE);
        token = preferences.getString("sessionToken", "");
    }

}