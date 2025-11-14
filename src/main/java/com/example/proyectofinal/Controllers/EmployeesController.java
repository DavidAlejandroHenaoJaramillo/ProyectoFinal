package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.Admin;
import com.example.proyectofinal.Models.Cashier;
import com.example.proyectofinal.Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
    private Button btnModifyEmployee;

    @FXML
    private Button btbnDeleteEmployee;

    private ObservableList<User> employeesObservable;

    private static final String USERS_FILE = "usuarios.txt";

    @FXML
    public void initialize() {
        configureColumns();
        loadEmployees();
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
            } else {
                return new SimpleStringProperty("");
            }
        });

        colRoleEmployees.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getRolDescription()));
    }

    private void loadEmployees() {
        List<User> employees = readUsersFromFile();
        employeesObservable = FXCollections.observableArrayList(employees);
        employeeTable.setItems(employeesObservable);
    }

    private List<User> readUsersFromFile() {
        List<User> users = new ArrayList<>();
        File file = new File(USERS_FILE);

        if (!file.exists()) return users;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");
                if (parts.length < 5) continue;

                String role = parts[0].trim();
                String id = parts[1].trim();
                String password = parts[2].trim();
                String name = parts[3].trim();
                String email = parts[4].trim();

                if (role.equalsIgnoreCase("Admin") && parts.length >= 6) {
                    String department = parts[5].trim();
                    users.add(new Admin(id, password, name, email, department));
                } else if (role.equalsIgnoreCase("Cashier") && parts.length >= 6) {
                    String workerId = parts[5].trim();
                    users.add(new Cashier(id, password, name, email, workerId));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    @FXML
    private void onModifyEmployee() {
        User selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Warning", "Please select an employee to modify.", Alert.AlertType.WARNING);
            return;
        }

        showAlert("Info", "Modify function not implemented yet.", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void onDeleteEmployee() {
        User selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Warning", "Please select an employee to delete.", Alert.AlertType.WARNING);
            return;
        }

        employeesObservable.remove(selected);
        saveUsersToFile();
        showAlert("Success", "Employee deleted successfully.", Alert.AlertType.INFORMATION);
    }

    private void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (User user : employeesObservable) {
                if (user instanceof Admin admin) {
                    writer.write(String.join(",", "Admin", admin.getId(), admin.getPassword(),
                            admin.getName(), admin.getEmail(), admin.getDepartment()));
                } else if (user instanceof Cashier cashier) {
                    writer.write(String.join(",", "Cashier", cashier.getId(), cashier.getPassword(),
                            cashier.getName(), cashier.getEmail(), cashier.getWorkerId()));
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
