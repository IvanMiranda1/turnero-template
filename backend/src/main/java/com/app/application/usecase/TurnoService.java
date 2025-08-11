package com.app.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.domain.model.Turno;
import com.app.domain.port.TurnoRepository;

@Service
public class TurnoService {
    private final TurnoRepository turnoRepository;

    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public Turno create(Turno turno) {
        return turnoRepository.createOrUpdate(turno);
    }

    public Turno update(Turno turno) {
        return turnoRepository.createOrUpdate(turno);
    }
    
    public void delete(String id) {
        turnoRepository.delete(id);
    }

    public Turno findById(String id) {
        return turnoRepository.findById(id);
    }

    public List<Turno> findAll() {
        return turnoRepository.findAll();
    }

    public List<Turno> findByDate(String date) {
        return turnoRepository.findByDate(date);
    }

    public List<Turno> findByClienteId(String clienteId) {
        return turnoRepository.findByClienteId(clienteId);
    }// ejemplo, ver los turnos que ya tuvo un cliente

    public List<Turno> findByUsuarioId(String usuarioId) {
        return turnoRepository.findByUsuarioId(usuarioId);
    }// ejemplo, ver los turnos que ya tuvo un empleado


}
