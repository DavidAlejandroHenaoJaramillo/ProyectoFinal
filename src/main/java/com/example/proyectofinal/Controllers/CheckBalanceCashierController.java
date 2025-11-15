package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.Account;
import com.example.proyectofinal.Models.AccountArrange;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CheckBalanceCashierController {

    @FXML
    private TextField txtAccountNumber;
    @FXML
    private Label lblBalance;
    @FXML
    private Button btnCheckBalance;
    private AccountArrange accountArrange;

    public void setAccountArrange(AccountArrange accountArrange) {
        this.accountArrange = accountArrange;
        this.accountArrange.loadAccounts();
    }

    @FXML
    public void initialize() {
        btnCheckBalance.setOnAction(e -> checkBalance());
    }

    @FXML
    private void checkBalance() {
        String number = txtAccountNumber.getText().trim();

        if (number.isEmpty()) {
            showError("Enter an account number.");
            return;
        }

        Account acc = accountArrange.getAccount(number);
        if (acc == null) {
            showError("Account not found.");
            return;
        }

        lblBalance.setText(String.format("%.2f", acc.getBalance()));
    }

    private void showError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText("Error");
        a.setContentText(msg);
        a.show();
    }
}
