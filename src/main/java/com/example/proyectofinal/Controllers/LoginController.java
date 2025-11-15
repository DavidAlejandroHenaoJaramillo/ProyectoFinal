package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LoginController {
    @FXML private TextField txtId;
    @FXML private PasswordField txtPassword;
    @FXML private Button btnLogin;
    @FXML private Button btnCancel;

    private GestionUsuarios gestor = new GestionUsuarios();
    private final AccountArrange accountArrange = new AccountArrange();


    @FXML
    public void initialize() {

        if(!gestor.cargarUsuarios()){
            showAlertError("Error" , "An error occurred, it will start with empty data");
        }
        accountArrange.loadAccounts();

        if(gestor.getAdminList().isEmpty() && gestor.getCashierList().isEmpty() && gestor.getClientList().isEmpty()){
            Admin adminDefault = new Admin("1" , "123456" , "Admin Default" , "admin@gmail.com" , "System");
            gestor.addAdmin(adminDefault);
        }
    }

    @FXML
    private void login() {
        if(!validateFields()) return;

        boolean found = false;
        String id = txtId.getText().trim();
        String password = txtPassword.getText();

        for(Client client : gestor.getClientList()){
            if(client.getId().equals(id) && client.getPassword().equals(password)){
                openViewClient(client);
                found = true; break;
            }
        }
        if (!found) {
            for (Admin admin : gestor.getAdminList()) {
                if(admin.getId().equals(id) && admin.getPassword().equals(password)){
                    openViewAdmin(admin);
                    found = true; break;
                }
            }
        }
        if (!found) {
            for (Cashier cashier : gestor.getCashierList()) {
                if(cashier.getId().equals(id) && cashier.getPassword().equals(password)){
                    openViewCashier(cashier);
                    found = true; break;
                }
            }
        }
        if(!found) showAlertWarning("Warning", "The ID or password are incorrect");
    }

    private boolean validateFields(){
        if(txtId.getText().trim().isEmpty() || txtPassword.getText().trim().isEmpty()){
            showAlertWarning("Error", "Please fill all the fields");
            return false;
        }
        return true;
    }

    @FXML private void cancel() { txtId.clear(); txtPassword.clear(); }

    private void openViewClient(Client client){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/View/ClientView.fxml"));
            Parent root = loader.load();

            ClientViewController controller = loader.getController();
            controller.setClient(client);
            controller.setGestor(gestor);
            controller.setAccountArrange(accountArrange);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Client panel " + client.getName());
            stage.show();
            Stage loginStage = (Stage) txtId.getScene().getWindow();
            loginStage.close();
        } catch(IOException e) {
            e.printStackTrace();
            showAlertError("Error" , "An error occurred, the client view could not be opened");
        }
    }

    private void openViewAdmin(Admin admin){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/View/Administrator.fxml"));
            Parent root = loader.load();

            AdministratorController controller = loader.getController();
            controller.setAdmin(admin);
            controller.setGestor(gestor);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Admin panel " + admin.getName());
            stage.show();
            Stage loginStage = (Stage) txtId.getScene().getWindow();
            loginStage.close();
        } catch(IOException e) {
            e.printStackTrace();
            showAlertError("Error" , "An error occurred, the admin view could not be opened");
        }
    }

    private void openViewCashier(Cashier cashier){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/View/Cashier.fxml"));
            Parent root = loader.load();

            CashierController controller = loader.getController();
            controller.setCashier(cashier);
            controller.setGestor(gestor);
            controller.setAccountArrange(accountArrange);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Cashier panel " + cashier.getName());
            stage.show();
            Stage loginStage = (Stage) txtId.getScene().getWindow();
            loginStage.close();
        } catch(IOException e) {
            e.printStackTrace();
            showAlertError("Error" , "An error occurred, the cashier view could not be opened");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title); alert.setHeaderText(null); alert.setContentText(message); alert.showAndWait();
    }
    private void showAlertWarning(String title, String message) {
        Alert alertWarning = new Alert(Alert.AlertType.WARNING);
        alertWarning.setTitle(title); alertWarning.setHeaderText(null); alertWarning.setContentText(message); alertWarning.showAndWait();
    }
    private void showAlertError(String title, String message) {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle(title); alertError.setHeaderText(null); alertError.setContentText(message); alertError.showAndWait();
    }
}
