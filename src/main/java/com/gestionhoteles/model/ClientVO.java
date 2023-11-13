package com.gestionhoteles.model;

public class ClientVO {
    private String dni, name, lastName, address, town, province;

    public ClientVO(String dni, String name, String apellido, String direccion, String localidad,
                    String provincia) {
        this.dni = dni;
        this.name = name;
        this.lastName = apellido;
        this.address = direccion;
        this.town = localidad;
        this.province = provincia;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
