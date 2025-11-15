package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CashierController {
    @FXML private Label usernameCashier;
    @FXML private AnchorPane layoutCashier;

    private Cashier cashier;
    private GestionUsuarios gestor;
    private AccountArrange accountArrange;


    public void setGestor (GestionUsuarios gestor){ this.gestor = gestor; }
    public void setAccountArrange(AccountArrange accountArrange) { this.accountArrange = accountArrange; }

    @FXML private void showRegisterClient() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/View/ClientsRegistration.fxml"));
            Parent root = loader.load();
            ClientsRegistrationController controller = loader.getController();
            controller.setGestor(gestor);
            controller.setAccountArrange(accountArrange);
            layoutCashier.getChildren().setAll(root);
            setAnchors(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML private void showClients() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/View/Clients.fxml"));
            Parent root = loader.load();
            ClientsController controller = loader.getController();
            controller.setGestor(gestor);
            layoutCashier.getChildren().setAll(root);
            setAnchors(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML private void showCheckBalance() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/View/CheckBalance.fxml"));
            Parent root = loader.load();
            CheckBalanceController controller = loader.getController();
            controller.setAccountArrange(accountArrange);
            layoutCashier.getChildren().setAll(root);
            setAnchors(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML private void showDeposit () {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/View/Deposit.fxml"));
            Parent root = loader.load();
            DepositController controller = loader.getController();
            controller.setAccountArrange(accountArrange);
            layoutCashier.getChildren().setAll(root);
            setAnchors(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML private void showTransfers() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/View/Transfer.fxml"));
            Parent root = loader.load();
            TransferController controller = loader.getController();
            controller.setAccountArrange(accountArrange);
            layoutCashier.getChildren().setAll(root);
            setAnchors(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void showCheckMovements() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/View/MovementView.fxml"));
        BorderPane movementsView = loader.load();

        MovementViewController controller = loader.getController();
        layoutCashier.getChildren().setAll(movementsView);
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

    private void setAnchors(Parent root) {
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
    }
}
