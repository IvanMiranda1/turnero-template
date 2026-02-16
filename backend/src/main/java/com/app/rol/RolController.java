package com.app.rol;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.rol.RolDTO.OnCreate;
import com.app.rol.RolDTO.OnUpdate;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/roles")
public class RolController {
    private final RolService service;
    public RolController(RolService service) {this.service = service;}

    @PostMapping
    public ResponseEntity<RolDTO> create(@Validated(OnCreate.class) @RequestBody RolDTO dto){
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping
    public ResponseEntity<RolDTO> update(@Validated(OnUpdate.class) @RequestBody RolDTO dto){
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<RolDTO> findById(@PathVariable UUID id){ 
        return ResponseEntity.ok(service.findById(id)); 
    }
    @GetMapping("/{nombreRol}")
    public ResponseEntity<List<RolDTO>> findByNombreRol(@PathVariable String nombreRol){ 
        return ResponseEntity.ok(service.findByNombreRol(nombreRol)); 
    }
}
