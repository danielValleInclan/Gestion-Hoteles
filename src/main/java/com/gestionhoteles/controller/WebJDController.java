package com.gestionhoteles.controller;

import com.gestionhoteles.MainApp;
import com.gestionhoteles.model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.File;

/**
 * Clase JavaDoc
 */
public class WebJDController {

    private MainApp mainApp;
    private Model model;
    private Stage stage;
    @FXML
    private WebView  webView;

    @FXML
    private void initialize() {
        WebEngine webEngine = webView.getEngine();

        String indexPathWindows = "ruta windos"; // no tengo windows
        String indexPathLinux = "/home/daniel/dam/Gestion-Hoteles/src/main/java/com/gestionhoteles/docs/index.html";

        File indexFile = new File(indexPathLinux);
        if (indexFile.exists()) {
            String url = indexFile.toURI().toString();
            webEngine.load(url);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al mostrar la documentacion.");
            alert.setTitle("Error con la ruta especificada");
            alert.setContentText("No se mostrar la documentacion del javaDoc");
            alert.showAndWait();
        }
    }


    public MainApp getMainApp() {
        return mainApp;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Stage getScene() {
        return stage;
    }

    public void setStage(Stage dialogeScene) {
        this.stage = dialogeScene;
    }
}
