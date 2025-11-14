package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.Admin;
import com.example.proyectofinal.Models.Cashier;
import com.example.proyectofinal.Models.GestionUsuarios;
import com.example.proyectofinal.Models.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmployeesController {

    @FXML
    private TableView<User> employeeTable;

    @FXML
    private TableColumn<User, String> colIdEmployees;

    @FXML
    private TableColumn<User, String> colNameEmployees;

    @FXML
    private TableColumn<User, String> colEmailEmployees;

    @FXML
    private TableColumn<User, String> colPasswordEmployees;

    @FXML
    private TableColumn<User, String> colDepartmentEmployees;

    @FXML
    private TableColumn<User, String> colRoleEmployees;

    @FXML
    private TextField txtIdEmployee;
    @FXML
    private TextField txtNameEmployee;
    @FXML
    private TextField txtPasswordEmployee;
    @FXML
    private TextField txtEmailEmployee;
    @FXML
    private TextField txtWorkerId;

    @FXML
    private Button btnModifyEmployee;

    @FXML
    private Button btbnDeleteEmployee;

    private GestionUsuarios gestor;

    public void setGestor(GestionUsuarios gestor) {
        this.gestor = gestor;
        loadEmployees();
    }

    @FXML
    public void initialize() {
        configureColumns();

        employeeTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, selected) -> {
            if (selected != null) {
                txtIdEmployee.setText(selected.getId());
                txtNameEmployee.setText(selected.getName());
                txtPasswordEmployee.setText(selected.getPassword());
                txtEmailEmployee.setText(selected.getEmail());

                if (selected instanceof Admin admin) {
                    txtWorkerId.setText(admin.getDepartment());
                } else if (selected instanceof Cashier cashier) {
                    txtWorkerId.setText(cashier.getWorkerId());
                }
            }
        });
    }

    private void configureColumns() {
        colIdEmployees.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNameEmployees.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmailEmployees.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPasswordEmployees.setCellValueFactory(new PropertyValueFactory<>("password"));

        colDepartmentEmployees.setCellValueFactory(data -> {
            User user = data.getValue();
            if (user instanceof Admin admin) {
                return new SimpleStringProperty(admin.getDepartment());
            } else if (user instanceof Cashier cashier) {
                return new SimpleStringProperty(cashier.getWorkerId());
            }
            return new SimpleStringProperty("");
        });

        colRoleEmployees.setCellValueFactory(u ->
                new SimpleStringProperty(u.getValue().getRolDescription()));
    }

    private void loadEmployees() {
        employeeTable.setItems(FXCollections.observableArrayList(gestor.getAdminList()));
        employeeTable.getItems().addAll(gestor.getCashierList());
    }

    @FXML
    private void onModifyEmployees() {
        User selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Warning", "Please select an employee.", Alert.AlertType.WARNING);
            return;
        }

        selected.setId(txtIdEmployee.getText());
        selected.setName(txtNameEmployee.getText());
        selected.setPassword(txtPasswordEmployee.getText());
        selected.setEmail(txtEmailEmployee.getText());

        gestor.guardarUsuarios();
        employeeTable.refresh();

        showAlert("Success", "Employee updated successfully!", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void onDeleteEmployees() {
        User selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Warning", "Please select an employee.", Alert.AlertType.WARNING);
            return;
        }

        if (selected instanceof Admin) {
            gestor.getAdminList().remove(selected);
        } else if (selected instanceof Cashier) {
            gestor.getCashierList().remove(selected);
        }

        gestor.guardarUsuarios();
        loadEmployees();

        showAlert("Success", "Employee deleted.", Alert.AlertType.INFORMATION);
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
