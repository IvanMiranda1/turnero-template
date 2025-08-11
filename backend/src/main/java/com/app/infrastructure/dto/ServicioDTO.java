package com.app.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicioDTO {
    private String id;
    private String nombre;
    private Integer duracion_estimada; //minutos
    private float precio;
    private String detalle;
}
