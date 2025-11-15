package com.example.proyectofinal.Controllers;

import com.example.proyectofinal.Models.AccountArrange;
import com.example.proyectofinal.Models.Client;
import com.example.proyectofinal.Models.GestionUsuarios;
import com.example.proyectofinal.Models.ManagementAccount;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientViewController {

    @FXML
    private Label usernameClient;

    @FXML
    private AnchorPane layoutClient;

    private Client client;
    private GestionUsuarios gestor;

    public void setClient(Client client){
        this.client = client;
        usernameClient.setText(client.getName() + " " + client.getId());
    }

    public void setGestor(GestionUsuarios gestor){
        this.gestor = gestor;
    }
    private AccountArrange accountArrange;
    public void setAccountArrange(AccountArrange accountArrange) {
        this.accountArrange = accountArrange;
    }

    @FXML
    private void showBalance() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/View/CheckBalance.fxml"));
        Parent root = loader.load();
        CheckBalanceController controller = loader.getController();
        controller.setClient(client);
        controller.setAccountArrange(accountArrange);
        setAnchors(root);
        layoutClient.getChildren().setAll(root);
    }

    @FXML
    private void showTransferView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/View/TransferClient.fxml"));
        Parent root = loader.load();

        TransferClientController controller = loader.getController();
        controller.setClient(client);
        controller.setAccountArrange(accountArrange);

        setAnchors(root);
        layoutClient.getChildren().setAll(root);
    }

    @FXML
    private void onLogOut() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/View/Login.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        ((Stage) layoutClient.getScene().getWindow()).close();
    }

    private void setAnchors(Parent root){
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
    }
}
