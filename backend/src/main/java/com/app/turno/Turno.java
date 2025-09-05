package com.app.turno;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;
    @Column(name = "fecha_alta")
    private LocalDate fechaAlta;
    @Column(name = "fecha_turno")
    private LocalDateTime fechaTurno;
    @Column(name = "fk_estado")
    private UUID fkEstado;
    @Column(name = "fk_cliente")
    private UUID fkCliente;
    @Column(name = "fk_usuario")
    private UUID fkUsuario;
    @Column(name = "fk_servicio")
    private UUID fkServicio;
    private String detalle;

}
