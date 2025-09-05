package com.app.rol;

import java.util.List;
import java.util.UUID;


import org.springframework.stereotype.Service;

import com.app.utils.DataNormalizer;

@Service
public class RolService {
    private final RolRepository repo;
    private final RolMapper mapper;

    public RolService(RolRepository repo, RolMapper mapper) {this.repo = repo; this.mapper = mapper;}

    public RolDTO create(RolDTO dto) {
        dto.setNombreRol(DataNormalizer.capitalize(dto.getNombreRol()));
        Rol entity = mapper.toEntity(dto);
        entity = repo.save(entity);
        return mapper.toDTO(entity);
    }

    public RolDTO update(RolDTO dto) {
        dto.setNombreRol(DataNormalizer.capitalize(dto.getNombreRol()));
        Rol entity = mapper.toEntity(dto);
        entity = repo.save(entity);
        return mapper.toDTO(entity);
    }

    public void delete(UUID id) {
        repo.deleteById(id);
    }

    public RolDTO findById(UUID id) {
        Rol r = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("No existe"));
        return mapper.toDTO(r);
    }

    public List<RolDTO> findAll() {
        return repo.findAll().stream().map(mapper::toDTO).toList();
    }

    public List<RolDTO> findByNombreRol(String nombreRol) {
        nombreRol = DataNormalizer.capitalize(nombreRol);
        return repo.findByNombreRol(nombreRol).stream().map(mapper::toDTO).toList();
    }

}
