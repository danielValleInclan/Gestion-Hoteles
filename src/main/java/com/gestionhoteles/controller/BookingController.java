package com.gestionhoteles.controller;

import com.gestionhoteles.MainApp;
import com.gestionhoteles.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.ConnectException;
import java.time.LocalDate;
import java.util.ArrayList;

public class BookingController {

    private MainApp mainApp;
    private Model model;
    private Stage stage;
    private final ObservableList<Booking> bookingData_Client = FXCollections.observableArrayList();
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
    @FXML
    private ImageView ivCheckEdit;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public BookingController() {
    }
    @FXML
    private void initialize() {
        tvBooking.setItems(bookingData_Client);
        // Obtén los valores del enum y conviértelos en una lista observable
        ObservableList<TypeRoom> typeRooms = FXCollections.observableArrayList(TypeRoom.values());
        ObservableList<Regime> regimes = FXCollections.observableArrayList(Regime.values());

        // Establece la lista de opciones en el ChoiceBox
        cbTypeRoom.setItems(typeRooms);
        cbRegime.setItems(regimes);

        // Initialize the booking table with the two columns.
        cID.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        cDate.setCellValueFactory(cellData -> cellData.getValue().arrivalDateProperty());

        // Clear client details.
        showDetailsBooking(null);
        // Listen for selection changes and show the client details when changed.
        tvBooking.getSelectionModel().selectedItemProperty().addListener((
                (observableValue, oldValue, newValue) -> showDetailsBooking(newValue)));
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
            dpArriveDate.setValue(LocalDate.parse(booking.getArrivalDate()));
            dpDepartureDate.setValue(LocalDate.parse(booking.getDepartureDate()));
            cbSmoke.setSelected(Boolean.parseBoolean(booking.getSmoke()));
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
        ivCheckEdit.setVisible(false);
    }
    @FXML
    private void handleNewBooking() throws ExeptionBooking {
        Booking tempBooking = new Booking();
        boolean okClicked = mainApp.showNewBooking(tempBooking, client);
        if (okClicked) {
            bookingData_Client.add(tempBooking); //Añade a la ObservableList de reservas
            model.addBookingVO(mainApp.getConverter().convertBooking(tempBooking));
        }
        tvBooking.getSelectionModel().select(tempBooking);
    }
    @FXML
    private void handleEditBooking() throws ExeptionBooking {
        Booking selectBooking = getSelectBooking();
        if (selectBooking != null){
            BookingVO bookingVO = mainApp.getConverter().convertBooking(selectBooking);
            bookingVO.setId(selectBooking.getId());
            bookingVO.setArrivalDate(dpArriveDate.getValue());
            bookingVO.setDepartureDate(dpDepartureDate.getValue());
            bookingVO.setSmoke(cbSmoke.isSelected());
            bookingVO.setTypeRoom(cbTypeRoom.getValue());
            bookingVO.setRegime(cbRegime.getValue());
            bookingVO.setClientDni(client.getDni());
            bookingVO.setnRoom(1);
            if (mainApp.getConverter().convertBookingVO(bookingVO).equals(selectBooking)){
                model.editBookingVO(bookingVO, model.getLastIdBooking());
                // Nothing changed.
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("No has efectuado cambios");
                alert.showAndWait();
            } else {
                for (Booking b: bookingData_Client){
                    if (b.equals(selectBooking)){
                        bookingData_Client.remove(b);
                        bookingData_Client.add(mainApp.getConverter().convertBookingVO(bookingVO));
                        break;
                    }
                }
                selectBooking = mainApp.getConverter().convertBookingVO(bookingVO);
                tvBooking.getSelectionModel().select(selectBooking);
                ivCheckEdit.setVisible(true);
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

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteBooking() throws ExeptionBooking {
        int selectedIndex = tvBooking.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0){
            Booking booking = tvBooking.getItems().get(selectedIndex);
            model.deleteBookingVO(booking.getId());
            tvBooking.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("No seleccionado");
            alert.setContentText("No has seleccionado ninguna reserva");
            alert.showAndWait();
        }
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    // Crea una tabla solamente del cliente seleccionado
    public void setBookingData_Client() throws ExeptionBooking{
        try {
            ArrayList<BookingVO> bookingVOS = model.GetListBooking_Client(client.getDni());
            ArrayList<Booking> bookings = mainApp.getConverter().convertListBVO(bookingVOS);
            bookingData_Client.addAll(bookings);
        } catch (ExeptionBooking e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al listar a los clientes.");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos");
            alert.showAndWait();
        }
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
