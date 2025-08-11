package com.app.domain.port;

import java.util.List;

import com.app.domain.model.Turno;

public interface TurnoRepository {
    Turno createOrUpdate(Turno turno);
    void delete(String id);
    Turno findById(String id);
    List<Turno> findAll();
    List<Turno> findByDate(String date);
    List<Turno> findByClienteId(String clienteId);
    List<Turno> findByUsuarioId(String usuarioId);
}

//el turno sirve mas para visualizar datos mas que logica en general