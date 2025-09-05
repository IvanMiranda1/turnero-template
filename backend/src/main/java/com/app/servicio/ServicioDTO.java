package com.app.servicio;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicioDTO {
    public interface OnCreate {}
    public interface OnUpdate {}
    
    @Null(groups = OnCreate.class, message = "No enviar id al crear")
    @NotBlank(groups = OnUpdate.class, message = "Id requerido al actualizar")
    @Pattern(groups = OnUpdate.class, regexp = "^[0-9a-fA-F-]{36}$", message = "Id UUID inválido")
    private String id;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "Nombre requerido")
    @Size(max = 120, message = "Nombre máximo 120 caracteres")
    private String nombre;

    @NotNull(groups = {OnCreate.class, OnUpdate.class}, message = "Duración estimada requerida")
    @Min(value = 1, message = "La duración debe ser mayor a 0 minutos")
    private Integer duracionEstimada; //minutos

    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    @Digits(integer = 8, fraction = 2, message = "Máximo 2 decimales")
    private float precio;

    @Size(max = 500, message = "Detalle máximo 500 caracteres")
    private String detalle;
}
