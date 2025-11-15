package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TransferClientController {
    @FXML private ComboBox<String> comboOriginAccount;
    @FXML private TextField txtAccountNumber;
    @FXML private TextField txtAmountDeposit;
    @FXML private Button btnMakeDeposit;
    @FXML private Button btnCancelTransfer;

    private AccountArrange accountArrange;
    private Client client;

    public void setAccountArrange(AccountArrange accountArrange){
        this.accountArrange = accountArrange;
        if (this.accountArrange!=null)
            this.accountArrange.loadAccounts(); loadClientAccounts();
    }

    public void setClient(Client client){ this.client = client; loadClientAccounts(); }

    @FXML public void initialize() {
        if (btnMakeDeposit != null) btnMakeDeposit.setOnAction(e -> performTransfer());
        if (btnCancelTransfer != null) btnCancelTransfer.setOnAction(e -> clearFields());
        if (accountArrange != null) accountArrange.loadAccounts();
    }

    private void loadClientAccounts() {
        if (comboOriginAccount == null) return;
        comboOriginAccount.getItems().clear();
        if (client == null || accountArrange == null) return;
        for (Account acc : accountArrange.getClientAccounts(client.getId())) {
            comboOriginAccount.getItems().add(acc.getAccountNumber());
        }
    }

    private void performTransfer() {
        String origin = comboOriginAccount.getValue();
        String dest = txtAccountNumber.getText().trim();
        String amountText = txtAmountDeposit.getText().trim();

        if (origin == null || origin.trim().isEmpty() || dest.isEmpty() || amountText.isEmpty()) {
            showAlert("Error", "All fields must be filled.");
            return;
        }

        double amount;
        try { amount = Double.parseDouble(amountText); if (amount <= 0) throw new NumberFormatException(); }
        catch (NumberFormatException e) { showAlert("Error", "Invalid amount."); return; }

        if (accountArrange == null) { showAlert("Error", "Accounts system not available."); return; }
        accountArrange.loadAccounts();
        boolean success = accountArrange.transfer(origin, dest, amount);
        if (success) { showAlert("Success", "Transfer completed successfully."); clearFields(); }
        else { showAlert("Error", "Transfer failed. Check funds and account numbers."); }
    }

    private void clearFields() {
        if (comboOriginAccount != null) comboOriginAccount.setValue(null);
        if (txtAccountNumber != null) txtAccountNumber.clear();
        if (txtAmountDeposit != null) txtAmountDeposit.clear();
    }

    private void showAlert(String title, String msg) { Alert alert = new Alert(Alert.AlertType.INFORMATION); alert.setHeaderText(title); alert.setContentText(msg); alert.showAndWait(); }
}
