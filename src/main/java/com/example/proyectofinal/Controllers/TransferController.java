package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.Cashier;
import com.example.proyectofinal.Models.GestionUsuarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static java.util.Arrays.setAll;

public class TransferController {

    @FXML
    private TextField txtOriginAccount;
    @FXML
    private TextField txtAccountNumber;
    @FXML
    private TextField txtAmountTransfer;
    @FXML
    private Button btnMakeTransfer;
    @FXML
    private Button btnCancelTransfer;

    private GestionUsuarios gestor = new GestionUsuarios();



    @FXML private void makeTransfer (ActionEvent event) throws IOException {
        String originAccount = txtOriginAccount.getText();
        String accountNumber = txtAccountNumber.getText();
        String amountTransfer = txtAmountTransfer.getText();

        if(!validateFields()){
            return;
        }
        Cashier cashier = new Cashier(idEmployee,passwordEmployee , nameEmployee , emailEmployee , workerId);
        gestor.addCashier(cashier);
        gestor.guardarUsuarios();
        showAlertInformation("Success" , "Cashier registered successfully");
        clearFields();
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


    @FXML private void clearFields(){
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
