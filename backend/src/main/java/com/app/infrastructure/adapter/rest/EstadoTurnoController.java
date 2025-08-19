package com.app.infrastructure.adapter.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PutMapping("/{id}")
    public ResponseEntity<EstadoTurnoDTO> update(@RequestBody EstadoTurnoDTO estadoTurnoDTO) {
        EstadoTurno estadoTurno = toDomain(estadoTurnoDTO);
        EstadoTurno updated = estadoTurnoService.update(estadoTurno);
        return ResponseEntity.ok(toDTO(updated));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoTurnoDTO> getById(@PathVariable String id) {
        EstadoTurno estadoTurno = estadoTurnoService.findById(id);
        if (estadoTurno == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDTO(estadoTurno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        estadoTurnoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EstadoTurnoDTO>> getAll() {
        List<EstadoTurno> estadoTurnos = estadoTurnoService.findAll();
        List<EstadoTurnoDTO> dtos = estadoTurnos.stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/search")
    public ResponseEntity<List<EstadoTurnoDTO>> findByNombre(@PathVariable String nombre) {
        List<EstadoTurnoDTO> dtos = estadoTurnoService.findByNombre(nombre)
                .stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }


    private EstadoTurno toDomain(EstadoTurnoDTO dto) {
        return new EstadoTurno(dto.getId(), dto.getNombre());
    }

    private EstadoTurnoDTO toDTO(EstadoTurno estadoTurno) {
        return new EstadoTurnoDTO(estadoTurno.getId(), estadoTurno.getNombre());
    }
}
