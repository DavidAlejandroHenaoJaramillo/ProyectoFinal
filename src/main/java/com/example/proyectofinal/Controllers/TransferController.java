package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.AccountArrange;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TransferController {
    @FXML private TextField txtOriginAccount;
    @FXML private TextField txtDestinationAccount;
    @FXML private TextField txtAmount;
    @FXML private Button btnTransfer;
    @FXML private Button btnCancelTransfer;

    private AccountArrange accountArrange;

    public void setAccountArrange(AccountArrange accountArrange){ this.accountArrange = accountArrange; }

    @FXML public void initialize() {
        if (btnTransfer != null) btnTransfer.setOnAction(e -> handleTransfer());
        if (btnCancelTransfer != null) btnCancelTransfer.setOnAction(e -> clearFields());
        if (accountArrange != null) accountArrange.loadAccounts();
    }

    private void clearFields() { txtOriginAccount.clear(); txtDestinationAccount.clear(); txtAmount.clear(); }

    private void handleTransfer() {
        String origin = txtOriginAccount.getText().trim();
        String dest = txtDestinationAccount.getText().trim();
        String amountText = txtAmount.getText().trim();

        if (origin.isEmpty() || dest.isEmpty() || amountText.isEmpty()) { showAlert("Error", "All fields must be filled."); return; }

        double amount;
        try { amount = Double.parseDouble(amountText); if (amount <= 0) throw new NumberFormatException(); }
        catch (NumberFormatException e) { showAlert("Error", "Invalid amount."); return; }

        if (accountArrange == null) { showAlert("Error", "Accounts system not available."); return; }
        accountArrange.loadAccounts();
        boolean success = accountArrange.transfer(origin, dest, amount);
        if (success) { showAlert("Success", "Transfer completed successfully."); clearFields(); }
        else showAlert("Error", "Transfer could not be completed. Check balances and account numbers.");
    }

    private void showAlert(String title, String msg) { Alert alert = new Alert(Alert.AlertType.INFORMATION); alert.setHeaderText(title); alert.setContentText(msg); alert.showAndWait(); }
}
