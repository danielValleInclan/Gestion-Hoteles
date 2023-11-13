package com.gestionhoteles.model;

public class ExceptionClient extends Exception{
    String msg;

    public ExceptionClient(){}
    public ExceptionClient(String msg){
        this.msg = msg;
    }
    public String printMsg(){
        return this.msg;
    }
}
