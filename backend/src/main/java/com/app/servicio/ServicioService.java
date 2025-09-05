package com.app.servicio;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.app.utils.DataNormalizer;

@Service
public class ServicioService {
    private final ServicioRepository repo;
    private final ServicioMapper mapper;
    public ServicioService(ServicioRepository repo, ServicioMapper mapper) {this.repo = repo;this.mapper = mapper;}

    public ServicioDTO create(ServicioDTO dto) {
        dto.setNombre(DataNormalizer.capitalizarPalabras(dto.getNombre().trim()));
        // Validar unicidad del nombrev
        if (repo.countByNombre(dto.getNombre()) > 0) {
            throw new IllegalArgumentException("Ya existe un servicio con ese nombre.");
        }
        Servicio entity = mapper.toEntity(dto);
        entity = repo.save(entity);
        return mapper.toDTO(entity);
    }

    public ServicioDTO update(ServicioDTO dto) {
        Servicio existente = repo.findById(UUID.fromString(dto.getId()))
            .orElseThrow(() -> new IllegalArgumentException("No existe un servicio con el ID proporcionado."));
        // Capitalizar el nombre del dto
        dto.setNombre(dto.getNombre().trim().toUpperCase());
        // Validar unicidad del nombre si ha cambiado
        if (!dto.getNombre().equals(existente.getNombre()) &&
            repo.countByNombre(dto.getNombre()) > 0) {
            throw new IllegalArgumentException("Ya existe un servicio con ese nombre.");
        }
        // Actualizar el dto
        Servicio entity = mapper.toEntity(dto);
        entity = repo.save(entity);
        return mapper.toDTO(entity);
    }
    
    public void delete(UUID id) {
        if (!repo.findById(id).isPresent()) {
            throw new IllegalArgumentException("No existe un servicio con el ID proporcionado.");
        }
        if (repo.TurnosAsociados(id) > 0) {
            throw new IllegalArgumentException("No se puede eliminar el servicio porque tiene turnos asociados.");
        }
        repo.deleteById(id);
    }

    public ServicioDTO findById(UUID id) {
        return repo.findById(id).map(mapper::toDTO)
            .orElseThrow(() -> new IllegalArgumentException("No existe un servicio con el ID proporcionado."));
    }

    public List<ServicioDTO> findAll() {
        return repo.findAll().stream().map(mapper::toDTO).toList();
    }

    public List<ServicioDTO> findByNombre(String nombre) {
        return repo.findByNombre(nombre).stream().map(mapper::toDTO).toList();
    }
    
}