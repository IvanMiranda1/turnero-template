package com.app.turno;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;


@Service
public class TurnoService {
    private final TurnoRepository repo;
    private final TurnoMapper mapper;

    public TurnoService(TurnoRepository repo, TurnoMapper mapper) {
        this.repo = repo; this.mapper = mapper;
    }

    public TurnoDTO create(TurnoDTO dto) {
        Turno entity = mapper.toEntity(dto);
        entity = repo.save(entity);
        return mapper.toDTO(entity);
    }

    public TurnoDTO update(TurnoDTO turno) {
        Turno entity = mapper.toEntity(turno);
        entity = repo.save(entity);
        return mapper.toDTO(entity);
    }
    
    public void delete(UUID id) {
        repo.deleteById(id);
    }

    public TurnoDTO findById(UUID id) {
        return repo.findById(id).map(mapper::toDTO)
            .orElseThrow(() -> new IllegalArgumentException("No existe"));
    }

    public List<TurnoDTO> findAll() {
        return repo.findAll().stream().map(mapper::toDTO).toList();
    }

    public List<TurnoDTO> findByDate(String fechaTurno) {
        return repo.findByFechaTurno(fechaTurno).stream().map(mapper::toDTO).toList();
    }

    public List<TurnoDTO> findByClienteId(UUID clienteId) {
        return repo.findByFkCliente(clienteId).stream().map(mapper::toDTO).toList();
    }// ejemplo, ver los turnos que ya tuvo un cliente

    public List<TurnoDTO> findByUsuarioId(UUID usuarioId) {
        return repo.findByFkUsuario(usuarioId).stream().map(mapper::toDTO).toList();
    }// ejemplo, ver los turnos que ya tuvo un empleado


}
