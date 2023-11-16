package com.gestionhoteles.controller;

import com.gestionhoteles.MainApp;
import com.gestionhoteles.model.Client;
import com.gestionhoteles.model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    private Label dniLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label townLabel;
    @FXML
    private Label provinceLabel;


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


    private void showClientDetails(Client client){
        if (client != null){
            // Rellena la tabla con la información del cliente
            dniLabel.setText(client.getName());
            addressLabel.setText(client.getAddres());
            provinceLabel.setText(client.getProvince());
            townLabel.setText(client.getTown());
        } else {
            // Cliente es nulo elimina el texto
            dniLabel.setText("");
            addressLabel.setText("");
            provinceLabel.setText("");
            townLabel.setText("");
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
