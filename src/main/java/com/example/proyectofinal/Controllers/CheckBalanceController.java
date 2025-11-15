package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CheckBalanceController {
    @FXML private ComboBox<String> cmbAccounts;
    @FXML private Button btnCheckBalance;
    @FXML private Label txtBalance;

    private AccountArrange accountArrange;
    private Client client;

    public void setAccountArrange(AccountArrange accountArrange) {
        this.accountArrange = accountArrange;
        if (this.accountArrange != null) this.accountArrange.loadAccounts();
        tryLoadClientAccounts();
    }

    public void setClient(Client client) {
        this.client = client;
        tryLoadClientAccounts();
    }

    @FXML
    public void initialize() {
        if (btnCheckBalance != null) btnCheckBalance.setOnAction(e -> checkBalance());
    }

    private void tryLoadClientAccounts() {
        if (cmbAccounts == null) return;
        cmbAccounts.getItems().clear();
        if (this.client == null || this.accountArrange == null) return;

        for (Account acc : accountArrange.getClientAccounts(client.getId())) {
            cmbAccounts.getItems().add(acc.getAccountNumber());
        }
    }

    private void checkBalance() {
        String selected = cmbAccounts.getValue();
        if (selected == null || selected.trim().isEmpty()) { showError("Please select an account."); return; }
        Account acc = accountArrange.getAccount(selected);
        if (acc == null) { showError("Account not found."); return; }
        txtBalance.setText(String.format("%.2f", acc.getBalance()));
    }
    private void showError(String msg){ new Alert(Alert.AlertType.ERROR, msg).showAndWait(); }
}
