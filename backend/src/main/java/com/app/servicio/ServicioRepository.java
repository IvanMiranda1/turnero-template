package com.app.servicio;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ServicioRepository extends JpaRepository<Servicio, UUID> {
    @Query("SELECT s FROM Servicio s WHERE s.nombre LIKE %?1%")
    List<Servicio> findByNombre(String nombre);

    @Query("SELECT COUNT(s) FROM Servicio s WHERE s.nombre = ?1")
    Long countByNombre(String nombre);

    @Query("SELECT COUNT(t) FROM TurnoEntity t WHERE t.fk_servicio = ?1")
    Long TurnosAsociados(UUID idServicio);
}