package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.Account;
import com.example.proyectofinal.Models.Client;
import com.example.proyectofinal.Models.ManagementAccount;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CheckBalanceController {

    @FXML
    private ComboBox<String> cmbAccounts;

    @FXML
    private Button btnCheckBalance;

    @FXML
    private Label txtBalance;

    private ManagementAccount managementAccount;
    private Client client;

    public void setManagementAccount(ManagementAccount managementAccount) {
        this.managementAccount = managementAccount;
        this.managementAccount.loadAccounts();
    }

    public void setClient(Client client) {
        this.client = client;
        loadClientAccounts();
    }

    @FXML
    public void initialize() {
        btnCheckBalance.setOnAction(event -> checkBalance());
    }

    private void loadClientAccounts() {
        if (client == null || managementAccount == null) return;

        cmbAccounts.getItems().clear();

        for (Account acc : managementAccount.getClientAccount(client.getId())) {
            cmbAccounts.getItems().add(acc.getAccountNumber());
        }
    }

    private void checkBalance() {
        String selectedAccount = cmbAccounts.getValue();

        if (selectedAccount == null) {
            showError("Please select an account.");
            return;
        }

        Account account = managementAccount.searchAccount(selectedAccount);

        if (account == null) {
            showError("The selected account does not exist.");
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
