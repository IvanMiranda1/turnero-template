package com.app.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.infrastructure.persistence.entity.ServicioEntity;

public interface ServicioJpaRepository extends JpaRepository<ServicioEntity, String> {
    @Query("SELECT s FROM ServicioEntity s WHERE s.nombre LIKE %?1%")
    List<ServicioEntity> findByNombre(String nombre);

    @Query("SELECT COUNT(s) FROM ServicioEntity s WHERE s.nombre = ?1")
    Long countByNombre(String nombre);

    @Query("SELECT COUNT(t) FROM TurnoEntity t WHERE t.fk_servicio = ?1")
    Long TurnosAsociados(String idServicio);
}
