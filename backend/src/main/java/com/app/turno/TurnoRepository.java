package com.app.turno;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TurnoRepository extends JpaRepository<Turno, UUID> {
    @Query("SELECT t FROM Turno t WHERE t.fechaTurno = ?1")
    List<Turno> findByFechaTurno(String fechaTurno);

    @Query("SELECT t FROM Turno t JOIN t.cliente c WHERE c.id = ?1")
    List<Turno> findByFkCliente(UUID fkCliente);

    @Query("SELECT t FROM Turno t JOIN t.usuario u WHERE u.id = ?1")
    List<Turno> findByFkUsuario(UUID fkUsuario);

    //crear query que traiga todos los turnos en un estado determinado, ejemplo: todos los turnos "Pendiente"s..
    @Query("SELECT t FROM Turno t JOIN t.estadoTurno et WHERE et.nombre = ?1")
    List<Turno> findByEstado(String estadoTurno);
}

//el turno sirve mas para visualizar datos mas que logica en general