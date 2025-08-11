package com.app.domain.model;
//las entidades de dominio no pueden tener librerias externas
//no pueden tener dependencias de JPA, ni de Spring, ni de nada
//son clases simples, que representan el modelo del negocio
//y que se usan en la capa de dominio
//en este caso, Servicio es una entidad del dominio
public class Servicio {
    private String id;
    private String nombre;
    private Integer duracion_estimada; //minutos
    private float precio;
    private String detalle;

    public Servicio() {
    }

    public Servicio(String id, String nombre, Integer duracion_estimada, float precio, String detalle) {
        this.id = id;
        this.nombre = nombre;
        this.duracion_estimada = duracion_estimada;
        this.precio = precio;
        this.detalle = detalle;
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

    public Integer getDuracion_estimada() {
        return duracion_estimada;
    }

    public void setDuracion_estimada(Integer duracion_estimada) {
        this.duracion_estimada = duracion_estimada;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
    
}
