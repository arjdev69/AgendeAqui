package com.brunoaraujo.agendeaqui.activities;

import android.os.Bundle;
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
import com.brunoaraujo.agendeaqui.fragment.ProvidersFragment;
import com.brunoaraujo.agendeaqui.model.Appointments;
import com.brunoaraujo.agendeaqui.service.AppointmentService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class SchedulesActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private AppointmentService appointmentService;

    private TextView title_view;

    private String token = "";
    private List<Appointments> appointments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedules);

        // User infos
        //this.getUserInfo();

        // Layout id's
        title_view = findViewById(R.id.title_schedules);

        // fragments
        setupBottomNavigationView();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.viewPage, new DashboardFragment()).commit();

        // Services
        //this.StartService();
        //this.getAppointmentsList();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(SchedulesActivity.this,"Usuário logado...", Toast.LENGTH_SHORT).show();
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
                        return true;
                    case R.id.id_add_schedules:
                        fragmentTransaction.replace(R.id.viewPage, new ProvidersFragment()).commit();
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