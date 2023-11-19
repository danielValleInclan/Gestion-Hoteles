package com.gestionhoteles.controller;

import com.gestionhoteles.MainApp;
import com.gestionhoteles.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Iterator;

public class BookingController {

    private MainApp mainApp;
    private Model model;
    private Stage stage;
    private Client client;
    @FXML
    private TableView<Booking> tvBooking;
    @FXML
    private TableColumn<Booking, String> cID;
    @FXML
    private TableColumn<Booking, String> cDate;
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

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public BookingController() {
    }
    @FXML
    private void initialize() {
        // Obtén los valores del enum y conviértelos en una lista observable
        ObservableList<TypeRoom> typeRooms = FXCollections.observableArrayList(TypeRoom.values());
        ObservableList<Regime> regimes = FXCollections.observableArrayList(Regime.values());

        // Establece la lista de opciones en el ChoiceBox
        cbTypeRoom.setItems(typeRooms);
        cbRegime.setItems(regimes);
        // Initialize the person table with the two columns.
        cID.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        cDate.setCellValueFactory(cellData -> cellData.getValue().arrivalDateProperty());

        // Clear client details.
        showDetailsBooking(null);
        // Listen for selection changes and show the client details when changed.
        tvBooking.getSelectionModel().selectedItemProperty().addListener((
                (observableValue, oldValue, newValue) -> showDetailsBooking(newValue)));
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private Booking getSelectBooking(){
        return tvBooking.getSelectionModel().getSelectedItem();
    }
    @FXML
    private void showDefaultDetails(){
        Booking selectBooking = getSelectBooking();
        if (selectBooking != null){
            showDetailsBooking(selectBooking);
        }
    }

    private void showDetailsBooking(Booking booking){
        if (booking != null){
            dpArriveDate.setValue(booking.getArrivalDate());
            dpDepartureDate.setValue(booking.getDepartureDate());
            cbSmoke.setSelected(booking.getSmoke());
            cbTypeRoom.setValue(booking.getTypeRoom());
            cbRegime.setValue(booking.getRegime());
        }else {
            // Cliente es nulo elimina el texto
            dpArriveDate.setValue(null);
            dpDepartureDate.setValue(null);
            cbSmoke.setSelected(false);
            cbTypeRoom.setValue(null);
            cbRegime.setValue(null);
        }
    }
    @FXML
    private void handleNewBooking() {

    }
    @FXML
    private void handleEditBooking() {
        Booking selectBooking = getSelectBooking();
        if (selectBooking != null){
            BookingVO bookingVO = mainApp.getConverter().convertBooking(selectBooking);
            bookingVO.setArrivalDate(dpArriveDate.getValue());
            bookingVO.setDepartureDate(dpDepartureDate.getValue());
            if (cbSmoke.isSelected()) bookingVO.setSmoke(true);
            bookingVO.setTypeRoom(cbTypeRoom.getValue());
            bookingVO.setRegime(cbRegime.getValue());
            bookingVO.setClientDni(client.getDni());
            model.editBookingVO(bookingVO, bookingVO.getId());
            Iterator<Booking> iterator = mainApp.getBookingData().iterator();
            while (iterator.hasNext()) {
                Booking booking = iterator.next();
                // Realizar operaciones en cliente
                if (client.equals(selectBooking)) {
                    iterator.remove(); // Elimina el cliente de la lista
                }
            }
        }else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("No seleccionado");
            alert.setContentText("No has seleccionado ninguna Reserva");
            alert.showAndWait();
        }
    }
    @FXML
    private void handleDeleteBooking() {
    }



    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        tvBooking.setItems(mainApp.getBookingData());
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
