package com.brunoaraujo.agendeaqui.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.brunoaraujo.agendeaqui.R;
import com.brunoaraujo.agendeaqui.fragment.DashboardFragment;
import com.brunoaraujo.agendeaqui.fragment.PerfilFragment;
import com.brunoaraujo.agendeaqui.fragment.SchedulesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class SchedulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedules);

        setupBottomNavigationView();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.viewPage, new DashboardFragment()).commit();

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(SchedulesActivity.this,"Usuário logado...", Toast.LENGTH_SHORT).show();
    }

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
                        return true;
                    case R.id.id_add_schedules:
                        fragmentTransaction.replace(R.id.viewPage, new SchedulesFragment()).commit();
                        return true;
                    case R.id.id_perfil:
                        fragmentTransaction.replace(R.id.viewPage, new PerfilFragment()).commit();
                        return true;

                }

                return false;
            }
        });

    }
}