package com.app.infrastructure.adapter.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.application.usecase.ServicioService;
import com.app.domain.model.Servicio;
import com.app.infrastructure.dto.ServicioDTO;

@RestController
@RequestMapping("/servicios")
public class ServicioController {
    private final ServicioService servicioService;
    
    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @PostMapping
    public ResponseEntity<ServicioDTO> create(@RequestBody ServicioDTO servicioDTO) {
        Servicio servicio = toDomain(servicioDTO);
        Servicio createdServicio = servicioService.create(servicio);
        ServicioDTO createdServicioDTO = toDTO(createdServicio);
        return ResponseEntity.ok(createdServicioDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<ServicioDTO> update(@RequestBody ServicioDTO servicioDTO) {
        Servicio servicio = toDomain(servicioDTO);
        Servicio updatedServicio = servicioService.update(servicio);
        ServicioDTO updatedServicioDTO = toDTO(updatedServicio);
        return ResponseEntity.ok(updatedServicioDTO);
    }

    @GetMapping
    public ResponseEntity<List<ServicioDTO>> findAll() {
        List<Servicio> servicios = servicioService.findAll();
        List<ServicioDTO> servicioDTOs = servicios.stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(servicioDTOs);
    }

    @GetMapping("/id")
    public ResponseEntity<ServicioDTO> findById(String id) {
        Servicio servicio = servicioService.findById(id);
        if (servicio == null) {
            return ResponseEntity.notFound().build();
        }
        ServicioDTO servicioDTO = toDTO(servicio);
        return ResponseEntity.ok(servicioDTO);
    }

    @GetMapping("/nombre")
    public ResponseEntity<List<ServicioDTO>> findByNombre(String nombre) {
        List<Servicio> servicios = servicioService.findByNombre(nombre);
        List<ServicioDTO> servicioDTOs = servicios.stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(servicioDTOs);
    }


    //metodos aux
    private Servicio toDomain(ServicioDTO dto) {
        return new Servicio(
            dto.getId(),
            dto.getNombre(),
            dto.getDuracion_estimada(),
            dto.getPrecio(),
            dto.getDetalle()
        );
    }
    private ServicioDTO toDTO(Servicio servicio) {
        return new ServicioDTO(
            servicio.getId(),
            servicio.getNombre(),
            servicio.getDuracion_estimada(),
            servicio.getPrecio(),
            servicio.getDetalle()
        );
    }
}
