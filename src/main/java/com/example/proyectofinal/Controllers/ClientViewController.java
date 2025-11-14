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



    public void setClient(Client cient) {
        this.client = cient;
        usernameClient.setText(cient.getName() + " " + cient.getId());
    }

    public void setGestor (GestionUsuarios gestor){
        this.gestor = gestor;
    }

    @FXML private void showBalance () throws IOException {
        VBox viewCheckBalance = FXMLLoader.load(getClass().getResource("/com/example/proyectofinal/View/CheckBalance.fxml"));
        layoutClient.getChildren().setAll(viewCheckBalance);
    }
    @FXML
    private void showTransferView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/View/TransferClient.fxml"));
        Parent newView = loader.load();

        TransferClientController controller = loader.getController();
        controller.setClient(client);

        layoutClient.getChildren().setAll(newView);
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

