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

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getnRoom() {
        return nRoom.get();
    }

    public StringProperty nRoomProperty() {
        return nRoom;
    }

    public void setnRoom(String nRoom) {
        this.nRoom.set(nRoom);
    }

    public boolean getSmoke() {
        String string = this.smoke.toString();
        return Boolean.parseBoolean(string);
    }

    public StringProperty smokeProperty() {
        return smoke;
    }

    public void setSmoke(String smoke) {
        this.smoke.set(smoke);
    }

    public LocalDate getArrivalDate() {
        LocalDate localDate = LocalDate.parse(arrivalDate.get());
        return localDate;
    }

    public StringProperty arrivalDateProperty() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate.set(arrivalDate);
    }

    public LocalDate getDepartureDate() {
        LocalDate localDate = LocalDate.parse(departureDate.get());
        return localDate;
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
