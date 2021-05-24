package com.example.agendeaqui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.agendeaqui.R;

public class LoginActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
    }

    public void loginAction(View view){
        startActivity( new Intent(getApplicationContext(), RegisterActivity.class));
    }
    
}