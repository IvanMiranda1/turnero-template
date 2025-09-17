package com.app.turno;

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

import com.app.turno.TurnoDTO.OnCreate;
import com.app.turno.TurnoDTO.OnUpdate;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/turnos")
public class TurnoController {
    private final TurnoService service;

    public TurnoController(TurnoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TurnoDTO> create(@Validated(OnCreate.class) @RequestBody TurnoDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<TurnoDTO> update(@Validated(OnUpdate.class) @RequestBody TurnoDTO dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/date")
    public ResponseEntity<List<TurnoDTO>> findByDate(String date) {
        return ResponseEntity.ok(service.findByDate(date));
    }

    @GetMapping("/estado") // localhost:8080/api/turnos/estado?param=Pendiente
    public List<TurnoDTO> findByEstado(@RequestParam String param) {
        return service.findByEstado(param);
    }

    @GetMapping("/cliente")
    public ResponseEntity<List<TurnoDTO>> findByClienteId(UUID clienteId) {
        return ResponseEntity.ok(service.findByClienteId(clienteId));
    }
    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<TurnoDTO>> findByUsuarioId(@PathVariable UUID usuarioId) {
        return ResponseEntity.ok(service.findByUsuarioId(usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
