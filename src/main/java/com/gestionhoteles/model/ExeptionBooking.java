package com.gestionhoteles.model;

public class ExeptionBooking extends Exception{
    String msg;

    public ExeptionBooking(){

    }

    public ExeptionBooking(String msg){
        this.msg = msg;
    }

    public String printMsg(){
        return this.msg;
    }

}
