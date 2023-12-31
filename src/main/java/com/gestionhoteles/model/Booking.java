package com.gestionhoteles.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Booking {
    private StringProperty id, nRoom, smoke, arrivalDate, departureDate, clientDni, typeRoom, regime;

    public Booking(String id, String nRoom, String smoke, String arrivalDate, String departureDate, String clientDni,
                   String typeRoom, String regime) {
        this.id = new SimpleStringProperty(id);
        this.nRoom = new SimpleStringProperty(nRoom);
        this.smoke = new SimpleStringProperty(smoke);
        this.arrivalDate = new SimpleStringProperty(arrivalDate);
        this.departureDate = new SimpleStringProperty(departureDate);
        this.clientDni = new SimpleStringProperty(clientDni);
        this.typeRoom = new SimpleStringProperty(typeRoom);
        this.regime = new SimpleStringProperty(regime);
    }
    public Booking() {
        this(null, null, null, null, null, null, null, null);
    }

    public int getId() {
        return Integer.parseInt(this.id.get());
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public int getnRoom() {
        return Integer.parseInt(nRoom.get());
    }

    public StringProperty nRoomProperty() {
        return nRoom;
    }

    public void setnRoom(String nRoom) {
        this.nRoom.set(nRoom);
    }

    public String getSmoke() {
        return smoke.get();
    }

    public StringProperty smokeProperty() {
        return smoke;
    }

    public void setSmoke(String smoke) {
        this.smoke.set(smoke);
    }

    public String getArrivalDate() {
        return arrivalDate.get();
    }

    public StringProperty arrivalDateProperty() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate.set(arrivalDate);
    }

    public String getDepartureDate() {
        return this.departureDate.get();
    }

    public StringProperty departureDateProperty() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate.set(departureDate);
    }

    public String getClientDni() {
        return clientDni.get();
    }

    public StringProperty clientDniProperty() {
        return clientDni;
    }

    public void setClientDni(String clientDni) {
        this.clientDni.set(clientDni);
    }

    public TypeRoom getTypeRoom() {
        return TypeRoom.valueOf(typeRoom.get());
    }

    public StringProperty typeRoomProperty() {
        return typeRoom;
    }

    public void setTypeRoom(String typeRoom) {
        this.typeRoom.set(typeRoom);
    }

    public Regime getRegime() {
        return Regime.valueOf(regime.get());
    }

    public StringProperty regimeProperty() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime.set(regime);
    }
}
