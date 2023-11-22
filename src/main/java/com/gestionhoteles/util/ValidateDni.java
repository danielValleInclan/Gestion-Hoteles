package com.gestionhoteles.util;

/**
 Clase que permite validar un DNI.
 Se crea un objeto del tipo ValidadorDNI y se le pasa un String a validar.
 @return true si DNI es correcto.
 Desarrollado por Manuel Mato.
 */

public class ValidateDni {
    private String dni;

    public ValidateDni(String dni) {
        this.dni = dni;
    }


    public boolean validate() {


        String lowerLetter = ""; //Guardaremos la letra introducida en formato mayúscula

        // Aquí excluimos cadenas distintas a 9 caracteres que debe tener un dni y también si el último caracter no es una letra
        if(dni.length() != 9 || !Character.isLetter(this.dni.charAt(8))) {
            return false;
        }


        // Al superar la primera restricción, la letra la pasamos a mayúscula
        lowerLetter = (this.dni.substring(8)).toUpperCase();

        // Por último validamos que sólo tengo 8 dígitos entre los 8 primeros caracteres y que la letra introducida es igual a la de la ecuación
        // Llamamos a los métodos privados de la clase justNumbers() y letterDni()
        if(justNumbers() && dniLetter().equals(lowerLetter)) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean justNumbers() {

        int i, j = 0;
        String num = ""; // Es el número que se comprueba uno a uno por si hay alguna letra entre los 8 primeros dígitos
        String myDni = ""; // Guardamos en una cadena los números para después calcular la letra
        String[] oneNine = {"0","1","2","3","4","5","6","7","8","9"};

        for(i = 0; i < this.dni.length() - 1; i++) {
            num = this.dni.substring(i, i+1);

            for(j = 0; j < oneNine.length; j++) {
                if(num.equals(oneNine[j])) {
                    myDni += oneNine[j];
                }
            }
        }

        if(myDni.length() != 8) {
            return false;
        }
        else {
            return true;
        }
    }

    private String dniLetter() {
        // El método es privado porque lo voy a usar internamente en esta clase, no se necesita fuera de ella

        // pasar myNum a integer
        int myNum = Integer.parseInt(this.dni.substring(0,8));
        int rest = 0;
        String myLetter = "";
        String[] setLetter = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

        rest = myNum % 23;

        myLetter = setLetter[rest];

        return myLetter;
    }
}
