package com.app.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataNormalizer {
    private DataNormalizer() {
        // Constructor privado para evitar instanciación
    }

    //normaliza strings: trim y null si vacio
    public static String clean(String s){
        if(s == null) return null;
        String t = s.trim();
        return t.isEmpty() ? null : t;
    }

    //Capitaliza la primera letra y el resto minuscula
    public static String capitalize(String s) {
        s = clean(s);
        if (s==null) return null;
        return s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
    }
    //Normaliza email (trim y lower)
    public static String cleanEmail(String s) {
        s = clean(s);
        return s == null ? null : s.toLowerCase();
    }

    public static String formatLocalDate(LocalDate d, String pattern) {
        if (d == null) return null;
        return d.format(DateTimeFormatter.ofPattern(pattern));
    }
    
    public static LocalDate parseLocalDate(String s, String pattern) {
        if (s == null) return null;
        return LocalDate.parse(s.trim(), DateTimeFormatter.ofPattern(pattern));
    }

    public static String normalizeDni(String dni) {
        if (dni == null) return null;
        String clean = dni.replaceAll("[^\\d]", ""); // solo dígitos
        return clean.isEmpty() ? null : clean;
    }

    public static String capitalizarPalabras(String texto) {
        if (texto == null || texto.isEmpty())
            return texto; String[] p = texto.trim().split("\\s+");
        StringBuilder r = new StringBuilder();
        for (String x: p) r.append(capitalize(x)).append(" ");
        return r.toString().trim();
    }
}
