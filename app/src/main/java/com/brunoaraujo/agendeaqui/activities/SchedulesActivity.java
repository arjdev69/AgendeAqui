package com.brunoaraujo.agendeaqui.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.brunoaraujo.agendeaqui.R;

public class SchedulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedules);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(SchedulesActivity.this,"Usuário logado...", Toast.LENGTH_SHORT).show();
    }
}