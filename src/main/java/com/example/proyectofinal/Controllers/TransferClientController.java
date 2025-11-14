package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.AccountArrange;
import com.example.proyectofinal.Models.Client;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TransferClientController {

    @FXML
    private ComboBox<String> comboOriginAccount;

    @FXML
    private TextField txtAccountNumber;

    @FXML
    private TextField txtAmountDeposit;

    @FXML
    private Button btnMakeDeposit;

    @FXML
    private Button btnCancelTransfer;

    private final AccountArrange accountArrange = new AccountArrange();
    private Client client;

    public void setClient(Client client) {
        this.client = client;
        loadClientAccounts();
    }

    @FXML
    public void initialize() {
        accountArrange.loadAccounts();

        btnMakeDeposit.setOnAction(event -> performTransfer());
        btnCancelTransfer.setOnAction(event -> clearFields());
    }

    private void loadClientAccounts() {
        comboOriginAccount.getItems().clear();

        accountArrange.getClientAccounts(client.getId())
                .forEach(account -> comboOriginAccount.getItems().add(account.getAccountNumber()));
    }

    private void performTransfer() {
        String origin = comboOriginAccount.getValue();
        String dest = txtAccountNumber.getText().trim();
        String amountText = txtAmountDeposit.getText().trim();

        if (origin == null || dest.isEmpty() || amountText.isEmpty()) {
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

        if (success)
            showAlert("Success", "Transfer completed successfully.");
        else
            showAlert("Error", "Transfer failed.");
    }

    private void clearFields() {
        comboOriginAccount.setValue(null);
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
