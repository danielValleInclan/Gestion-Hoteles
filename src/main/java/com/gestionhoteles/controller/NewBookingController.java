package com.gestionhoteles.controller;

import com.gestionhoteles.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class NewBookingController {

    private Model model;
    private Stage dialogStage;

    private Client client;
    private Booking booking;
    @FXML
    private DatePicker dpArriveDate;
    @FXML
    private DatePicker dpDepartureDate;
    @FXML
    private CheckBox cbSmoke;
    @FXML
    private ChoiceBox<TypeRoom> cbTypeRoom;
    @FXML
    private ChoiceBox<Regime> cbRegime;

    private boolean okClicked = false;


    @FXML
    private void handleOk(){
        if (isInputValid()){
            booking.setArrivalDate(String.valueOf(dpArriveDate.getValue()));
            booking.setDepartureDate(String.valueOf(dpDepartureDate.getValue()));
            booking.setSmoke(String.valueOf(cbSmoke.isSelected()));
            booking.setTypeRoom(String.valueOf(cbTypeRoom.getValue()));
            booking.setRegime(String.valueOf(cbRegime.getValue()));
            booking.setClientDni(client.getDni());
            booking.setnRoom(String.valueOf(1));
            okClicked = true;
            dialogStage.close();
        }
    }

    private boolean isInputValid(){
        String errorMessage = "";
        if (dpArriveDate.getValue() == null) errorMessage += "Fecha de llegada invalida \n";
        if (dpDepartureDate.getValue() == null) errorMessage += "Fecha de salida inválida! \n";
        if (cbTypeRoom.getValue() == null) errorMessage += "Tipo de habitación invalida! \n";
        if (cbRegime.getValue() == null) errorMessage += "Regimen inválido!! \n";
        if (errorMessage.isEmpty()){
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Campos incorrectos");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setClient(Client client){
        this.client = client;
    }

    public boolean isOkClicked() {
        return true;
    }

    @FXML
    private void initialize() {
        // Obtén los valores del enum y conviértelos en una lista observable
        ObservableList<TypeRoom> typeRooms = FXCollections.observableArrayList(TypeRoom.values());
        ObservableList<Regime> regimes = FXCollections.observableArrayList(Regime.values());

        // Establece la lista de opciones en el ChoiceBox
        cbTypeRoom.setItems(typeRooms);
        cbRegime.setItems(regimes);
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Booking getBooking() {
        return booking;
    }
}
