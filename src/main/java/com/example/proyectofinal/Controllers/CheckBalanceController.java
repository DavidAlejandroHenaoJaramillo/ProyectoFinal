package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.Account;
import com.example.proyectofinal.Models.Client;
import com.example.proyectofinal.Models.ManagementAccount;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CheckBalanceController {
    @FXML private ComboBox<String> cmbAccounts;
    @FXML private Button btnCheckBalance;
    @FXML private Label txtBalance;

    private ManagementAccount managementAccount;
    private Client client;

    public void setManagementAccount(ManagementAccount managementAccount) {
        this.managementAccount = managementAccount;
        if (this.managementAccount != null) this.managementAccount.loadAccounts();
        tryLoadClientAccounts();
    }

    public void setClient(Client client) {
        this.client = client;
        tryLoadClientAccounts();
    }

    @FXML
    public void initialize() {
        btnCheckBalance.setOnAction(e -> checkBalance());
    }

    private void tryLoadClientAccounts() {
        if (this.client == null || this.managementAccount == null) return;
        cmbAccounts.getItems().clear();
        for (Account acc : managementAccount.getClientAccount(client.getId())) {
            cmbAccounts.getItems().add(acc.getAccountNumber());
        }
    }

    private void checkBalance() {
        String selected = cmbAccounts.getValue();
        if (selected == null) { showError("Please select an account."); return; }
        Account acc = managementAccount.searchAccount(selected);
        if (acc == null) { showError("Account not found."); return; }
        txtBalance.setText(String.format("%.2f", acc.getBalance()));
    }
    private void showError(String msg){ new Alert(Alert.AlertType.ERROR, msg).show(); }
}

