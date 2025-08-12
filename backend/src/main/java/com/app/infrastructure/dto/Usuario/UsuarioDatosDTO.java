package com.app.infrastructure.dto.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDatosDTO {
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
}
