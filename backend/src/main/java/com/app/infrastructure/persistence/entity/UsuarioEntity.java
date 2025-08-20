package com.app.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private String id;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    //Ahora rol hace referencia a RolEntity, en vez de usar un string como antes
    @ManyToOne
    @JoinColumn(name = "rol_id", referencedColumnName = "id")
    private RolEntity rol;

    private LocalDate fecha_creacion;
    private LocalDateTime ultima_sesion;
}