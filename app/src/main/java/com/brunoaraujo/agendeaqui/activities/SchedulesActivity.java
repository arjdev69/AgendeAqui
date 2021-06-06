package com.brunoaraujo.agendeaqui.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.brunoaraujo.agendeaqui.R;
import com.brunoaraujo.agendeaqui.fragment.DashboardFragment;
import com.brunoaraujo.agendeaqui.fragment.PerfilFragment;
import com.brunoaraujo.agendeaqui.fragment.SchedulesFragment;
import com.brunoaraujo.agendeaqui.model.Appointments;
import com.brunoaraujo.agendeaqui.model.Session;
import com.brunoaraujo.agendeaqui.service.AppointmentService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SchedulesActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private AppointmentService appointmentService;

    private TextView title_view;

    private String token = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedules);

        // User infos
        this.getUserInfo();

        // Layout id's
        title_view = findViewById(R.id.title_schedules);

        // fragments
        setupBottomNavigationView();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.viewPage, new DashboardFragment()).commit();

        // Services
        this.StartService();
        this.getAppointmentsList();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(SchedulesActivity.this,"Usuário logado...", Toast.LENGTH_SHORT).show();
    }

    // Services
    public void StartService(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.10:3333")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        appointmentService = retrofit.create(AppointmentService.class);
    }

    public void getAppointmentsList(){
        Call<List<Appointments>> call = appointmentService.getAppointmentsList("Bearer " + token);

        call.enqueue(new Callback<List<Appointments>>() {
            @Override
            public void onResponse(Call<List<Appointments>> call, Response<List<Appointments>> response) {
                if(response.isSuccessful()){
                    List<Appointments> appointments = response.body();
                    Log.d(" - - ", appointments.get(0).getProvider().getName());
                    Toast.makeText(SchedulesActivity.this,"Agendamentos listados", Toast.LENGTH_SHORT).show();
                }else{
                    Log.d("Resp: ", " -> " + response.message());
                    Toast.makeText(SchedulesActivity.this," - ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Appointments>> call, Throwable t) {
                Toast.makeText(SchedulesActivity.this,"Falha na requisição", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Actions
    public void getUserInfo(){
        SharedPreferences preferences = getSharedPreferences("com.brunoaraujo.agendeaqui", Context.MODE_PRIVATE);
        token = preferences.getString("sessionToken", "");
    }

    // Tabs Navigation
    private void setupBottomNavigationView(){

        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bnve);

        bottomNavigationViewEx.enableAnimation(true);
        bottomNavigationViewEx.enableItemShiftingMode(true);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(true);

        enableNavigation( bottomNavigationViewEx );
    }

    private void enableNavigation(BottomNavigationViewEx viewEx){

        viewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (item.getItemId()){
                    case R.id.id_dashboard:
                        fragmentTransaction.replace(R.id.viewPage, new DashboardFragment()).commit();
                        title_view.setText("Agendamentos");
                        getAppointmentsList();
                        return true;
                    case R.id.id_add_schedules:
                        fragmentTransaction.replace(R.id.viewPage, new SchedulesFragment()).commit();
                        title_view.setText("Intituições");
                        return true;
                    case R.id.id_perfil:
                        fragmentTransaction.replace(R.id.viewPage, new PerfilFragment()).commit();
                        title_view.setText("Meu Perfil");
                        return true;
                }

                return false;
            }
        });

    }
}