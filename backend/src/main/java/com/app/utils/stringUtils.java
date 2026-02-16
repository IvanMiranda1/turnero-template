package com.app.utils;

public class stringUtils {
    private stringUtils() {}

    public static String capitalizar(String texto){
        if(texto==null || texto.isEmpty()) return texto;
        return texto.substring(0,1).toUpperCase() + texto.substring(1).toLowerCase();
    }
}
