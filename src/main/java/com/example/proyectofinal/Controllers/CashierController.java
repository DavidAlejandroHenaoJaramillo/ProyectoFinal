package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.Admin;
import com.example.proyectofinal.Models.Cashier;
import com.example.proyectofinal.Models.GestionUsuarios;
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

    private Cashier cashier;
    private GestionUsuarios gestor;

    public void setGestor (GestionUsuarios gestor){
        this.gestor = gestor;
    }

    @FXML private void showRegisterClient () throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/View/ClientsRegistration.fxml"));
        Parent root = loader.load();
        ClientsRegistrationController controller = loader.getController();
        controller.setGestor(gestor);
        layoutCashier.getChildren().setAll(root);
    }
    @FXML private void showClients () throws IOException {
        Parent viewClients = FXMLLoader.load(getClass().getResource("/com/example/proyectofinal/View/Clients.fxml"));
        layoutCashier.getChildren().setAll(viewClients);
    }

    @FXML private void showCheckBalance() throws IOException {
        Parent viewCheckBalance = FXMLLoader.load(getClass().getResource("/com/example/proyectofinal/View/CheckBalance.fxml"));
        layoutCashier.getChildren().setAll(viewCheckBalance);
    }

    @FXML private void showDeposit () throws IOException {
        Parent viewDeposit = FXMLLoader.load(getClass().getResource("/com/example/proyectofinal/View/Deposit.fxml"));
        layoutCashier.getChildren().setAll(viewDeposit);
    }

    @FXML private void showTransfers() throws IOException {
        Parent viewTransfers = FXMLLoader.load(getClass().getResource("/com/example/proyectofinal/View/Transfer.fxml"));
        layoutCashier.getChildren().setAll(viewTransfers);
    }


    @FXML private void onLogOut () throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/View/Login.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        ((Stage) layoutCashier.getScene().getWindow()).close();
    }
    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
        usernameCashier.setText(cashier.getName() + " " + cashier.getId());
    }

}
