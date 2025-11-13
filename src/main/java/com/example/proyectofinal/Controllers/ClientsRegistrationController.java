package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.Cashier;
import com.example.proyectofinal.Models.Client;
import com.example.proyectofinal.Models.GestionUsuarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
    private TextField txtAdressClient;
    @FXML
    private TextField txtPhoneClient;
    @FXML
    private Button btnRegisterEmployee;
    @FXML
    private AnchorPane layoutCashier;

    private GestionUsuarios gestor = new GestionUsuarios();



    @FXML private void registerClient () {
        String idClient = txtIdClient.getText();
        String nameClient = txtNameClient.getText();
        String passwordClient = txtPasswordClient.getText();
        String emailClient = txtEmailClient.getText();
        String adressClient = txtAdressClient.getText();
        String phoneClient = txtPhoneClient.getText();

        if(!validateFields()){
            return;
        }
        Client client = new client(idClient,passwordClient , nameClient , emailClient , adressClient , phoneClient);
        gestor.addClient(client);
        gestor.guardarUsuarios();
        showAlertInformation("Success" , "Client registered successfully");
        clearFields();
    }
    @FXML private void backLayoutAdmin (ActionEvent event) throws IOException {
        Parent previusView = FXMLLoader.load(getClass().getResource("/com/example/proyectofinal/View/Administrator.fxml"));
        layoutAdmin.getChildren().setAll(previusView);
    }
    private boolean validateFields(){
        if(txtIdEmployee.getText().trim().isEmpty() || txtPasswordEmployee.getText().trim().isEmpty()
                || txtNameEmployee.getText().trim().isEmpty() || txtEmailEmployee.getText().trim().isEmpty(
        ) || txtWorkerId.getText().trim().isEmpty()){
            showAlertError("Error", "Please fill all the fields");
            return false;
        }
        return true;
    }


    private void clearFields(){
        txtPasswordEmployee.clear();
        txtNameEmployee.clear();
        txtEmailEmployee.clear();
        txtWorkerId.clear();
        txtIdEmployee.clear();
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
