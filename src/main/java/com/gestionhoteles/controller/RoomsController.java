package com.gestionhoteles.controller;

import com.gestionhoteles.MainApp;
import com.gestionhoteles.model.Model;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
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
    private Button bNext;

    @FXML
    private Button bPrev;
    private int i;

    @FXML
    private void initialize(){
        imageHashMap = new HashMap<>();

        imageHashMap.put(1, new Image(String.valueOf(getClass().getResource("doble.jpg"))));
        imageHashMap.put(2, new Image("dobleInd.jpeg"));
        imageHashMap.put(3, new Image("juniorSuite.jpg"));
        imageHashMap.put(4, new Image("suite.jpg"));

        // Inicializar la imagen mostrada al iniciar la ventana
        i = 1;
        ivRoom.setImage(imageHashMap.get(i));
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
