package com.app.domain.util;

public class RegexUtils {
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    public static final String TELEFONO_REGEX = "^\\d{8,15}$";
    public static final String DNI_REGEX = "^\\d{7,8}$";
    //
    public static final String NOMBRE_APELLIDO_REGEX = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]{2,}$";
}
