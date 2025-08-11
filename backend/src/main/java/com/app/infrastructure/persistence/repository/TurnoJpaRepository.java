package com.app.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.infrastructure.persistence.entity.TurnoEntity;

public interface TurnoJpaRepository extends JpaRepository<TurnoEntity, String> {
    @Query("SELECT t FROM TurnoEntity t WHERE t.fecha_turno = ?1")
    List<TurnoEntity> findByFechaTurno(String fechaTurno);

    @Query("SELECT t FROM TurnoEntity t WHERE t.fk_cliente = ?1")
    List<TurnoEntity> findByFkCliente(String fkCliente);

    @Query("SELECT t FROM TurnoEntity t WHERE t.fk_usuario = ?1")
    List<TurnoEntity> findByFkUsuario(String fkUsuario);
}
