package com.example.proyectofinal.Models;

public class Client extends User {
    private String address;
    private String phone;

    public Client(String id, String password, String name, String email  , String address, String phone) {
        super(id, password, name, email);
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String getRolDescription (){
        return "Client";
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
