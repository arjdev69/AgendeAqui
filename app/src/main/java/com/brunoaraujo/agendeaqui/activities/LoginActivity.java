package com.brunoaraujo.agendeaqui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.ui.AppBarConfiguration;

import com.brunoaraujo.agendeaqui.R;

public class LoginActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
    }

    public void goToRegisterView(View view){
        startActivity( new Intent(getApplicationContext(), RegisterActivity.class));
    }

    public void goToSchedulesView(View view){
        startActivity( new Intent(getApplicationContext(), SchedulesActivity.class));
    }
    
}