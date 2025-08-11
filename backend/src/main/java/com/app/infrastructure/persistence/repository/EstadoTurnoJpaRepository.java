package com.app.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.infrastructure.persistence.entity.EstadoTurnoEntity;

public interface EstadoTurnoJpaRepository extends JpaRepository<EstadoTurnoEntity, String> {
    //los metodos deben devolver la clase entity de cada tabla
    @Query("SELECT e FROM EstadoTurnoEntity e WHERE e.nombre LIKE %?1%")
    List<EstadoTurnoEntity> findByNombre(String nombre);
}