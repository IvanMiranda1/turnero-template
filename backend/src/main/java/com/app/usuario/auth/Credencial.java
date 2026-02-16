package com.app.usuario.auth;

import java.util.UUID;

import com.app.usuario.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
public class Credencial {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash; //almacena solo el hash

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Usuario usuario;
}
