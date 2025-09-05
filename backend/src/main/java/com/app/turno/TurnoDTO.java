package com.app.turno;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.FutureOrPresent;
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
public class TurnoDTO {
    public interface OnCreate {}
    public interface OnUpdate {}

    @Null(groups = OnCreate.class, message = "No enviar id al crear")
    @NotBlank(groups = OnUpdate.class, message = "Id requerido al actualizar")
    @Pattern(groups = OnUpdate.class, regexp = "^[0-9a-fA-F-]{36}$", message = "Id UUID inválido")
    private String id;

    @NotNull(groups = {OnCreate.class, OnUpdate.class}, message = "Fecha de alta requerida")
    private LocalDate fechaAlta;

    @NotNull(groups = {OnCreate.class, OnUpdate.class}, message = "Fecha de turno requerida")
    @FutureOrPresent(message = "La fecha de turno debe ser hoy o futura")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime fechaTurno;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "Estado requerido")
    @Pattern(groups = OnUpdate.class, regexp = "^[0-9a-fA-F-]{36}$", message = "Id UUID inválido")
    private String fkEstado;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "Cliente requerido")
    @Pattern(groups = OnUpdate.class, regexp = "^[0-9a-fA-F-]{36}$", message = "Id UUID inválido")
    private String fkCliente;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "Usuario requerido")
    @Pattern(groups = OnUpdate.class, regexp = "^[0-9a-fA-F-]{36}$", message = "Id UUID inválido")
    private String fkUsuario;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "Servicio requerido")
    @Pattern(groups = OnUpdate.class, regexp = "^[0-9a-fA-F-]{36}$", message = "Id UUID inválido")
    private String fkServicio;

    @Size(max = 500, message = "Detalle máximo 500 caracteres")
    private String detalle;
}
