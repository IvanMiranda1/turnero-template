package com.app.turno;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TurnoRepository extends JpaRepository<Turno, UUID> {
    @Query("SELECT t FROM Turno t WHERE t.fecha_turno = ?1")
    List<Turno> findByFechaTurno(String fechaTurno);

    @Query("SELECT t FROM Turno t WHERE t.fk_cliente = ?1")
    List<Turno> findByFkCliente(UUID fkCliente);

    @Query("SELECT t FROM Turno t WHERE t.fk_usuario = ?1")
    List<Turno> findByFkUsuario(UUID fkUsuario);

    
}

//el turno sirve mas para visualizar datos mas que logica en general