package com.example.proyectofinal.Models;

public class Admin extends User{

    private String department;

    public Admin(String id, String password, String name, String email , String department) {
        super(id, password, name, email);
        this.department = department;
    }
    public Admin(String id, String password, String name, String email) {
        this(id, password, name, email, "General administration");
    }

    @Override
    public String getRolDescription() {
        return "Admin";
    }

    public String getDepartment() {
        return department;
    }
}
