package com.gestionhoteles;

import com.gestionhoteles.controller.*;
import com.gestionhoteles.model.*;
import com.gestionhoteles.model.repository.Repository;
import com.gestionhoteles.model.repository.impl.RepositoryImpl;
import com.gestionhoteles.util.Converter;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase principal de la aplicación Gestion Hoteles.
 * Extiende la clase `Application` de JavaFX.
 */
public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private final Model model = new Model();
    private ObservableList<Client> clientsData = FXCollections.observableArrayList();
    private ObservableList<Booking> bookingData = FXCollections.observableArrayList();
    Converter converter = new Converter();

    /**
     * Retorna el objeto Converter utilizado en la aplicación.
     * @return El objeto Converter.
     */
    public Converter getConverter() {
        return converter;
    }

    /**
     * Constructor de la clase MainApp.
     * Inicializa la aplicación y carga datos iniciales desde la base de datos.
     */
    public MainApp() {
        Repository repository = new RepositoryImpl();
        model.setRepository(repository);

        try {
            ArrayList<ClientVO> clientVOS = model.GetListClienteVO();
            ArrayList<Client> clients = converter.convertListCVO(clientVOS);
            clientsData.addAll(clients);
            ArrayList<BookingVO> bookingVOS = model.GetListBookingVO();
            ArrayList<Booking> bookings = converter.convertListBVO(bookingVOS);
            bookingData.addAll(bookings);
        } catch (ExceptionClient e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al listar a los clientes.");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos");
            alert.showAndWait();
        } catch (ExeptionBooking e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Retorna la lista observable de clientes utilizada en la aplicación.
     * @return La lista observable de clientes.
     */
    public ObservableList<Client> getClientData() {
        return clientsData;
    }

    /**
     * Método principal que se llama al iniciar la aplicación.
     * @param stage El escenario principal de la aplicación.
     */
    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Gestion Holteles");

        initRootLayout();
        showMainView();
    }

    /**
     * Inicializa el diseño de la raíz de la aplicación cargando el archivo FXML correspondiente.
     */
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Muestra la vista principal de la aplicación cargando el archivo FXML correspondiente.
     */
    public void showMainView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("MainView.fxml"));
            AnchorPane mainView = loader.load();

            rootLayout.setCenter(mainView);

            MainVIewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setModel(model);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Muestra una ventana para agregar un nuevo cliente.
     * @param client El cliente a agregar (puede ser null si es un nuevo cliente).
     * @return true si el usuario hizo clic en Aceptar, false si no.
     */
    public boolean showNewClient(Client client) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("NewClient.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nuevo Cliente");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            NewClientController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            controller.setModel(model);
            controller.setClient(client);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public boolean showNewBooking(Booking booking, Client client){
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("NewBookingView.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nueva Reserva");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            NewBookingController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setModel(model);
            controller.setClient(client);
            controller.setBooking(booking);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Muestra la vista de reservas para un cliente específico.
     * @param client El cliente para el cual mostrar las reservas.
     * @throws ExeptionBooking Si ocurre un error al obtener las reservas.
     */
    public void showBookingView(Client client) throws ExeptionBooking {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("BookingView.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Reservas");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            BookingController controller = loader.getController();
            controller.setMainApp(this);
            controller.setStage(dialogStage);
            controller.setModel(model);
            controller.setClient(client);
            controller.setBookingData_Client();

            dialogStage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ExeptionBooking e) {
            throw new ExeptionBooking("No se ha podido realizar la operación");
        }
    }

    /**
     * Muestra la vista de habitaciones y su disponibilidad.
     */
    public void showRoomsView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RoomsView.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Habitaciones");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            RoomsController controller = loader.getController();
            controller.setMainApp(this);
            controller.setStage(dialogStage);
            controller.setModel(model);
            controller.updateProgressIcon();

            dialogStage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ExeptionBooking e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Muestra la vista gráfica de las reservas.
     */
    public void showGraphic() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("GraphicView.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Gráfico");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            GraphicController controller = loader.getController();
            controller.setMainApp(this);
            controller.setStage(dialogStage);
            controller.setModel(model);
            controller.setBookings(bookingData);

            dialogStage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Muestra la vista del JavaDoc de la aplicación.
     */
    public void showWebJD() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("WebJD.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("JavaDoc");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            WebJDController controller = loader.getController();
            controller.setMainApp(this);
            controller.setStage(dialogStage);
            controller.setModel(model);

            dialogStage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método principal que lanza la aplicación.
     * @param args Argumentos de la línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        launch();
    }
}
