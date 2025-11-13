package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class LoginController {
    @FXML
    private TextField txtId;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnCancel;

    private ArrayList<User> userList = new ArrayList<>();

    @FXML
    public void initialize() {
        userList.add(new User("1119150996" , "123456" , "Alejandro Henao" , "a.henaojaramillo87@gmail.com" ));

        btnLogin.setOnAction(e -> login());
        btnCancel.setOnAction(e -> cancel());
    }

    @FXML
    private void login() {
        String id = txtId.getText().trim();
        String password = txtPassword.getText();
        String name = userList.get(0).getName();


        if (id.isEmpty() || password.isEmpty()) {
            showAlertWarning("Error" , "Complete all fields");
            return;
        }
        boolean found = false;

        for (User user : userList) {
            if (user.getId().equals(id) && user.getPassword().equals(password)) {
                found = true;
                break;
            }
        }

        if (found) {
            showAlert("Login Succesful", "Welcome " + name);
        } else {
            showAlertError("Error", "Username or password incorrect / User not created");
        }
    }

    @FXML
    private void cancel() {
        txtId.clear();
        txtPassword.clear();
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



    @FXML
    protected void eventKey(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtId)) {

            if (event.getCharacter().equals(" ")) {
                event.consume();
            }

        } else if (evt.equals(txtPassword)) {
            if (event.getCharacter().equals(" ")) {
                event.consume();
            }
        }
    }
}
