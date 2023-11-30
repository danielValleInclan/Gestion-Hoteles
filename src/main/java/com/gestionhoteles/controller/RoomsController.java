package com.gestionhoteles.controller;

import com.gestionhoteles.MainApp;
import com.gestionhoteles.model.ExeptionBooking;
import com.gestionhoteles.model.Model;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
    private ProgressIndicator pi;
    @FXML
    private ImageView ivNext, ivPrev;
    @FXML
    private Label typeRoom;
    private HashMap<Integer, String> typeRooms = new HashMap<Integer, String>();

    private IntegerProperty valuePIDouble = new SimpleIntegerProperty();
    private IntegerProperty valuePIJunior = new SimpleIntegerProperty();
    private IntegerProperty valuePIDoubleI = new SimpleIntegerProperty();
    private IntegerProperty valuePISuite = new SimpleIntegerProperty();
    private IntegerProperty typeIndicator = new SimpleIntegerProperty();
    private int i;

    @FXML
    private void initialize(){
        imageHashMap = new HashMap<>();
        System.out.println("Ruta double.jpg: " + MainApp.class.getResource("double.jpg"));

        imageHashMap.put(1, new Image(String.valueOf(MainApp.class.getResource("images/double.jpg"))));
        imageHashMap.put(2, new Image(String.valueOf(MainApp.class.getResource("images/junior_suite.jpg"))));
        imageHashMap.put(3, new Image(String.valueOf(MainApp.class.getResource("images/double_indv.jpg"))));
        imageHashMap.put(4, new Image(String.valueOf(MainApp.class.getResource("images/suite.jpg"))));

        // Inicializar la imagen mostrada al iniciar la ventana
        i = 1;
        typeIndicator.set(i);
        ivRoom.setImage(imageHashMap.get(i));

        ivNext.setOnMouseClicked(mouseEvent -> {
            try {
                nextImg();
            } catch (ExeptionBooking e) {
                throw new RuntimeException(e);
            }
        });
        ivPrev.setOnMouseClicked(mouseEvent -> {
            try {
                prevImg();
            } catch (ExeptionBooking e) {
                throw new RuntimeException(e);
            }
        });

        typeRooms.put(1, "Habitación doble");
        typeRooms.put(2, "Junior Suite");
        typeRooms.put(3, "Doble de uso individual");
        typeRooms.put(4, "Habitación Suite");
        typeRoom.setText(typeRooms.get(i));
    }

    @FXML
    private void nextImg() throws ExeptionBooking {
        if (i == 4){
            i = 1;
            ivRoom.setImage(imageHashMap.get(i));
        } else {
            i++;
            ivRoom.setImage(imageHashMap.get(i));
        }
        typeRoom.setText(typeRooms.get(i));
        typeIndicator.set(i);
        updateProgressIcon();
    }

    @FXML
    private void prevImg() throws ExeptionBooking {
        if (i == 1){
            i = 4;
            ivRoom.setImage(imageHashMap.get(i));
        } else {
            i--;
            ivRoom.setImage(imageHashMap.get(i));
        }
        typeRoom.setText(typeRooms.get(i));
        typeIndicator.set(i);
        updateProgressIcon();
    }

    public void updateProgressIcon() throws ExeptionBooking {
        switch (typeIndicator.getValue()){
            case 1: // double suite
                this.valuePIDouble.bind(model.getNumBookingDoubles());
                this.pi.setProgress((double) valuePIDouble.getValue() / 80);
                valuePIDouble.addListener((observableValue, number, t1) -> {
                    pi.setProgress( (double) valuePIDouble.getValue() / 80);
                });
                break;
            case 2: // junior suite
                this.valuePIJunior.bind(model.getNumBookingJunior());
                this.pi.setProgress((double) valuePIJunior.getValue() / 15);
                valuePIJunior.addListener((observableValue, number, t1) -> {
                    pi.setProgress( (double) valuePIJunior.getValue() / 15);
                });
                break;
            case 3: // double individual use suite
                this.valuePIDoubleI.bind(model.getNumBookingDoubleInd());
                this.pi.setProgress((double) valuePIDouble.getValue() / 20);
                valuePIDoubleI.addListener((observableValue, number, t1) -> {
                    pi.setProgress( (double) valuePIDoubleI.getValue() / 20);
                });
                break;
            case 4: // suite
                this.valuePISuite.bind(model.getNumBookingSuite());
                this.pi.setProgress((double) valuePISuite.getValue() / 5);
                valuePISuite.addListener((observableValue, number, t1) -> {
                    pi.setProgress( (double) valuePISuite.getValue() / 5);
                });
                break;
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
