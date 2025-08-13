package com.app.infrastructure.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
private String id;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String rol;
    private LocalDate fecha_creacion;
    private LocalDateTime ultima_sesion;
}
