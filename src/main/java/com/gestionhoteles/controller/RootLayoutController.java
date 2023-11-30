package com.gestionhoteles.controller;

import com.gestionhoteles.MainApp;
import com.gestionhoteles.model.ExeptionBooking;
import javafx.fxml.FXML;

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

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
}
