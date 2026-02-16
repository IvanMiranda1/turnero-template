package com.app.estadoTurno;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoTurnoRepository extends JpaRepository<EstadoTurno, UUID> {
    //los metodos deben devolver la clase entity de cada tabla
    @Query("SELECT e FROM EstadoTurno e WHERE e.nombre LIKE %?1%")
    List<EstadoTurno> findByNombre(String nombre);
    
    @Query("SELECT COUNT(e) FROM EstadoTurno e WHERE e.nombre = ?1")
    Long countByNombre(String nombre);
    
    @Query("SELECT COUNT(t) FROM Turno t JOIN t.estadoTurno e WHERE e.id = ?1")
    Long countByEstadoTurnoEnUso(UUID idEstadoTurno);

    //@Query("SELECT COUNT(e) FROM EstadoTurno e WHERE e.nombre = ?1")
    Long existsByNombre(String nombre);

    @Query("select count(t) from EstadoTurno t where t.nombre = ?1 and (?2 is null or t.id <> ?2)")
    Long unicidadByNombreConId(String nombre, UUID id);
}
// repository interface define los metodos que se implementaran en el persistence/Estado_TurnoPostgresAdapter.java