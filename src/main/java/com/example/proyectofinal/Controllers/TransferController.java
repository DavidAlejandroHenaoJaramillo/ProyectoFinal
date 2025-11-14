package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.AccountArrange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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

    private AccountArrange accountArrange = new AccountArrange();

    @FXML
    private void initialize() {
        accountArrange.cargarAccounts();
    }

    @FXML
    private void makeTransfer(ActionEvent event) {

        String origin = txtOriginAccount.getText().trim();
        String destination = txtAccountNumber.getText().trim();
        String amountStr = txtAmountTransfer.getText().trim();

        if (origin.isEmpty() || destination.isEmpty() || amountStr.isEmpty()) {
            showAlertWarning("Missing fields", "Please fill in all fields.");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountStr);
            if (amount <= 0) {
                showAlertWarning("Invalid amount", "The amount must be greater than 0.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlertError("Invalid number", "Please enter a valid numeric amount.");
            return;
        }

        boolean result = accountArrange.realizarTransferencia(origin, destination, amount);

        if (result) {
            showAlertInformation("Success", "Transfer completed successfully!");
            clearFields();
        } else {
            showAlertError("Error", "Transfer failed. Check account numbers or balance.");
        }
    }

    @FXML
    private void clearFields() {
        txtOriginAccount.clear();
        txtAccountNumber.clear();
        txtAmountTransfer.clear();
    }

    private void showAlertInformation(String title, String message) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(message);
        a.showAndWait();
    }

    private void showAlertWarning(String title, String message) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(message);
        a.showAndWait();
    }

    private void showAlertError(String title, String message) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(message);
        a.showAndWait();
    }
}
