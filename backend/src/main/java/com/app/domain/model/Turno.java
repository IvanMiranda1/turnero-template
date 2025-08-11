package com.app.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
//las entidades de dominio no pueden tener librerias externas
//no pueden tener dependencias de JPA, ni de Spring, ni de nada
//son clases simples, que representan el modelo del negocio
//y que se usan en la capa de dominio
//en este caso, Turno es una entidad del dominio
public class Turno {
    private String id;
    private LocalDate fecha_alta;
    private LocalDateTime fecha_turno;
    private String fk_estado;
    private String fk_cliente;
    private String fk_usuario;
    private String fk_servicio;
    private String detalle;

    public Turno() {
    }
    
    public Turno(String id, LocalDate fecha_alta, LocalDateTime fecha_turno, String fk_estado, String fk_cliente, String fk_usuario,
            String fk_servicio, String detalle) {
        this.id = id;
        this.fecha_alta = fecha_alta;
        this.fecha_turno = fecha_turno;
        this.fk_estado = fk_estado;
        this.fk_cliente = fk_cliente;
        this.fk_usuario = fk_usuario;
        this.fk_servicio = fk_servicio;
        this.detalle = detalle;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public LocalDate getFecha_alta() {
        return fecha_alta;
    }
    public void setFecha_alta(LocalDate fecha_alta) {
        this.fecha_alta = fecha_alta;
    }
    public LocalDateTime getFecha_turno() {
        return fecha_turno;
    }
    public void setFecha_turno(LocalDateTime fecha_turno) {
        this.fecha_turno = fecha_turno;
    }
    public String getFk_estado() {
        return fk_estado;
    }
    public void setFk_estado(String fk_estado) {
        this.fk_estado = fk_estado;
    }
    public String getFk_cliente() {
        return fk_cliente;
    }
    public void setFk_cliente(String fk_cliente) {
        this.fk_cliente = fk_cliente;
    }
    public String getFk_usuario() {
        return fk_usuario;
    }
    public void setFk_usuario(String fk_usuario) {
        this.fk_usuario = fk_usuario;
    }
    public String getFk_servicio() {
        return fk_servicio;
    }
    public void setFk_servicio(String fk_servicio) {
        this.fk_servicio = fk_servicio;
    }
    public String getDetalle() {
        return detalle;
    }
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
