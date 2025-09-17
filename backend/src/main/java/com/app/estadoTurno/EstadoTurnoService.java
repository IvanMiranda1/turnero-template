package com.app.estadoTurno;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.app.utils.DataNormalizer;

@Service
public class EstadoTurnoService {
    private final EstadoTurnoRepository repo;
    private final EstadoTurnoMapper mapper;
    
    public EstadoTurnoService(EstadoTurnoRepository repo, EstadoTurnoMapper mapper) {this.repo = repo; this.mapper = mapper;}

    //funciones crud
    public EstadoTurnoDTO create(EstadoTurnoDTO dto) {        
        dto.setNombre(DataNormalizer.capitalizarPalabras(dto.getNombre()));
        //validarFormatoNombre(dto.getNombre());
        if (repo.countByNombre(dto.getNombre()) > 0) {
            throw new IllegalArgumentException("Ya existe un estado de turno con ese nombre.");
        }
        EstadoTurno estadoTurno = mapper.toEntity(dto);
        estadoTurno = repo.save(estadoTurno);
        return mapper.toDTO(estadoTurno);
    }

    public EstadoTurnoDTO update(EstadoTurnoDTO dto) {
        EstadoTurno existente = repo.findById(UUID.fromString(dto.getId()))
            .orElseThrow( () -> new IllegalArgumentException("No existe un estado de turno con el ID proporcionado."));
        dto.setNombre(DataNormalizer.capitalize(dto.getNombre()));
        // Solo validar unicidad si cambió el nombre
        if (!existente.getNombre().equalsIgnoreCase(dto.getNombre()) && repo.countByNombre(dto.getNombre()) > 0) {
            throw new IllegalArgumentException("Ya existe un estado de turno con ese nombre.");
        }
        EstadoTurno entity = mapper.toEntity(dto);
        entity = repo.save(entity);
        return mapper.toDTO(entity);
    }

    public EstadoTurnoDTO findById(UUID id) {
        EstadoTurno e = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("No existe un estado de turno con el ID proporcionado."));
            return mapper.toDTO(e);
    }

    public void delete(UUID id) {
        if (!repo.findById(id).isPresent()) {
            throw new IllegalArgumentException("No existe un estado de turno con el ID proporcionado.");
        }
        // Verificar si el estado de turno está en uso por algún turno
        if (repo.countByEstadoTurnoEnUso(id) > 0) {
            throw new IllegalArgumentException("No se puede eliminar el estado de turno porque está en uso.");
        }
        // Si pasa las validaciones, se procede a eliminar
        repo.deleteById(id);
    }

    public List<EstadoTurnoDTO> findAll() {
        return repo.findAll().stream().map(mapper::toDTO).toList();
    }

    public List<EstadoTurnoDTO> findByNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio para buscar.");
        }
        return repo.findByNombre(nombre).stream().map(mapper::toDTO).toList();
    }
}
