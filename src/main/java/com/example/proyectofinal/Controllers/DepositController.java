package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.AccountArrange;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DepositController {
    @FXML private TextField txtAccountNumber;
    @FXML private TextField txtAmountDeposit;
    @FXML private Button btnRegisterEmployee;
    @FXML private Button btnCancelRegistration;

    private AccountArrange accountArrange;

    public void setAccountArrange(AccountArrange accountArrange){ this.accountArrange = accountArrange; if (this.accountArrange!=null) this.accountArrange.loadAccounts(); }

    @FXML public void initialize() {
        if (btnRegisterEmployee != null) btnRegisterEmployee.setOnAction(event -> makeDeposit());
        if (btnCancelRegistration != null) btnCancelRegistration.setOnAction(event -> clearFields());
    }

    private void makeDeposit() {
        String accountNumber = txtAccountNumber.getText().trim();
        String amountText = txtAmountDeposit.getText().trim();

        if (accountNumber.isEmpty() || amountText.isEmpty()) { showAlert("Error", "All fields must be filled."); return; }

        double amount;
        try { amount = Double.parseDouble(amountText); if (amount <= 0) throw new NumberFormatException(); }
        catch (NumberFormatException e) { showAlert("Error", "Invalid amount."); return; }

        if (accountArrange == null) { showAlert("Error", "Accounts system not available."); return; }
        accountArrange.loadAccounts();
        boolean success = accountArrange.deposit(accountNumber, amount);
        if (success){ showAlert("Success", "Deposit completed successfully."); clearFields(); }
        else{ showAlert("Error", "Deposit failed. Check the account number."); }
    }

    private void clearFields() { txtAccountNumber.clear(); txtAmountDeposit.clear(); }
    private void showAlert(String title, String msg) { Alert alert = new Alert(Alert.AlertType.INFORMATION); alert.setHeaderText(title); alert.setContentText(msg); alert.showAndWait(); }
}
