package com.app.infrastructure.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioAuth {
    private String id;
    private String password;
    private String fk_rol;
}
