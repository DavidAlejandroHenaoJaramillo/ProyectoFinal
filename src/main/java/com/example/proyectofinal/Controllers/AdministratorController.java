package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.Admin;
import com.example.proyectofinal.Models.GestionUsuarios;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private GestionUsuarios gestor;

    public void setGestor(GestionUsuarios gestor){
        this.gestor = gestor;
    }

    @FXML
    private void showEmployeesRegister() {
        loadView("/com/example/proyectofinal/View/EmployeesRegistration.fxml");
    }

    @FXML
    private void showEmployees() {
        loadView("/com/example/proyectofinal/View/Employees.fxml");
    }

    @FXML
    private void showAdminRegister() {
        loadView("/com/example/proyectofinal/View/AdminRegistration.fxml");
    }

    @FXML
    private void onLogOut() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/View/Login.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        ((Stage) layoutAdmin.getScene().getWindow()).close();
    }

    public void setAdmin(Admin admin){
        this.admin = admin;
        usernameAdministrator.setText(admin.getName() + " " + admin.getId());
    }

    private void loadView(String path){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();

            layoutAdmin.getChildren().setAll(root);

            Object controller = loader.getController();
            try {
                controller.getClass().getMethod("setGestor", GestionUsuarios.class).invoke(controller, gestor);
            } catch (Exception ignored) {}

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
