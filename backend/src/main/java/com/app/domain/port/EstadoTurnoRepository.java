package com.app.domain.port;

import java.util.List;
import java.util.Optional;

import com.app.domain.model.EstadoTurno;

public interface EstadoTurnoRepository {
    EstadoTurno createOrUpdate(EstadoTurno estado_turno);
    Optional<EstadoTurno> findById(String id);
    void delete(String id);
    List<EstadoTurno> findAll();
    List<EstadoTurno> findByNombre(String nombre);

    Long existByNombre(String nombre);
    Long existUsoDelEstado(String idEstadoTurno);
}
// repository interface define los metodos que se implementaran en el persistence/Estado_TurnoPostgresAdapter.java