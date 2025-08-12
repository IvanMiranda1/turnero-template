package com.app.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private String id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String dni;
}

