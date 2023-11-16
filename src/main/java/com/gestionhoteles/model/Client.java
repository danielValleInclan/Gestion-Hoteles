package com.gestionhoteles.model;

import javafx.beans.property.StringProperty;

public class Client {

    private final StringProperty dni, name, lastName, addres, town, province;

    public Client(StringProperty dni, StringProperty name, StringProperty lastName, StringProperty addres, StringProperty town, StringProperty province) {
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.addres = addres;
        this.town = town;
        this.province = province;
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
