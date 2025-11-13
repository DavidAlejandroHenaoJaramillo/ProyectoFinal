package com.example.proyectofinal.Models;

import com.example.proyectofinal.Models.Admin;
import com.example.proyectofinal.Models.Cashier;
import com.example.proyectofinal.Models.Client;

import java.io.*;
import java.util.ArrayList;

public class GestionUsuarios {
    private ArrayList<Admin> adminList;
    private ArrayList<Cashier> cashierList;
    private ArrayList<Client> clientList;

    private static final String ARCHIVO = "usuarios.txt";

    public GestionUsuarios() {
        adminList = new ArrayList<>();
        cashierList = new ArrayList<>();
        clientList = new ArrayList<>();
    }

    public ArrayList<Admin> getAdminList() {
        return adminList;
    }

    public void setAdminList(ArrayList<Admin> adminList) {
        this.adminList = adminList;
    }

    public ArrayList<Cashier> getCashierList() {
        return cashierList;
    }

    public void setCashierList(ArrayList<Cashier> cashierList) {
        this.cashierList = cashierList;
    }

    public ArrayList<Client> getClientList() {
        return clientList;
    }

    public void setClientList(ArrayList<Client> clientList) {
        this.clientList = clientList;
    }
    public boolean cargarUsuarios() {
        File archivo = new File(ARCHIVO);
        if(!archivo.exists()){
            return true;
        }
        try(BufferedReader reader = new BufferedReader(new FileReader(archivo))){
            String linea;
            while((linea = reader.readLine())!=null){
                String[]datos = linea.split(",");
                String tipo = datos[0];

                switch(tipo.trim()){
                    case "Admin":
                        Admin admin = new Admin(datos[1], datos[2], datos[3], datos[4], datos[  5]);
                        adminList.add(admin);
                        break;
                    case "Cashier":
                        Cashier cashier = new Cashier(datos[1], datos[2], datos[3], datos[4], datos[5]);
                        cashierList.add(cashier);
                        break;
                    case "Client":
                        Client client = new Client(datos[1], datos[2], datos[3], datos[4], datos[5],datos[6] );
                        clientList.add(client);
                        break;
                }
            }
            return true;
        } catch(IOException e){
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean guardarUsuarios() {
        try(PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO))){
            for(Admin admin : adminList){
                writer.println("Admin,"+
                        admin.getId()+ ","
                        + admin.getPassword() + ","
                        + admin.getName()+ ","
                        + admin.getEmail()+ ","
                        + admin.getDepartment());
            }
            for (Cashier cashier : cashierList) {
                writer.println("Cashier," +
                        cashier.getId() + "," +
                        cashier.getPassword() + "," +
                        cashier.getName() + "," +
                        cashier.getEmail() + "," +
                        cashier.getWorkerId());
            }
            for (Client client : clientList) {
                writer.println("Client," +
                        client.getId() + "," +
                        client.getPassword() + "," +
                        client.getName() + "," +
                        client.getEmail() + "," +
                        client.getPhone() + "," +
                        client.getAdress());
            }
            return true;
        } catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean addClient(Client client) {
        clientList.add(client);
        return guardarUsuarios();
    }

    public boolean addCashier(Cashier cashier) {
        cashierList.add(cashier);
        return guardarUsuarios();
    }

    public boolean addAdmin(Admin admin) {
        adminList.add(admin);
        return guardarUsuarios();
    }
    public boolean eliminateClient(String id) {
        clientList.removeIf(c -> c.getId().equals(id));
        return guardarUsuarios();
    }

    public boolean eliminateCashier(String id) {
        cashierList.removeIf(c -> c.getId().equals(id));
        return guardarUsuarios();
    }

    public boolean eliminateAdmin(String id) {
        adminList.removeIf(a -> a.getId().equals(id));
        return guardarUsuarios();
    }
}