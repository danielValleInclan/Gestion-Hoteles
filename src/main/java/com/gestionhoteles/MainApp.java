package com.gestionhoteles;

import com.gestionhoteles.controller.MainVIewController;
import com.gestionhoteles.controller.RootLayoutController;
import com.gestionhoteles.model.Client;
import com.gestionhoteles.model.Model;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private final Model model = new Model();

    private ObservableList<Client> clientsData = FXCollections.observableArrayList();



    public ObservableList<Client> getClientData() {
        return clientsData;
    }

    @Override
    public void start(Stage stage) throws Exception {
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
    public static void main(String[] args) {
        launch();
    }
}
