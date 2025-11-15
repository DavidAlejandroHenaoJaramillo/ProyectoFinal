package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class ClientsRegistrationController {

    @FXML private TextField txtIdClient;
    @FXML private TextField txtNameClient;
    @FXML private TextField txtPasswordClient;
    @FXML private TextField txtEmailClient;
    @FXML private TextField txtAddressClient;
    @FXML private TextField txtPhoneClient;
    @FXML private Button btnRegisterClient;
    @FXML private AnchorPane layoutCashier;
    @FXML private ComboBox<String> cmbAccountType;
    @FXML private TextField txtOpeningBalance;

    private GestionUsuarios gestor;
    private AccountArrange accountArrange;

    public void setGestor(GestionUsuarios gestor){ this.gestor = gestor; }
    public void setAccountArrange(AccountArrange accountArrange){ this.accountArrange = accountArrange; }

    @FXML
    public void initialize() {
        if (cmbAccountType != null) {
            cmbAccountType.getItems().addAll("SAVINGS", "CHECKING", "BUSINESS");
        }
    }

    @FXML private void registerClient () {
        String idClient = txtIdClient.getText().trim();
        String nameClient = txtNameClient.getText().trim();
        String passwordClient = txtPasswordClient.getText().trim();
        String emailClient = txtEmailClient.getText().trim();
        String addressClient = txtAddressClient.getText().trim();
        String phoneClient = txtPhoneClient.getText().trim();
        String accountType = (cmbAccountType.getValue() == null) ? "SAVINGS" : cmbAccountType.getValue();
        String openingBalanceText = txtOpeningBalance.getText().trim();

        if(!validateFields()) return;

        double openingBalance;
        try {
            openingBalance = Double.parseDouble(openingBalanceText);
            if (openingBalance < 0) {
                showAlertError("Error", "Opening balance must be positive.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlertError("Error", "Invalid opening balance.");
            return;
        }

        Client client = new Client(idClient, passwordClient, nameClient, emailClient, addressClient, phoneClient);
        gestor.addClient(client);

        if (accountArrange != null) accountArrange.loadAccounts();

        Account newAccount = accountArrange.createAccount(idClient, accountType, openingBalance);
        accountArrange.addAccount(newAccount);

        showAlertInformation("Success" , "Client and account registered successfully.\nAccount number: " + newAccount.getAccountNumber());
        clearFields();
    }

    private boolean validateFields(){
        if(txtIdClient.getText().trim().isEmpty() || txtPasswordClient.getText().trim().isEmpty()
                || txtNameClient.getText().trim().isEmpty() || txtEmailClient.getText().trim().isEmpty()
                || txtAddressClient.getText().trim().isEmpty()|| txtPhoneClient.getText().trim().isEmpty()
                || txtOpeningBalance.getText().trim().isEmpty()){
            showAlertError("Error", "Please fill all the fields");
            return false;
        }
        return true;
    }

    @FXML private void clearFields(){
        txtPasswordClient.clear();
        txtNameClient.clear();
        txtEmailClient.clear();
        txtAddressClient.clear();
        txtIdClient.clear();
        txtPhoneClient.clear();
        txtOpeningBalance.clear();
        if(cmbAccountType!=null) cmbAccountType.getSelectionModel().clearSelection();
    }

    private void showAlertError(String title, String message) { new Alert(Alert.AlertType.ERROR, message).showAndWait(); }
    private void showAlertInformation(String title, String message) { new Alert(Alert.AlertType.INFORMATION, message).showAndWait(); }
}
