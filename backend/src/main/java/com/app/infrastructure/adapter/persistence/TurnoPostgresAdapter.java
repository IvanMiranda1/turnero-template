package com.app.infrastructure.adapter.persistence;

import java.util.List;

import org.springframework.stereotype.Component;

import com.app.domain.model.Turno;
import com.app.domain.port.TurnoRepository;
import com.app.infrastructure.persistence.entity.TurnoEntity;
import com.app.infrastructure.persistence.repository.TurnoJpaRepository;

@Component
public class TurnoPostgresAdapter implements TurnoRepository {
    private final TurnoJpaRepository turnoJpaRepository;
    
    public TurnoPostgresAdapter(TurnoJpaRepository turnoJpaRepository) {
        this.turnoJpaRepository = turnoJpaRepository;
    }

    @Override
    public Turno createOrUpdate(Turno turno) {
        TurnoEntity entity = toEntity(turno);
        TurnoEntity savedEntity = turnoJpaRepository.save(entity);
        return toDomain(savedEntity);   
    }

    @Override
    public Turno findById(String id) {
        return turnoJpaRepository.findById(id)
                .map(this::toDomain)
                .orElse(null);
    }

    @Override
    public void delete(String id) {
        turnoJpaRepository.deleteById(id);
    }

    @Override
    public List<Turno> findAll() {
        return turnoJpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Turno> findByDate(String date) {
        return turnoJpaRepository.findByFechaTurno(date)
                .stream()
                .map(this::toDomain)
                .toList();
    }
    
    @Override
    public List<Turno> findByClienteId(String clienteId) {
        return turnoJpaRepository.findByFkCliente(clienteId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Turno> findByUsuarioId(String usuarioId) {
        return turnoJpaRepository.findByFkUsuario(usuarioId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    //metodos aux
    private TurnoEntity toEntity(Turno turno) {
        return new TurnoEntity(
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

    private Turno toDomain(TurnoEntity entity) {
        return new Turno(
            entity.getId(),
            entity.getFecha_alta(),
            entity.getFecha_turno(),
            entity.getFk_estado(),
            entity.getFk_cliente(),
            entity.getFk_usuario(),
            entity.getFk_servicio(),
            entity.getDetalle()
        );
    }
}
