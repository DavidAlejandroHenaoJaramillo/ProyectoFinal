package com.example.proyectofinal.Models;

public class Cashier extends User{
    private String workerId;

    public Cashier(String id, String password, String name, String email , String workerId) {
        super(id, password, name, email);
        this.workerId = workerId;
    }

    @Override
    public String getRolDescription() {
        return "Cashier";
    }
    public String getWorkerId() {
        return workerId;
    }
    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }
}
