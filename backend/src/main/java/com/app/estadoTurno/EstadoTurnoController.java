package com.app.estadoTurno;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.estadoTurno.EstadoTurnoDTO.OnCreate;
import com.app.estadoTurno.EstadoTurnoDTO.OnUpdate;


@RestController
@RequestMapping("/estado-turnos")
public class EstadoTurnoController {
    private final EstadoTurnoService service;

    public EstadoTurnoController(EstadoTurnoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EstadoTurnoDTO> create(@Validated(OnCreate.class) @RequestBody EstadoTurnoDTO dto) {
         return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoTurnoDTO> update(@Validated(OnUpdate.class) @RequestBody EstadoTurnoDTO dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoTurnoDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EstadoTurnoDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<EstadoTurnoDTO>> findByNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(service.findByNombre(nombre));
    }
}
