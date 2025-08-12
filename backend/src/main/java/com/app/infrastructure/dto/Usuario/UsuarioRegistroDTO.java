package com.app.infrastructure.dto.Usuario;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRegistroDTO {
    private UsuarioDatosDTO usuario;
    private UsuarioCredencialesDTO auth;
}
