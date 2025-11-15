package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.Account;
import com.example.proyectofinal.Models.AccountArrange;
import com.example.proyectofinal.Models.Movement;
import com.example.proyectofinal.Models.MovementArrange;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MovementViewController {

    @FXML
    private TableView<Movement> tblMovements;

    @FXML
    private TextField txtClientId;

    @FXML
    private TextField txtAccountNumber;

    @FXML
    private TableColumn<Movement, String> colDate;

    @FXML
    private TableColumn<Movement, String> colType;

    @FXML
    private TableColumn<Movement, String> colRelated;

    @FXML
    private TableColumn<Movement, Double> colAmount;

    @FXML
    private Button btnBack;

    private String accountNumber;

    private final MovementArrange movementArrange = new MovementArrange();


    @FXML
    public void initialize() {

        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colRelated.setCellValueFactory(new PropertyValueFactory<>("relatedAccount"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }


    public void loadMovementsOfAccount(String accountNumber) {
        this.accountNumber = accountNumber;

        ObservableList<Movement> list =
                FXCollections.observableArrayList(
                        movementArrange.getMovementsOfAccount(accountNumber)
                );

        tblMovements.setItems(list);
    }

    @FXML
    public void onSearch() {

        String accountNum = txtAccountNumber.getText().trim();
        String clientId = txtClientId.getText().trim();

        if (accountNum.isEmpty() || clientId.isEmpty()) {
            System.out.println("You must fill out both fields");
            return;
        }


        AccountArrange arrange = new AccountArrange();
        arrange.loadAccounts();
        Account acc = arrange.findAccount(accountNum);

        if (acc == null || !acc.getClientId().equals(clientId)) {
            System.out.println("The account does not exist or does not belong to this client.");
            tblMovements.setItems(FXCollections.observableArrayList());
            return;
        }


        loadMovementsOfAccount(accountNum);
    }



    @FXML
    public void onBack() {
        txtClientId.clear();
        txtAccountNumber.clear();
        tblMovements.getItems().clear();
        tblMovements.getSelectionModel().clearSelection();
    }

}
