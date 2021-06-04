package com.brunoaraujo.agendeaqui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.brunoaraujo.agendeaqui.R;
import com.brunoaraujo.agendeaqui.model.Login;
import com.brunoaraujo.agendeaqui.model.Session;
import com.brunoaraujo.agendeaqui.service.AuthService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;

    private Retrofit retrofit;
    private AuthService authService;

    private EditText email_login;
    private EditText password_login;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        email_login = findViewById(R.id.email_login);
        password_login = findViewById(R.id.password_login);

        this.StartServiceLogin();
    }

    // Method Service
    public void StartServiceLogin(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.10:3333")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        authService = retrofit.create(AuthService.class);
    }

    private void Auth(String email, String senha){
        Login login = new Login(email, senha);
        Call<Session> call = authService.loginService(login);

        call.enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {

                if(response.isSuccessful()) {
                    Session loginResponse = response.body();
                    Log.d("Login ","Response: " + loginResponse.getUser().getName());
                    Toast.makeText(LoginActivity.this,"Login Realizado",Toast.LENGTH_SHORT).show();
                    goToSchedulesView();
                }else{
                    Toast.makeText(LoginActivity.this,
                            "Login não foi realizado",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {
                Toast.makeText(LoginActivity.this,
                        "Falha ao realizar o Login.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


    // Method Action View
    public void loginAction(View view) {
        String email = email_login.getText().toString();
        String senha = password_login.getText().toString();

        if( !email.isEmpty() ){
            if( !senha.isEmpty() ){
                this.Auth(email, senha);
            } else{
                Toast.makeText(LoginActivity.this,
                        "Preencha a senha!",
                        Toast.LENGTH_SHORT).show();
            }
        } else{
            Toast.makeText(LoginActivity.this,
                    "Preencha o campo email!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void goToRegisterView(View view){
        startActivity( new Intent(getApplicationContext(), RegisterActivity.class));
    }

    public void goToSchedulesView(){
        startActivity( new Intent(getApplicationContext(), SchedulesActivity.class));
    }
    
}