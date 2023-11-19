package com.gestionhoteles.model;

import java.time.LocalDate;

public class BookingVO {
    private int id, nRoom;
    private boolean smoke;
    private LocalDate arrivalDate, departureDate;
    private String clientDni;
    private TypeRoom typeRoom;

    private Regime regime;

    public BookingVO(int id, int nRoom, boolean smoke, LocalDate arrivalDate, LocalDate departureDate, String clientDni, TypeRoom typeRoom, Regime regime) {
        this.id = id;
        this.nRoom = nRoom;
        this.smoke = smoke;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.clientDni = clientDni;
        this.typeRoom = typeRoom;
        this.regime = regime;
    }

    public BookingVO(){
    }

    public String getStringTRoom(){
        return typeRoom.name();
    }

    public String getStringRegime(){
        return regime.name();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getnRoom() {
        return nRoom;
    }

    public void setnRoom(int nRoom) {
        this.nRoom = nRoom;
    }

    public boolean getSmoke() {
        return smoke;
    }

    public void setSmoke(boolean smoke) {
        this.smoke = smoke;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public String getClientDni() {
        return clientDni;
    }

    public void setClientDni(String clientDni) {
        this.clientDni = clientDni;
    }

    public TypeRoom getTypeRoom() {
        return typeRoom;
    }

    public void setTypeRoom(TypeRoom typeRoom) {
        this.typeRoom = typeRoom;
    }

    public Regime getRegime() {
        return regime;
    }

    public void setRegime(Regime regime) {
        this.regime = regime;
    }
}
