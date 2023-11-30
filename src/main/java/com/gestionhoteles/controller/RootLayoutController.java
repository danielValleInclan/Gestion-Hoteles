package com.gestionhoteles.controller;

import com.gestionhoteles.MainApp;
import com.gestionhoteles.model.ExeptionBooking;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.io.IOException;

public class RootLayoutController {
    private MainApp mainApp;

    @FXML
    private void openRooms() {
        mainApp.showRoomsView();
    }

    @FXML
    private void openGraphic() {
        mainApp.showGraphic();
    }
    @FXML
    private void generateJD(){

        mainApp.showWebJD();

        try {
            // Ruta al directorio fuente de tu proyecto
            String sourceDirectory = "src/main/java";
            // Ruta al directorio donde se generará JavaDoc
            String destinationDirectory = "docs";

            // Ejecuta el comando para generar JavaDoc
            Process process = Runtime.getRuntime().exec("javadoc -d " + destinationDirectory + " -sourcepath " + sourceDirectory);
            // Espera a que el proceso termine
            process.waitFor();

            // Muestra un mensaje de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("JavaDoc Generado");
            alert.setHeaderText(null);
            alert.setContentText("JavaDoc se ha generado exitosamente en el directorio: " + destinationDirectory);
            alert.showAndWait();
        } catch (IOException | InterruptedException e) {
            // Maneja las excepciones
            e.printStackTrace();
        }

    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
}
