package com.app.infrastructure.adapter.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.application.usecase.TurnoService;
import com.app.domain.model.Turno;
import com.app.infrastructure.dto.TurnoDTO;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private final TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping
    public ResponseEntity<TurnoDTO> create(@RequestBody TurnoDTO turnoDTO) {
        Turno turno = toDomain(turnoDTO);
        Turno createdTurno = turnoService.create(turno);
        TurnoDTO createdTurnoDTO = toDTO(createdTurno);
        return ResponseEntity.ok(createdTurnoDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<TurnoDTO> update(@RequestBody TurnoDTO turnoDTO) {
        Turno turno = toDomain(turnoDTO);
        Turno updatedTurno = turnoService.update(turno);
        TurnoDTO updatedTurnoDTO = toDTO(updatedTurno);
        return ResponseEntity.ok(updatedTurnoDTO);
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> findAll() {
        List<Turno> turnos = turnoService.findAll();
        List<TurnoDTO> turnoDTOs = turnos.stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(turnoDTOs);
    }

    @GetMapping("/date")
    public ResponseEntity<List<TurnoDTO>> findByDate(String date) {
        List<Turno> turnos = turnoService.findByDate(date);
        List<TurnoDTO> turnoDTOs = turnos.stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(turnoDTOs);
    }

    @GetMapping("/cliente")
    public ResponseEntity<List<TurnoDTO>> findByClienteId(String clienteId) {
        List<Turno> turnos = turnoService.findByClienteId(clienteId);
        List<TurnoDTO> turnoDTOs = turnos.stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(turnoDTOs);
    }
    
    @GetMapping("/usuario")
    public ResponseEntity<List<TurnoDTO>> findByUsuarioId(String usuarioId) {
        List<Turno> turnos = turnoService.findByUsuarioId(usuarioId);
        List<TurnoDTO> turnoDTOs = turnos.stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(turnoDTOs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(String id) {
        turnoService.delete(id);
        return ResponseEntity.noContent().build();
    }


    //metodos aux
    private Turno toDomain(TurnoDTO dto) {
        return new Turno(
            dto.getId(),
            dto.getFecha_alta(),
            dto.getFecha_turno(),
            dto.getFk_estado(),
            dto.getFk_cliente(),
            dto.getFk_usuario(),
            dto.getFk_servicio(),
            dto.getDetalle()
        );
    }
    
    private TurnoDTO toDTO(Turno turno) {
        return new TurnoDTO(
            turno.getId(),
            turno.getFecha_alta(),
            turno.getFecha_turno(),
            turno.getFk_estado(),
            turno.getFk_cliente(),
            turno.getFk_usuario(),
            turno.getFk_servicio(),
            turno.getDetalle()
        );
    }

}
