package com.app.usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "UUID")
    private UUID id;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    @Column(name = "fk_rol")
    private String  fkRol; // uso un string para el rol, en lugar de Rol como tipo de dato
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Proveedor proveedor;
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;
    @Column(name = "ultima_sesion")
    private LocalDateTime ultimaSesion;

    public enum Proveedor {
        LOCAL,
        GOOGLE,
    }
}