package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.Cashier;
import com.example.proyectofinal.Models.GestionUsuarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

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
    private TextField txtClientId;
    @FXML
    private Button btnRegisterClient;

    private GestionUsuarios gestor = new GestionUsuarios();



    @FXML private void registerClient () {
        String idClient = txtIdClient.getText();
        String nameClient = txtNameClient.getText();
        String passwordClient = txtPasswordClient.getText();
        String emailClient = txtEmailClient.getText();
        String ClientId = txtClientId.getText();

        if(!validateFields()){
            return;
        }
        Cashier cashier = new Cashier(idClient,passwordClient , nameClient , emailClient , ClientId);
        gestor.addCashier(cashier);
        gestor.guardarUsuarios();
        showAlertInformation("Success" , "Cashier registered successfully");
        clearFields();
    }
    @FXML private void backLayoutCashier () {

    }
    private boolean validateFields(){
        if(txtIdClient.getText().trim().isEmpty() || txtPasswordClient.getText().trim().isEmpty()
                || txtNameClient.getText().trim().isEmpty() || txtEmailClient.getText().trim().isEmpty(
        ) || txtClientId.getText().trim().isEmpty()){
            showAlertError("Error", "Please fill all the fields");
            return false;
        }
        return true;
    }


    private void clearFields(){
        txtPasswordClient.clear();
        txtNameClient.clear();
        txtEmailClient.clear();
        txtClientId.clear();
        txtIdClient.clear();
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
