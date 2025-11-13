package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.Admin;
import com.example.proyectofinal.Models.Client;
import com.example.proyectofinal.Models.GestionUsuarios;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientViewController {

    @FXML
    private Label usernameClient;

    @FXML
    private AnchorPane layoutClient;

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

    @FXML private void showBalance () throws IOException {
        VBox viewCheckBalance = FXMLLoader.load(getClass().getResource("/com/example/proyectofinal/View/CheckBalance.fxml"));
        layoutClient.getChildren().setAll(viewCheckBalance);
    }
    @FXML private void showTransferView () throws IOException {
        VBox viewTransferClient = FXMLLoader.load(getClass().getResource("/com/example/proyectofinal/View/TransferClient.fxml"));
        layoutClient.getChildren().setAll(viewTransferClient);
    }
    @FXML private void onLogOut () throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/View/Login.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        ((Stage) layoutClient.getScene().getWindow()).close();
    }
}

