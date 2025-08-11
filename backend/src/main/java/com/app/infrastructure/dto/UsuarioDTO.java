package com.app.infrastructure.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UsuarioDTO {
    private String id;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private LocalDateTime fecha_creacion;
    private LocalDate ultima_sesion;
}
