package com.gestionhoteles.controller;

import com.gestionhoteles.MainApp;
import com.gestionhoteles.model.Client;
import com.gestionhoteles.model.ClientVO;
import com.gestionhoteles.model.ExceptionClient;
import com.gestionhoteles.model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Objects;

public class MainVIewController {
    private MainApp mainApp;
    private Model model;

    @FXML
    private TableView<Client> clientTable;
    @FXML
    private TableColumn<Client, String> nameColumn;
    @FXML
    private TableColumn<Client, String> lastNameColumn;
    @FXML
    private Label lDni;
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
    @FXML
    private ImageView ivCheckEdit;


    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MainVIewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // Clear client details.
        showClientDetails(null);
        // Listen for selection changes and show the client details when changed.
        clientTable.getSelectionModel().selectedItemProperty().addListener((
                (observableValue, oldValue, newValue) -> showClientDetails(newValue)));
    }

    @FXML
    private void showDefaultDetails(){
        Client selectClient = clientTable.getSelectionModel().getSelectedItem();
        if (selectClient != null){
            showClientDetails(selectClient);
        }
    }

    private void showClientDetails(Client client){
        if (client != null){
            // Rellena la tabla con la información del cliente
            lDni.setText(client.getDni());
            tfName.setText(client.getName());
            tfLastName.setText(client.getLastName());
            tfAddress.setText(client.getAddres());
            tfProvince.setText(client.getProvince());
            tfTown.setText(client.getTown());
        } else {
            // Cliente es nulo elimina el texto
            lDni.setText("");
            tfName.setText("");
            tfLastName.setText("");
            tfAddress.setText("");
            tfProvince.setText("");
            tfTown.setText("");
        }
    }


    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteClient() throws ExceptionClient{
        int selectedIndex = clientTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0){
            Client client = clientTable.getItems().get(selectedIndex);
            model.deleteClienteVO(client);
            clientTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("No seleccionado");
            alert.setContentText("No has seleccionado ningún cliente");
            alert.showAndWait();
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new client.
     */
    @FXML
    private void handleNewClient() throws ExceptionClient {
        Client tempCLient = new Client();
        boolean okClicked = mainApp.showNewClient(tempCLient);
        if (okClicked) {
            mainApp.getClientData().add(tempCLient); //Añade a la ObservableList de personas
            model.addClienteVO(mainApp.getConverter().convertClient(tempCLient));
        }
    }


    @FXML
    private void handleEditClient() throws ExceptionClient {
        Client selectClient = clientTable.getSelectionModel().getSelectedItem();
        if (selectClient != null){
            ClientVO clientVO = mainApp.getConverter().convertClient(selectClient);
            clientVO.setDni(lDni.getText());
            clientVO.setName(tfName.getText());
            clientVO.setLastName(tfLastName.getText());
            clientVO.setAddress(tfAddress.getText());
            clientVO.setTown(tfTown.getText());
            clientVO.setProvince(tfProvince.getText());
            model.editClienteVO(clientVO, selectClient.getDni());
            for (Client c: mainApp.getClientData()){
                if (c.equals(selectClient)){
                    mainApp.getClientData().remove(c);
                    c = mainApp.getConverter().convertClientVO(clientVO);
                    mainApp.getClientData().add(c);
                }
            }
            ivCheckEdit.setVisible(true);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("No seleccionado");
            alert.setContentText("No has seleccionado ningún Cliente");
            alert.showAndWait();
        }
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;

        // Add observable list data to the table
        clientTable.setItems(mainApp.getClientData());
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
