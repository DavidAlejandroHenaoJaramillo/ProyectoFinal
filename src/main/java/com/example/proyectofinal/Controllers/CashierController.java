package com.example.proyectofinal.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CashierController {
    @FXML
    private Label usernameCashier;
    @FXML
    private AnchorPane layoutCashier;

    @FXML private void showRegisterClient () throws IOException {
        VBox viewClientRegistration = FXMLLoader.load(getClass().getResource("/com/example/proyectofinal/Views/ClientRegistration.fxml"));
        layoutCashier.getChildren().setAll(viewClientRegistration);
    }
    @FXML private void showClients () throws IOException {
        VBox viewClients = FXMLLoader.load(getClass().getResource("/com/example/proyectofinal/Views/Clients.fxml"));
        layoutCashier.getChildren().setAll(viewClients);
    }
    @FXML private void showCheckBalance() throws IOException {
        VBox viewCheckBalance = FXMLLoader.load(getClass().getResource("/com/example/proyectofinal/Views/CheckBalance.fxml"));
    }
    @FXML private void showDeposit () throws IOException {
        VBox viewDeposit = FXMLLoader.load(getClass().getResource("/com/example/proyectofinal/Views/Deposit.fxml"));
        layoutCashier.getChildren().setAll(viewDeposit);
    }
    @FXML private void showTransfers() throws IOException {
        VBox viewTransfers = FXMLLoader.load(getClass().getResource("/com/example/proyectofinal/Views/Transfers.fxml"));
        layoutCashier.getChildren().setAll(viewTransfers);
    }

    @FXML private void onLogOut () throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/views/Login.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        ((Stage) layoutCashier.getScene().getWindow()).close();
    }
}
