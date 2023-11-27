package com.gestionhoteles.controller;

import com.gestionhoteles.MainApp;
import com.gestionhoteles.model.Model;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.HashMap;

public class RoomsController {

    private MainApp mainApp;
    private Model model;
    private Stage stage;

    private HashMap<Integer, Image> imageHashMap;

    @FXML
    private ImageView ivRoom;

    @FXML
    private ProgressBar pbHabitaciones;
    @FXML
    private ProgressIndicator pi;
    @FXML
    private ImageView ivNext, ivPrev;
    private IntegerProperty valuePI = new SimpleIntegerProperty();
    private int i;

    @FXML
    private void initialize(){
        imageHashMap = new HashMap<>();
        System.out.println("Ruta double.jpg: " + MainApp.class.getResource("double.jpg"));

        imageHashMap.put(1, new Image(String.valueOf(MainApp.class.getResource("double.jpg"))));
        imageHashMap.put(2, new Image(String.valueOf(MainApp.class.getResource("junior_suite.jpg"))));
        imageHashMap.put(3, new Image(String.valueOf(MainApp.class.getResource("double_indv.jpg"))));
        imageHashMap.put(4, new Image(String.valueOf(MainApp.class.getResource("suite.jpg"))));

        // Inicializar la imagen mostrada al iniciar la ventana
        i = 1;
        ivRoom.setImage(imageHashMap.get(i));

        ivNext.setOnMouseClicked(mouseEvent -> nextImg());
        ivPrev.setOnMouseClicked(mouseEvent -> prevImg());

    }

    @FXML
    private void nextImg(){
        if (i == 4){
            i = 1;
            ivRoom.setImage(imageHashMap.get(i));
        } else {
            i++;
            ivRoom.setImage(imageHashMap.get(i));
        }
        ivRoom.setFitHeight(460);
        ivRoom.setFitWidth(720);
    }

    @FXML
    private void prevImg(){
        if (i == 1){
            i = 4;
            ivRoom.setImage(imageHashMap.get(i));
        } else {
            i--;
            ivRoom.setImage(imageHashMap.get(i));
        }
    }

    public void updateProgressIcon() {
        this.valuePI.bind(model.getNumBooking());
        this.pi.setProgress((double) valuePI.getValue() / 50);
        this.pi.setProgress(Double.parseDouble(valuePI.getValue()+ "/50"));
        valuePI.addListener((observableValue, number, t1) -> {
            pi.setProgress( (double) valuePI.getValue() / 50);
        });
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setModel(Model model) {
        this.model = model;
    }

}
