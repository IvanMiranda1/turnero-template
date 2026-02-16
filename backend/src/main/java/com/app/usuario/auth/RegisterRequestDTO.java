package com.app.usuario.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {
    String dni;
    String password;
    String nombre;
    String apellido;
    String email;
}
