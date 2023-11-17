package com.gestionhoteles.controller;

import com.gestionhoteles.MainApp;
import com.gestionhoteles.model.Booking;
import com.gestionhoteles.model.Client;
import com.gestionhoteles.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class BookingController {

    private MainApp mainApp;
    private Model model;
    private Stage stage;
    private Client client;


    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void showDetailsBooking(Booking booking){
        if (booking != null){

        }
    }
    @FXML
    private void handleNewBooking() {
    }
    @FXML
    private void handleEditBooking() {
    }
    @FXML
    private void handleDeleteBooking() {
    }
    @FXML
    private void showDefaultDetails() {
    }



    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
