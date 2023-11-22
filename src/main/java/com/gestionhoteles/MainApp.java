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

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private final Model model = new Model();
    private ObservableList<Client> clientsData = FXCollections.observableArrayList();
    //private ObservableList<Booking> bookingData = FXCollections.observableArrayList();
    private ObservableList<Booking> bookingData_Client;
    Converter converter = new Converter();

    public Converter getConverter(){
        return converter;
    }

    public MainApp(){
        Repository repository = new RepositoryImpl();
        model.setRepository(repository);

        try {
            ArrayList<ClientVO> clientVOS = model.GetListClienteVO();
            ArrayList<Client> clients = converter.convertListCVO(clientVOS);
            clientsData.addAll(clients);
            /*
            ArrayList<BookingVO> bookingVOS = model.GetListBookingVO();
            ArrayList<Booking> bookings = converter.convertListBVO(bookingVOS);
            //bookingData.addAll(bookings);
             */
        } catch (ExceptionClient e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al listar a los clientes.");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos");
            alert.showAndWait();
        }

    }


    public ObservableList<Client> getClientData() {
        return clientsData;
    }

    //public ObservableList<Booking> getBookingData() {
      //  return bookingData;
    //}

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Gestion Holteles");

        initRootLayout();
        showMainView();
    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            // FXMLLoader loader = new FXMLLoader();
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("RootLayout.fxml"));
            // loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showMainView(){
        try {
            // Load main view.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("MainView.fxml"));
            AnchorPane mainView = loader.load();

            // Set main view into the center of root layout.
            rootLayout.setCenter(mainView);

            // Give the controller access to the main app.
            MainVIewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setModel(model);

        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    public boolean showNewClient(Client client){
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("NewClient.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nuevo Cliente");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            NewClientController controller = loader.getController();
            controller.setDialogStage(dialogStage);
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
            throw new RuntimeException();
        }
    }
    public void showBookingView (Client client) throws ExeptionBooking {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("BookingView.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Reservas");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Give the controller access to the main app.
            BookingController controller = loader.getController();
            controller.setMainApp(this);
            controller.setStage(dialogStage);
            controller.setModel(model);
            controller.setClient(client);
            controller.setBookingData_Client();

            dialogStage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException();
        } catch (ExeptionBooking e) {
            throw new ExeptionBooking("No se ha podido realizar la operación");
        }
    }
    public static void main(String[] args) {
        launch();
    }
}
