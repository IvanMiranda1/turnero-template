package com.app.infrastructure.adapter.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.app.domain.model.EstadoTurno;
import com.app.domain.port.EstadoTurnoRepository;
import com.app.infrastructure.persistence.entity.EstadoTurnoEntity;
import com.app.infrastructure.persistence.repository.EstadoTurnoJpaRepository;

@Component
public class EstadoTurnoPostgresAdapter implements EstadoTurnoRepository {
    private final EstadoTurnoJpaRepository estadoTurnoJpaRepository;

    public EstadoTurnoPostgresAdapter(EstadoTurnoJpaRepository estadoTurnoJpaRepository) {
        this.estadoTurnoJpaRepository = estadoTurnoJpaRepository;
    }

    @Override
    public EstadoTurno createOrUpdate(EstadoTurno estado_turno) {
        EstadoTurnoEntity entity = toEntity(estado_turno);
        EstadoTurnoEntity savedEntity = estadoTurnoJpaRepository.save(entity);
        return toDomain(savedEntity);
    }
    @Override
    public Optional<EstadoTurno> findById(String id) {
        return estadoTurnoJpaRepository.findById(id)
                .map(this::toDomain);
    }
    
    @Override
    public List<EstadoTurno> findByNombre(String nombre) {
        return estadoTurnoJpaRepository.findByNombre(nombre)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<EstadoTurno> findAll() {
        return estadoTurnoJpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void delete(String id) {
        estadoTurnoJpaRepository.deleteById(id);
    }

    @Override
    public Long existByNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del estado de turno no puede ser nulo o vacío.");
        }
        return estadoTurnoJpaRepository.countByNombre(nombre);
    }

    @Override
    public Long existUsoDelEstado(String idEstadoTurno) {
        if (idEstadoTurno == null || idEstadoTurno.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del estado de turno no puede ser nulo o vacío.");
        }
        return estadoTurnoJpaRepository.existUsoDelEstado(idEstadoTurno);
    }

    //metodos aux
    private EstadoTurnoEntity toEntity(EstadoTurno estado_turno) {
        return new EstadoTurnoEntity(
            estado_turno.getId(),
            estado_turno.getNombre()
        );
    }

    private EstadoTurno toDomain(EstadoTurnoEntity entity) {
        return new EstadoTurno(
            entity.getId(),
            entity.getNombre()
        );
    }
        
    
}
