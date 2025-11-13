package com.example.proyectofinal.Models;

public class Client extends User {
    private String adress;
    private String phone;

    public Client(String id, String password, String name, String email  , String adress, String phone) {
        super(id, password, name, email);
        this.adress = adress;
        this.phone = phone;
    }

    @Override
    public String getRolDescription (){
        return "Cliente";
    }

    public String getAdress() {
        return adress;
    }
    public void setAdress(String adress) {
        this.adress = adress;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
