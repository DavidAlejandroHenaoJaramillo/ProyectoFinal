package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.AccountArrange;
import com.example.proyectofinal.Models.Account;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CheckBalanceController {

    @FXML
    private TextField txtNumberAccount;

    @FXML
    private Button btnCheckBalance;

    @FXML
    private Label txtBalance;

    private final AccountArrange accountArrange = new AccountArrange();

    @FXML
    public void initialize() {
        accountArrange.loadAccounts();
        btnCheckBalance.setOnAction(event -> checkBalance());
    }

    private void checkBalance() {
        String number = txtNumberAccount.getText().trim();

        if (number.isEmpty()) {
            showError("Please enter an account number.");
            return;
        }

        Account account = accountArrange.getAccount(number);

        if (account == null) {
            showError("The account number does not exist.");
            return;
        }

        txtBalance.setText(String.format("%.2f", account.getBalance()));
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText(msg);
        alert.show();
    }
}
