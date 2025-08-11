package com.app.infrastructure.adapter.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.application.usecase.EstadoTurnoService;
import com.app.domain.model.EstadoTurno;
import com.app.infrastructure.dto.EstadoTurnoDTO;

@RestController
@RequestMapping("/estado-turnos")
public class EstadoTurnoController {
    private final EstadoTurnoService estadoTurnoService;

    public EstadoTurnoController(EstadoTurnoService estadoTurnoService) {
        this.estadoTurnoService = estadoTurnoService;
    }

    @PostMapping
    public ResponseEntity<EstadoTurnoDTO> create(@RequestBody EstadoTurnoDTO estadoTurnoDTO) {
        EstadoTurno estadoTurno = toDomain(estadoTurnoDTO);
        // Llama al servicio
        EstadoTurno createdEstadoTurno = estadoTurnoService.create(estadoTurno);
        // Convierte de dominio a DTO
        EstadoTurnoDTO createdEstadoTurnoDTO = toDTO(createdEstadoTurno);
        return ResponseEntity.ok(createdEstadoTurnoDTO);
    }

    private EstadoTurno toDomain(EstadoTurnoDTO dto) {
        return new EstadoTurno(dto.getId(), dto.getNombre());
    }

    private EstadoTurnoDTO toDTO(EstadoTurno estadoTurno) {
        return new EstadoTurnoDTO(estadoTurno.getId(), estadoTurno.getNombre());
    }
}
