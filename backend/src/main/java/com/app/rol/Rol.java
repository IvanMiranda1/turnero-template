package com.app.rol;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rol")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "UUID")
    private UUID id;
    @Column(name = "nombre_rol")
    private String nombreRol;
}
