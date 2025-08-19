package com.app.infrastructure.adapter.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.app.domain.model.Servicio;
import com.app.domain.port.ServicioRepository;
import com.app.infrastructure.persistence.entity.ServicioEntity;
import com.app.infrastructure.persistence.repository.ServicioJpaRepository;

@Component
public class ServicioPostgresAdapter implements ServicioRepository {

    private final ServicioJpaRepository servicioJpaRepository;

    public ServicioPostgresAdapter(ServicioJpaRepository servicioJpaRepository) {
        this.servicioJpaRepository = servicioJpaRepository;
    }

    @Override
    public Servicio createOrUpdate(Servicio servicio) {
        ServicioEntity entity = toEntity(servicio);
        ServicioEntity savedEntity = servicioJpaRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Servicio> findById(String id) {
        return servicioJpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public void delete(String id) {
        servicioJpaRepository.deleteById(id);
    }

    @Override
    public List<Servicio> findAll() {
        return servicioJpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Servicio> findByNombre(String nombre) {
        return servicioJpaRepository.findByNombre(nombre)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Long countByNombre(String nombre) {
        return servicioJpaRepository.countByNombre(nombre);
    }

    @Override
    public Long TurnosAsociados(String idServicio) {
        return servicioJpaRepository.TurnosAsociados(idServicio);
    }
    
    //metodos aux
    private Servicio toDomain(ServicioEntity entity) {
        return new Servicio(
            entity.getId(),
            entity.getNombre(),
            entity.getDuracion_estimada(),
            entity.getPrecio(),
            entity.getDetalle()
        );
    }
    private ServicioEntity toEntity(Servicio servicio) {
        return new ServicioEntity(
            servicio.getId(),
            servicio.getNombre(),
            servicio.getDuracion_estimada(),
            servicio.getPrecio(),
            servicio.getDetalle()
        );
    }

    
}
