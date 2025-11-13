package com.example.proyectofinal.Models;

public class User{
    private String username;
    private String password;
    private String name;
    private String email;
    private String id;


    public User(String username, String password, String name, String email, String id) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
