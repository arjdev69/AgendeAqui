package com.brunoaraujo.agendeaqui.model;

public class Login {

    private String email;
    private String password;

    public Login(String email, String senha) {
        this.email = email;
        this.password = senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
