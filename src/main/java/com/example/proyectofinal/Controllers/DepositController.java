package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.AccountArrange;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DepositController {

    @FXML
    private TextField txtAccountNumber;

    @FXML
    private TextField txtAmountDeposit;

    @FXML
    private Button btnRegisterEmployee;

    @FXML
    private Button btnCancelRegistration;

    private final AccountArrange accountArrange = new AccountArrange();

    @FXML
    public void initialize() {
        accountArrange.loadAccounts();

        btnRegisterEmployee.setOnAction(event -> makeDeposit());
        btnCancelRegistration.setOnAction(event -> clearFields());
    }

    private void makeDeposit() {
        String accountNumber = txtAccountNumber.getText().trim();
        String amountText = txtAmountDeposit.getText().trim();

        if (accountNumber.isEmpty() || amountText.isEmpty()) {
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

        boolean success = accountArrange.deposit(accountNumber, amount);

        if (success){
            showAlert("Success", "Deposit completed successfully.");
            clearFields();}
        else{
            showAlert("Error", "Deposit failed. Check the account number.");}
    }

    private void clearFields() {
        txtAccountNumber.clear();
        txtAmountDeposit.clear();
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(title);
        alert.setContentText(msg);
        alert.show();
    }
}
