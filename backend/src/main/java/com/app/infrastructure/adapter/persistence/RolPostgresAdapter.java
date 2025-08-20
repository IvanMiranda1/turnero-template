package com.app.infrastructure.adapter.persistence;

import com.app.domain.model.Rol;
import com.app.domain.port.RolRepository;
import com.app.infrastructure.persistence.entity.RolEntity;
import com.app.infrastructure.persistence.repository.RolJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RolPostgresAdapter implements RolRepository {

    private final RolJpaRepository rolJpaRepository;

    public RolPostgresAdapter(RolJpaRepository rolJpaRepository) {
        this.rolJpaRepository = rolJpaRepository;
    }

    @Override
    public Rol createOrUpdate(Rol rol) {
        RolEntity entity = toEntity(rol);
        RolEntity savedEntity = rolJpaRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public void delete(String id) {
        rolJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Rol> findById(String id) {
        return rolJpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Rol> findAll() {
        return rolJpaRepository.findAll().stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Rol> findByNombre(String nombre) {
        return rolJpaRepository.findByNombre(nombre).stream()
                .map(this::toDomain)
                .toList();
    }

    // Métodos auxiliares de mapeo
    private RolEntity toEntity(Rol rol) {
        return new RolEntity(rol.getId(), rol.getNombre());
    }

    private Rol toDomain(RolEntity entity) {
        return new Rol(entity.getId(), entity.getNombre());
    }
}