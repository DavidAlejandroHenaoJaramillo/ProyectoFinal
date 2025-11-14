package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.Client;
import com.example.proyectofinal.Models.GestionUsuarios;
import com.example.proyectofinal.Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClientsController {

    @FXML
    private TableView<Client> clientsTable;

    @FXML
    private TableColumn<Client, String> colIdClients;

    @FXML
    private TableColumn<Client, String> colNameClients;

    @FXML
    private TableColumn<Client, String> colPhoneClients;

    @FXML
    private TableColumn<Client, String> colPasswordClients;

    @FXML
    private TableColumn<Client, String> colAddressClients;

    @FXML
    private TableColumn<Client, String> colEmailClients;

    @FXML
    private TextField txtIdClient;
    @FXML
    private TextField txtNameClient;
    @FXML
    private TextField txtPasswordClient;
    @FXML
    private TextField txtEmailClient;
    @FXML
    private TextField txtAddressClient;
    @FXML
    private TextField txtPhoneClient;

    @FXML
    private Button btnModifyClient;

    @FXML
    private Button btnDeleteClient;

    private ObservableList<Client> clientsObservable;

    private GestionUsuarios gestor;

    public void setGestor (GestionUsuarios gestor){
        this.gestor = gestor;
    }

    private static final String USERS_FILE = "usuarios.txt";

    @FXML
    public void initialize() {
        configureColumns();
        loadClients();
        clientsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, selectedClient) -> {
            if (selectedClient != null) {
                txtIdClient.setText(selectedClient.getId());
                txtNameClient.setText(selectedClient.getName());
                txtEmailClient.setText(selectedClient.getEmail());
                txtPhoneClient.setText(selectedClient.getPhone());
            }
        });
    }

    private void configureColumns() {
        colIdClients.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNameClients.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhoneClients.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colPasswordClients.setCellValueFactory(new PropertyValueFactory<>("password"));
        colAddressClients.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmailClients.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadClients() {
        clientsTable.setItems(FXCollections.observableArrayList(gestor.getClientList()));
    }

    @FXML
    private void onModifyClient() {
        Client selected = clientsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Warning", "Please select a client.", Alert.AlertType.WARNING);
            return;
        }

        selected.setId(txtIdClient.getText());
        selected.setName(txtNameClient.getText());
        selected.setPassword(txtPasswordClient.getText());
        selected.setEmail(txtEmailClient.getText());
        selected.setAddress(txtAddressClient.getText());
        selected.setPhone(txtPhoneClient.getText());

        gestor.guardarUsuarios();
        clientsTable.refresh();

        showAlert("Success", "Client updated successfully!", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void onDeleteClient() {
        Client selected = clientsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Warning", "Please select a client.", Alert.AlertType.WARNING);
            return;
        }

        gestor.getClientList().remove(selected);
        gestor.guardarUsuarios();
        loadClients();

        showAlert("Success", "Client deleted.", Alert.AlertType.INFORMATION);
    }

    private void saveClientsToFile() {
        List<User> otherUsers = readNonClientUsers();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {


            for (Client client : clientsObservable) {
                writer.write(String.join(",", "Client", client.getId(), client.getPassword(),
                        client.getName(), client.getEmail(), client.getAddress(), client.getPhone()));
                writer.newLine();
            }


            for (User user : otherUsers) {
                writer.write(switch (user.getRolDescription()) {
                    case "Admin" -> String.join(",", "Admin", user.getId(), user.getPassword(),
                            user.getName(), user.getEmail(), ((com.example.proyectofinal.Models.Admin) user).getDepartment());
                    case "Cashier" -> String.join(",", "Cashier", user.getId(), user.getPassword(),
                            user.getName(), user.getEmail(), ((com.example.proyectofinal.Models.Cashier) user).getWorkerId());
                    default -> "";
                });
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<User> readNonClientUsers() {
        List<User> users = new ArrayList<>();
        File file = new File(USERS_FILE);
        if (!file.exists()) return users;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 5) continue;

                String role = parts[0].trim();

                if (role.equalsIgnoreCase("Admin")) {
                    String id = parts[1].trim();
                    String password = parts[2].trim();
                    String name = parts[3].trim();
                    String email = parts[4].trim();
                    String department = parts.length >= 6 ? parts[5].trim() : "General administration";
                    users.add(new com.example.proyectofinal.Models.Admin(id, password, name, email, department));
                } else if (role.equalsIgnoreCase("Cashier")) {
                    String id = parts[1].trim();
                    String password = parts[2].trim();
                    String name = parts[3].trim();
                    String email = parts[4].trim();
                    String workerId = parts.length >= 6 ? parts[5].trim() : "";
                    users.add(new com.example.proyectofinal.Models.Cashier(id, password, name, email, workerId));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
