package com.app.infrastructure.adapter.rest;

import com.app.application.usecase.RolService;
import com.app.domain.model.Rol;
import com.app.infrastructure.dto.RolDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RolController {
    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @PostMapping
    public ResponseEntity<RolDTO> create(@RequestBody RolDTO rolDTO) {
        Rol rol = toDomain(rolDTO);
        Rol createdRol = rolService.create(rol);
        return ResponseEntity.ok(toDTO(createdRol));
    }

    @PutMapping("/update")
    public ResponseEntity<RolDTO> update(@RequestBody RolDTO rolDTO) {
        Rol rol = toDomain(rolDTO);
        Rol updatedRol = rolService.update(rol);
        return ResponseEntity.ok(toDTO(updatedRol));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        rolService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDTO> findById(@PathVariable String id) {
        try {
            Rol rol = rolService.findById(id);
            return ResponseEntity.ok(toDTO(rol));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<RolDTO>> findAll() {
        List<Rol> roles = rolService.findAll();
        List<RolDTO> dtos = roles.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Métodos auxiliares de mapeo
    private Rol toDomain(RolDTO dto) {
        return new Rol(dto.getId(), dto.getNombre());
    }

    private RolDTO toDTO(Rol rol) {
        return new RolDTO(rol.getId(), rol.getNombre());
    }
}
