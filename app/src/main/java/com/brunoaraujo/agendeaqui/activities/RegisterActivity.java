package com.brunoaraujo.agendeaqui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.brunoaraujo.agendeaqui.R;
import com.brunoaraujo.agendeaqui.model.Session;
import com.brunoaraujo.agendeaqui.model.User;
import com.brunoaraujo.agendeaqui.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private UserService userService;

    private EditText name_user;
    private EditText email_login;
    private EditText password_login;
    private EditText password_login_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        name_user = findViewById(R.id.name_user);
        email_login = findViewById(R.id.email_login);
        password_login = findViewById(R.id.password_login);
        password_login_confirm = findViewById(R.id.password_login_confirm);

        this.StartServiceLogin();
    }

    // Method Service
    public void StartServiceLogin(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.10:3333")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userService = retrofit.create(UserService.class);
    }

    public void RegisterUserService(String name, String email, String password, String password_confirm){
        if(!password.equals(password_confirm)){
            Toast.makeText(RegisterActivity.this,"Senhas diferentes", Toast.LENGTH_SHORT).show();
        }else{
            User user = new User(0, name, email, password);
            Call<User>  call = userService.registerUserService(user);

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()) {
                        User userResponse = response.body();
                        Log.d("USER: ", "-> " + userResponse.getName());
                        Toast.makeText(RegisterActivity.this,"Usuário cadastrado.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(RegisterActivity.this,"Falha ao cadastrar usuário.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this,"Falha no serviço de cadastro de usuário.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    // Actions
    public void RegisterUser(View view){
        String name = email_login.getText().toString();
        String email = email_login.getText().toString();
        String password = password_login.getText().toString();
        String password_confirm = password_login_confirm.getText().toString();

        if(!email.isEmpty()){
            if(!password.isEmpty()){
                if(!name.isEmpty()){
                    this.RegisterUserService(name, email, password, password_confirm);
                }else{
                    Toast.makeText(RegisterActivity.this,
                            "Preencha o nome do usuário!",
                            Toast.LENGTH_SHORT).show();
                }
            } else{
                Toast.makeText(RegisterActivity.this,
                        "Preencha a senha!",
                        Toast.LENGTH_SHORT).show();
            }
        } else{
            Toast.makeText(RegisterActivity.this,
                    "Preencha o campo email!",
                    Toast.LENGTH_SHORT).show();
        }
    }


    // Move the scenes
    public void goToLoginView(View view){
        startActivity( new Intent(getApplicationContext(), LoginActivity.class));
    }
}