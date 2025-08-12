package com.app.infrastructure.security.model;

import com.app.domain.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioAuth {
    private String id;
    private Usuario usuario; // referencia al usuario
    private PasswordHash password;
}
