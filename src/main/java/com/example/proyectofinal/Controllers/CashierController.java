package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.Cashier;
import com.example.proyectofinal.Models.GestionUsuarios;
import com.example.proyectofinal.Models.ManagementAccount;
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

    public void setGestor(GestionUsuarios gestor){
        this.gestor = gestor;
    }

    private ManagementAccount managementAccount = new ManagementAccount();

    public void setManagementAccount(ManagementAccount managementAccount){
        this.managementAccount = managementAccount;
    }

    @FXML
    private void showRegisterClient() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/View/ClientsRegistration.fxml"));
            Parent root = loader.load();

            ClientsRegistrationController controller = loader.getController();
            controller.setGestor(gestor);
            controller.setAccountManager(managementAccount);

            setAnchors(root);
            layoutCashier.getChildren().setAll(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showClients() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/View/Clients.fxml"));
            Parent root = loader.load();

            ClientsController controller = loader.getController();
            controller.setGestor(gestor);

            setAnchors(root);
            layoutCashier.getChildren().setAll(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showCheckBalance() throws IOException {
        VBox root = FXMLLoader.load(getClass().getResource("/com/example/proyectofinal/View/CheckBalance.fxml"));
        setAnchors(root);
        layoutCashier.getChildren().setAll(root);
    }

    @FXML
    private void showDeposit() throws IOException {
        VBox root = FXMLLoader.load(getClass().getResource("/com/example/proyectofinal/View/Deposit.fxml"));
        setAnchors(root);
        layoutCashier.getChildren().setAll(root);
    }

    @FXML
    private void showTransfers() throws IOException {
        VBox root = FXMLLoader.load(getClass().getResource("/com/example/proyectofinal/View/Transfer.fxml"));
        setAnchors(root);
        layoutCashier.getChildren().setAll(root);
    }

    @FXML
    private void onLogOut() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/View/Login.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        ((Stage) layoutCashier.getScene().getWindow()).close();
    }

    public void setCashier(Cashier cashier){
        this.cashier = cashier;
        usernameCashier.setText(cashier.getName() + " " + cashier.getId());
        managementAccount.loadAccounts();
    }

    private void setAnchors(Parent root){
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
    }
}
