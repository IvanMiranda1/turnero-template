package com.app.estadoTurno;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoTurnoRepository extends JpaRepository<EstadoTurno, UUID> {
    //los metodos deben devolver la clase entity de cada tabla
    @Query("SELECT e FROM EstadoTurnoEntity e WHERE e.nombre LIKE %?1%")
    List<EstadoTurno> findByNombre(String nombre);
    
    @Query("SELECT COUNT(e) FROM EstadoTurnoEntity e WHERE e.nombre = ?1")
    Long countByNombre(String nombre);
    
    @Query("SELECT COUNT(t) FROM TurnoEntity t WHERE t.fk_estado = ?1")
    Long existUsoDelEstado(UUID idEstadoTurno);

    @Query("SELECT COUNT(t) FROM EstadoTurno e WHERE e.nombre = ?1")
    Long existByNombre(String nombre);
}
// repository interface define los metodos que se implementaran en el persistence/Estado_TurnoPostgresAdapter.java