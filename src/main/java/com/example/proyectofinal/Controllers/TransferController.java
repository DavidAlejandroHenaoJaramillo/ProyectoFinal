package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.AccountArrange;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

public class TransferController {

    @FXML
    private TextField txtOriginAccount;

    @FXML
    private TextField txtDestinationAccount;

    @FXML
    private TextField txtAmount;

    @FXML
    private Button btnTransfer;

    @FXML
    private Button btnCancelTransfer;

    private final AccountArrange accountArrange = new AccountArrange();

    @FXML
    public void initialize() {
        accountArrange.loadAccounts();

        btnTransfer.setOnAction(event -> handleTransfer());
        btnCancelTransfer.setOnAction(event -> clearFields());
    }

    private void clearFields() {
        txtOriginAccount.clear();
        txtDestinationAccount.clear();
        txtAmount.clear();
    }

    private void handleTransfer() {
        String origin = txtOriginAccount.getText().trim();
        String dest = txtDestinationAccount.getText().trim();
        String amountText = txtAmount.getText().trim();

        if (origin.isEmpty() || dest.isEmpty() || amountText.isEmpty()) {
            showAlert("Error", "All fields must be filled.");
            return;
        }

        double amount;

        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid amount.");
            return;
        }

        boolean success = accountArrange.transfer(origin, dest, amount);

        if (success) {
            showAlert("Success", "Transfer completed successfully.");
        } else {
            showAlert("Error", "Transfer could not be completed. Check balances and account numbers.");
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(title);
        alert.setContentText(msg);
        alert.show();
    }
}
