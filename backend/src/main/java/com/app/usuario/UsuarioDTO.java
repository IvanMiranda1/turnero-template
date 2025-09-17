package com.app.usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.app.usuario.Usuario.Proveedor;

import jakarta.validation.constraints.Email;
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
public class UsuarioDTO {
    public interface OnCreate {}
    public interface OnUpdate {}
    
    @Null(groups = OnCreate.class, message = "No enviar id al crear")
    @NotBlank(groups = OnUpdate.class, message = "Id requerido al actualizar")
    @Pattern(groups = OnUpdate.class, regexp = "^[0-9a-fA-F-]{36}$", message = "Id UUID inválido")
    private String id;
    
    @NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "Nombre requerido")
    @Size(max = 120, message = "Nombre máximo 120 caracteres")
    private String nombre;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "Apellido requerido")
    @Size(max = 120, message = "Apellido máximo 120 caracteres")
    private String apellido;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "DNI requerido") 
    @Pattern(groups = {OnCreate.class, OnUpdate.class}, regexp = "^\\d{8}$", message = "DNI debe tener 8 dígitos")
    private String dni;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "Email requerido")
    @Email(groups =      {OnCreate.class, OnUpdate.class}, message = "Formato de email inválido")
    @Pattern(groups = {OnCreate.class, OnUpdate.class}, regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Formato de email inválido")
    @Size(groups = {OnCreate.class, OnUpdate.class}, max = 100, message = "Email máximo 100 caracteres")
    private String email;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "Rol requerido")
    @Pattern(groups = OnUpdate.class, regexp = "^[0-9a-fA-F-]{36}$", message = "Id UUID inválido")
    private String fkRol;

    private Proveedor proveedor;

    @Null(groups = OnCreate.class, message = "No enviar fecha_creacion al crear")
    private LocalDate fechaCreacion;

    @Null(groups = OnUpdate.class, message = "No enviar ultima_sesion al actualizar")
    private LocalDateTime ultimaSesion;
}
