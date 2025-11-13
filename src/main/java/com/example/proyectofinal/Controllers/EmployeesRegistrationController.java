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

public class EmployeesRegistrationController {
    @FXML
    private TextField txtIdEmployee;
    @FXML
    private TextField txtNameEmployee;
    @FXML
    private TextField txtPasswordEmployee;
    @FXML
    private TextField txtEmailEmployee;
    @FXML
    private TextField txtWorkerId;
    @FXML
    private Button btnRegisterEmployee;

    private GestionUsuarios gestor = new GestionUsuarios();



    @FXML private void registerEmployee () {
        String idEmployee = txtIdEmployee.getText();
        String nameEmployee = txtNameEmployee.getText();
        String passwordEmployee = txtPasswordEmployee.getText();
        String emailEmployee = txtEmailEmployee.getText();
        String workerId = txtWorkerId.getText();

        if(!validateFields()){
            return;
        }
        Cashier cashier = new Cashier(idEmployee,passwordEmployee , nameEmployee , emailEmployee , workerId);
        gestor.addCashier(cashier);
        gestor.guardarUsuarios();
        showAlertInformation("Success" , "Cashier registered successfully");
        clearFields();
    }
    @FXML private void backLayoutCashier () {

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
