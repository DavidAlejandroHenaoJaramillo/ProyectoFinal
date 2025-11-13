package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.Admin;
import com.example.proyectofinal.Models.GestionUsuarios;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class AdministratorController {
    @FXML
    private AnchorPane layoutAdmin;

    @FXML
    private Label usernameAdministrator;

    private Admin admin;
    private GestionUsuarios gestionUsuarios;


    @FXML private void showEmployeesRegister () throws IOException {
        VBox viewEmployeesRegister = FXMLLoader.load(getClass().getResource("/com/example/proyectofinal/View/EmployeesRegistration.fxml"));
        layoutAdmin.getChildren().setAll(viewEmployeesRegister);
    }
    @FXML private void showEmployees () throws IOException {
        VBox viewEmployees = FXMLLoader.load(getClass().getResource("/com/example/proyectofinal/View/Employees.fxml"));
        layoutAdmin.getChildren().setAll(viewEmployees);
    }
    @FXML private void onLogOut () throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/View/Login.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        ((Stage) layoutAdmin.getScene().getWindow()).close();
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
        usernameAdministrator.setText(admin.getName() + " " + admin.getId());
    }

    public void setGestor (GestionUsuarios gestor){
        this.gestionUsuarios = gestor;
    }


}

