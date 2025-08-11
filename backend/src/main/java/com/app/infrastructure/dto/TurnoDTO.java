package com.app.infrastructure.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurnoDTO {
    private String id;
    private LocalDate fecha_alta;
    private LocalDateTime fecha_turno;
    private String fk_estado;
    private String fk_cliente;
    private String fk_usuario;
    private String fk_servicio;
    private String detalle;
}
