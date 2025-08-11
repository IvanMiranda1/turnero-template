package com.app.application.usecase;

import org.springframework.stereotype.Service;

import com.app.domain.model.EstadoTurno;
import com.app.domain.port.EstadoTurnoRepository;

@Service
public class EstadoTurnoService {
    private final EstadoTurnoRepository estadoTurnoRepository;

    public EstadoTurnoService(EstadoTurnoRepository estadoTurnoRepository) {
        this.estadoTurnoRepository = estadoTurnoRepository;
    }

    //funciones crud
    public EstadoTurno create(EstadoTurno estadoTurno) {
        return estadoTurnoRepository.createOrUpdate(estadoTurno);
    }

    public EstadoTurno update(EstadoTurno estadoTurno) {
        return estadoTurnoRepository.createOrUpdate(estadoTurno);
    }

    public EstadoTurno findById(String id) {
        return estadoTurnoRepository.findById(id);
    }

    public void delete(String id) {
        estadoTurnoRepository.delete(id);
    }
}
