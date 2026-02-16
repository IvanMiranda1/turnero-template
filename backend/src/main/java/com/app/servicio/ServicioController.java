package com.app.servicio;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.cliente.ClienteDTO.OnCreate;
import com.app.cliente.ClienteDTO.OnUpdate;


@RestController
@RequestMapping("/servicios")
public class ServicioController {
    private final ServicioService service;
    
    public ServicioController(ServicioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ServicioDTO> create(@Validated(OnCreate.class) @RequestBody ServicioDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ServicioDTO> update(@Validated(OnUpdate.class) @RequestBody ServicioDTO dto) {
       
        return ResponseEntity.ok(service.update(dto));
    }

    @GetMapping
    public ResponseEntity<List<ServicioDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/id")
    public ResponseEntity<ServicioDTO> findById(UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/nombre")
    public ResponseEntity<List<ServicioDTO>> findByNombre(String nombre) {
        return ResponseEntity.ok(service.findByNombre(nombre));
    }
}
