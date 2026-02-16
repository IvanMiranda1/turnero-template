package com.app.servicio;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//las entidades de dominio no pueden tener librerias externas
//no pueden tener dependencias de JPA, ni de Spring, ni de nada
//son clases simples, que representan el modelo del negocio
//y que se usan en la capa de dominio
//en este caso, Servicio es una entidad del dominio
@Entity
@Table(name = "servicio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servicio {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "UUID")
    private UUID id;
    private String nombre;
    @Column(name = "duracion_estimada")
    private Integer duracionEstimada; //minutos
    private float precio;
    private String detalle;
}
