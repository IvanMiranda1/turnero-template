package com.app.turno;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.app.cliente.Cliente;
import com.app.estadoTurno.EstadoTurno;
import com.app.servicio.Servicio;
import com.app.usuario.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "turno")
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
    @ManyToOne
    @JoinColumn(name = "fk_estado")
    private EstadoTurno estadoTurno;
    @ManyToOne
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "fk_servicio")
    private Servicio servicio;
    private String detalle;

}
