package com.gestionhoteles.controller;

import com.gestionhoteles.MainApp;
import com.gestionhoteles.model.*;
import com.gestionhoteles.util.ValidateDni;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

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
    private TextField tfDNI;
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
    @FXML
    private Button bBooking;
    @FXML TextField tfSearch;
    @FXML
    private Button bSearch;


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


    private Client getSelectClient(){
        return clientTable.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void showDefaultDetails(){
        Client selectClient = getSelectClient();
        if (selectClient != null){
            showClientDetails(selectClient);
        }
    }

    private void showClientDetails(Client client){
        if (client != null){
            // Rellena la tabla con la información del cliente
            tfDNI.setText(client.getDni());
            tfName.setText(client.getName());
            tfLastName.setText(client.getLastName());
            tfAddress.setText(client.getAddres());
            tfProvince.setText(client.getProvince());
            tfTown.setText(client.getTown());
        } else {
            // Cliente es nulo elimina el texto
            tfDNI.setText("");
            tfName.setText("");
            tfLastName.setText("");
            tfAddress.setText("");
            tfProvince.setText("");
            tfTown.setText("");
        }
        ivCheckEdit.setVisible(false);
    }


    /**
     * Called when the user clicks on the delete button.
     */
    /*
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
     */

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new client.
     */
    @FXML
    private void handleNewClient() throws ExceptionClient {
        Client tempCLient = new Client();
        boolean okClicked = mainApp.showNewClient(tempCLient);
        if (okClicked) {
            mainApp.getClientData().add(tempCLient); //Añade a la ObservableList de clientes
            model.addClienteVO(mainApp.getConverter().convertClient(tempCLient));
        }
        clientTable.getSelectionModel().select(tempCLient);
    }


    @FXML
    private void handleEditClient() throws ExceptionClient {
        Client selectClient = getSelectClient();
        if (selectClient != null){
            ClientVO clientVO = mainApp.getConverter().convertClient(selectClient);
            clientVO.setDni(tfDNI.getText());
            clientVO.setName(tfName.getText());
            clientVO.setLastName(tfLastName.getText());
            clientVO.setAddress(tfAddress.getText());
            clientVO.setTown(tfTown.getText());
            clientVO.setProvince(tfProvince.getText());
            if (mainApp.getConverter().convertClientVO(clientVO).equals(selectClient)){
                model.editClienteVO(clientVO, selectClient.getDni());
                // Nothing changed.
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("No has efectuado cambios");
                alert.showAndWait();
            } else {
                for (Client c: mainApp.getClientData()){
                    if (c.equals(selectClient)){
                        mainApp.getClientData().remove(c);
                        mainApp.getClientData().add(mainApp.getConverter().convertClientVO(clientVO));
                        break;
                    }
                }
                selectClient = mainApp.getConverter().convertClientVO(clientVO);
                clientTable.getSelectionModel().select(selectClient);
                ivCheckEdit.setVisible(true); // Muestra imagen de modificado
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("No seleccionado");
            alert.setContentText("No has seleccionado ningún Cliente");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleSearchBooking() throws ExeptionBooking {
        boolean existDni = false;
        if (validateDni(tfSearch.getText())){
            for (Client c: mainApp.getClientData()){
                if (tfSearch.getText().equals(c.getDni())){
                    mainApp.showBookingView(c);
                    existDni = true;
                    break;
                }
            }
            if (!existDni){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("No existe DNI");
                alert.setContentText("No existe ningún cliente con tal dni");
                alert.showAndWait();
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Formato inválido");
            alert.setContentText("Formato de dni Inválido");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleSearchBookingKey(KeyEvent event) throws ExeptionBooking {
        if (event.getCode().toString().equals("ENTER")) {
            if (validateDni(tfSearch.getText())){
                // Este código se ejecutará cuando se presione Enter
                for (Client c: mainApp.getClientData()){
                    if (tfSearch.getText().equals(c.getDni())){
                        mainApp.showBookingView(c);
                        break;
                    }
                }
            }else {
                // Nothing selected.
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("Formato inválido");
                alert.setContentText("Formato de dni Inválido");
                alert.showAndWait();
            }
        }
    }

    private boolean validateDni(String s){
        return new ValidateDni(s).validate();
    }

    @FXML
    private void openBooking() throws ExeptionBooking {
        Client selectClient = getSelectClient();
        if (selectClient != null){
            mainApp.showBookingView(selectClient);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("No seleccionado");
            alert.setContentText("No has seleccionado ningún Cliente");
            alert.showAndWait();
        }
    }
    @FXML
    private void handleSerachBooking(){

       // mainApp.showBookingView();
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
