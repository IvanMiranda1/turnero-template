package com.app.cliente;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    @Query("SELECT c FROM Cliente c WHERE c.nombre LIKE %?1%")
    List<Cliente> findByNombre(String nombre);
    @Query("SELECT c FROM Cliente c WHERE c.apellido LIKE %?1%")
    List<Cliente> findByApellido(String apellido);
    @Query("SELECT c FROM Cliente c WHERE c.email LIKE %?1%")
    List<Cliente> findByEmail(String email);
    @Query("SELECT c FROM Cliente c WHERE c.telefono LIKE %?1%")   
    List<Cliente> findByTelefono(String telefono);

    //verificaciones de unicidad
    // AND (?4 IS NULL OR c.id <> ?4) si id es null (creacion) no lo tiene en cuenta,
    // si no es null y coincide con el id (edicion) lo excluye de la busqueda para evitar duplicados en la busqueda
    @Query("SELECT COUNT(c) FROM Cliente c WHERE (c.email = ?1 OR c.dni = ?2 OR c.telefono = ?3) AND (?4 IS NULL OR c.id <> ?4)")
    Long countByEmailOrDniOrTelefonoAndId(String email, String dni, String telefono, UUID id);

    @Query("SELECT COUNT(t) FROM Cliente c JOIN c.turnos t Join t.estadoTurno e WHERE c.id = ?1 AND e.nombre = 'Pendiente'")
    Long turnosPendientes(UUID idCliente);
    /***    al turno ya lo asocie con el mapping entonces no hace falta comprobar
     *      nada porque me asegura que el turno que se esta comparando ya tiene una
     *      relacion con el cliente  */

}
