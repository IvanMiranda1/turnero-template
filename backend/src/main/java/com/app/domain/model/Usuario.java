package com.app.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
//las entidades de dominio no pueden tener librerias externas
//no pueden tener dependencias de JPA, ni de Spring, ni de nada
//son clases simples, que representan el modelo del negocio
//y que se usan en la capa de dominio
//en este caso, Usuario es una entidad del dominio
public class Usuario {
    private String id;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private LocalDateTime fecha_creacion;
    private LocalDate ultima_sesion;

    public Usuario() {
    }

    public Usuario(String id, String nombre, String apellido, String dni, String email, LocalDateTime fecha_creacion, LocalDate ultima_sesion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.fecha_creacion = fecha_creacion;
        this.ultima_sesion = ultima_sesion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public LocalDate getUltima_sesion() {
        return ultima_sesion;
    }

    public void setUltima_sesion(LocalDate ultima_sesion) {
        this.ultima_sesion = ultima_sesion;
    }
}
