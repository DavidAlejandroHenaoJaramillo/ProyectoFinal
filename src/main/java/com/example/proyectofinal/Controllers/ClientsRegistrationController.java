package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static java.util.Arrays.setAll;


public class ClientsRegistrationController {

    @FXML
    private TextField txtIdClient;
    @FXML
    private TextField txtNameClient;
    @FXML
    private TextField txtPasswordClient;
    @FXML
    private TextField txtEmailClient;
    @FXML
    private TextField txtAddressClient;
    @FXML
    private TextField txtPhoneClient;
    @FXML
    private Button btnRegisterClient;
    @FXML
    private AnchorPane layoutAdmin;
    @FXML
    private ComboBox<String> cmbAccountType;
    @FXML
    private TextField txtOpeningBalance;

    private GestionUsuarios gestor;

    public void setGestor (GestionUsuarios gestor){
        this.gestor = gestor;
    }

    private ManagementAccount accountManager;

    public void setAccountManager (ManagementAccount accountManager){
        this.accountManager = accountManager;
    }

    @FXML
    public void initialize() {
        cmbAccountType.getItems().addAll("SAVING", "CHECKING", "BUSINESS");
    }

    @FXML
    private void registerClient() {

        String idClient = txtIdClient.getText();
        String nameClient = txtNameClient.getText();
        String passwordClient = txtPasswordClient.getText();
        String emailClient = txtEmailClient.getText();
        String addressClient = txtAddressClient.getText();
        String phoneClient = txtPhoneClient.getText();
        String accountType = cmbAccountType.getValue();
        String openingBalanceText = txtOpeningBalance.getText();

        if (!validateFields()) {
            return;
        }

        if (accountType == null) {
            showAlertError("Error", "Please select an account type.");
            return;
        }

        double openingBalance;
        try {
            openingBalance = Double.parseDouble(openingBalanceText);
            if (openingBalance < 0) {
                showAlertError("Error", "Opening balance must be positive.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlertError("Error", "Invalid opening balance.");
            return;
        }

        Client client = new Client(idClient, passwordClient, nameClient, emailClient, addressClient, phoneClient);
        gestor.addClient(client);

        Account newAccount = accountManager.createAccount(idClient, accountType, openingBalance);
        accountManager.addAccount(newAccount);

        showAlertInformation(
                "Success",
                "Client and account registered successfully.\nAccount number: " + newAccount.getAccountNumber()
        );

        clearFields();
        cmbAccountType.getSelectionModel().clearSelection();
    }


    private boolean validateFields(){
        if(txtIdClient.getText().trim().isEmpty() || txtPasswordClient.getText().trim().isEmpty()
                || txtNameClient.getText().trim().isEmpty() || txtEmailClient.getText().trim().isEmpty(
        ) || txtAddressClient.getText().trim().isEmpty()|| txtPhoneClient.getText().trim().isEmpty() ||
                txtOpeningBalance.getText().trim().isEmpty()){
            showAlertError("Error", "Please fill all the fields");
            return false;
        }
        return true;
    }


    @FXML private void clearFields(){
        txtPasswordClient.clear();
        txtNameClient.clear();
        txtEmailClient.clear();
        txtAddressClient.clear();
        txtIdClient.clear();
        txtPhoneClient.clear();
        txtOpeningBalance.clear();

    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }
    private void showAlertWarning(String title, String message) {
        Alert alertWarning = new Alert(Alert.AlertType.WARNING);
        alertWarning.setTitle(title);
        alertWarning.setHeaderText(null);
        alertWarning.setContentText(message);
        alertWarning.showAndWait();
    }

    private void showAlertError(String title, String message) {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle(title);
        alertError.setHeaderText(null);
        alertError.setContentText(message);
        alertError.showAndWait();
    }
    private void showAlertInformation(String title, String message) {
        Alert alertError = new Alert(Alert.AlertType.INFORMATION);
        alertError.setTitle(title);
        alertError.setHeaderText(null);
        alertError.setContentText(message);
        alertError.showAndWait();
    }

}
