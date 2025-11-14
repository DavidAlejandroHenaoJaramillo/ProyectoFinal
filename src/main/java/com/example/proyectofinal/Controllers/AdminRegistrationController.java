package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.Admin;
import com.example.proyectofinal.Models.Cashier;
import com.example.proyectofinal.Models.GestionUsuarios;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class AdminRegistrationController {
    @FXML
    private TextField txtIdAdmin;
    @FXML
    private TextField txtNameAdmin;
    @FXML
    private TextField txtPasswordAdmin;
    @FXML
    private TextField txtEmailAdmin;
    @FXML
    private TextField txtDepartment;
    @FXML
    private Button btnRegisterAdmin;
    @FXML
    private AnchorPane layoutAdmin;

    private GestionUsuarios gestor;

    public void setGestor(GestionUsuarios gestor) {
        this.gestor = gestor;
    }

    @FXML private void registerAdmin () {
        String idAdmin = txtIdAdmin.getText();
        String nameAdmin = txtNameAdmin.getText();
        String passwordAdmin = txtPasswordAdmin.getText();
        String emailAdmin = txtEmailAdmin.getText();
        String department = txtDepartment.getText();

        if(!validateFields()){
            return;
        }
        Admin admin = new Admin(idAdmin,passwordAdmin , nameAdmin , emailAdmin , department);
        gestor.addAdmin(admin);
        gestor.guardarUsuarios();
        showAlertInformation("Success" , "Admin registered successfully");
        clearFields();
    }

    private boolean validateFields(){
        if(txtIdAdmin.getText().trim().isEmpty() || txtPasswordAdmin.getText().trim().isEmpty()
                || txtNameAdmin.getText().trim().isEmpty() || txtEmailAdmin.getText().trim().isEmpty(
        ) || txtDepartment.getText().trim().isEmpty()){
            showAlertError("Error", "Please fill all the fields");
            return false;
        }
        return true;
    }


    @FXML private void clearFields(){
        txtIdAdmin.clear();
        txtNameAdmin.clear();
        txtPasswordAdmin.clear();
        txtEmailAdmin.clear();
        txtDepartment.clear();
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }
    private void showAlertWarning(String title, String message) {
        Alert alertWarning = new Alert(Alert.AlertType.WARNING);
        alertWarning.setTitle(title);
        alertWarning.setHeaderText(null);
        alertWarning.setContentText(message);
        alertWarning.showAndWait();
    }

    private void showAlertError(String title, String message) {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle(title);
        alertError.setHeaderText(null);
        alertError.setContentText(message);
        alertError.showAndWait();
    }
    private void showAlertInformation(String title, String message) {
        Alert alertError = new Alert(Alert.AlertType.INFORMATION);
        alertError.setTitle(title);
        alertError.setHeaderText(null);
        alertError.setContentText(message);
        alertError.showAndWait();
    }
}
