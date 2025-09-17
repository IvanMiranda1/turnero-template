package com.app.estadoTurno;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoTurnoDTO {
    public interface OnCreate {}
    public interface OnUpdate {}

    @Null(groups = OnCreate.class, message = "No enviar id al crear")
    @NotBlank(groups = OnUpdate.class, message = "Id requerido al actualizar")
    @Pattern(groups = OnUpdate.class, regexp = "^[0-9a-fA-F-]{36}$", message = "Id UUID inválido")
    private String id;
    
    @NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "Nombre requerido")
    @Size(max = 60, message = "Nombre máximo 120 caracteres")
    private String nombre;
    
}