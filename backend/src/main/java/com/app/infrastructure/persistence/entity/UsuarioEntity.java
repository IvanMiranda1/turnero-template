package com.app.infrastructure.persistence.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {
    private String id;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private LocalDateTime fecha_creacion;
    private LocalDate ultima_sesion;
    
}
