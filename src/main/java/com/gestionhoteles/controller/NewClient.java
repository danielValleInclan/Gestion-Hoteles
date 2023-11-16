package com.gestionhoteles.controller;

import com.gestionhoteles.model.Client;
import com.gestionhoteles.model.ExceptionClient;
import com.gestionhoteles.model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewClient {
    @FXML
    private TextField tfDni;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfAddress;
    @FXML
    private TextField tfTown;
    @FXML
    private TextField tfProvince;

    private boolean okClicked = false;
    private Model model;
    private Stage stage;
    private Client client;


    @FXML
    private void handleOk() throws ExceptionClient{
        if (isInputValid()){
            client.setDni(tfDni.getText());
            client.setName(tfName.getText());
            client.setLastName(tfLastName.getText());
            client.setAddres(tfAddress.getText());
            client.setTown(tfTown.getText());
            client.setProvince(tfProvince.getText());

            okClicked = true;
            stage.close();
        }
    }

    private boolean isInputValid(){
        String errorMessage = "";
        if (tfDni.getText() == null || tfDni.getText().isEmpty()) errorMessage += "Nombre inválido \n";
        if (tfName.getText() == null || tfName.getText().isEmpty()) errorMessage += "Apellido inválido! \n";
        if (tfLastName.getText() == null || tfLastName.getText().isEmpty()) errorMessage += "DNI inválido! \n";
        if (tfAddress.getText() == null || tfAddress.getText().isEmpty()) errorMessage += "Dirección inválida! \n";
        if (tfTown.getText() == null || tfTown.getText().isEmpty()) errorMessage += "Localidad inválida!! \n";
        if (tfProvince.getText() == null || tfProvince.getText().isEmpty()) errorMessage += "Provincia! \n";
        if (errorMessage.isEmpty()){
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Campos incorrectos");
            alert.setContentText("Por favor introduce datos válidos");
            alert.showAndWait();
            return false;
        }
    }


    public boolean isOkClicked() {
        return okClicked;
    }

    public void setClient(Client client){
        this.client = client;

        tfDni.setText(client.getDni());
        tfName.setText(client.getName());
        tfLastName.setText(client.getLastName());
        tfAddress.setText(client.getAddres());
        tfTown.setText(client.getTown());
        tfProvince.setText(client.getProvince());
    }

    @FXML
    private void handleCancel() {
        stage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.stage = dialogStage;
    }

    public void setModel(Model model){
        this.model = model;
    }

    @FXML
    private void initialize() {
    }
}
