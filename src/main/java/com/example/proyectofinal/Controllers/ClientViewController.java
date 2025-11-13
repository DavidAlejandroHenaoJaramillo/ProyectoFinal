package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.Admin;
import com.example.proyectofinal.Models.Client;
import com.example.proyectofinal.Models.GestionUsuarios;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ClientViewController {

    @FXML
    private Label usernameClient;

    private Client client;
    private GestionUsuarios gestor;
    private GestionUsuarios gestionUsuarios;


    public void setClient(Client cient) {
        this.client = client;
        usernameClient.setText(client.getName() + " " + client.getId());
    }

    public void setGestor (GestionUsuarios gestor){
        this.gestionUsuarios = gestor;
    }
}
