package com.gestionhoteles.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client {

    private final StringProperty dni, name, lastName, addres, town, province;

    public Client(String dni, String name, String lastName, String addres, String town, String province) {
        this.dni = new SimpleStringProperty(dni);
        this.name = new SimpleStringProperty(name);
        this.lastName = new SimpleStringProperty(lastName);
        this.addres = new SimpleStringProperty(addres);
        this.town = new SimpleStringProperty(town);
        this.province = new SimpleStringProperty(province);
    }

    public Client(){
        this(null, null,null,null,null,null);
    }

    public String getDni() {
        return dni.get();
    }

    public StringProperty dniProperty() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni.set(dni);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getAddres() {
        return addres.get();
    }

    public StringProperty addresProperty() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres.set(addres);
    }

    public String getTown() {
        return town.get();
    }

    public StringProperty townProperty() {
        return town;
    }

    public void setTown(String town) {
        this.town.set(town);
    }

    public String getProvince() {
        return province.get();
    }

    public StringProperty provinceProperty() {
        return province;
    }

    public void setProvince(String province) {
        this.province.set(province);
    }
}
