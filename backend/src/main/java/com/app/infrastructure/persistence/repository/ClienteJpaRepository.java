package com.app.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.infrastructure.persistence.entity.ClienteEntity;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, String> {
    @Query("SELECT c FROM ClienteEntity c WHERE c.nombre LIKE %?1%")
    List<ClienteEntity> findByNombre(String nombre);
    @Query("SELECT c FROM ClienteEntity c WHERE c.apellido LIKE %?1%")
    List<ClienteEntity> findByApellido(String apellido);
    @Query("SELECT c FROM ClienteEntity c WHERE c.email LIKE %?1%")
    List<ClienteEntity> findByEmail(String email);
    @Query("SELECT c FROM ClienteEntity c WHERE c.telefono LIKE %?1%")   
    List<ClienteEntity> findByTelefono(String telefono);


    //verificar unicidad individualmente
    @Query("SELECT COUNT(c) FROM ClienteEntity c WHERE c.email = ?1")
    Long countByEmail(String email);
    //verificar unicidad por ID
    @Query("SELECT COUNT(c) FROM ClienteEntity c WHERE c.id = ?1")
    Long countById(String id);
    //verificar unicidad por DNI
    @Query("SELECT COUNT(c) FROM ClienteEntity c WHERE c.dni = ?1")
    Long countByDni(String dni);
    //verificar unicidad por telefono
    @Query("SELECT COUNT(c) FROM ClienteEntity c WHERE c.telefono = ?1")
    Long countByTelefono(String telefono);
    //verificar unicidad por email, dni o telefono
    @Query("SELECT COUNT(c) FROM ClienteEntity c WHERE c.email = ?1 OR c.dni = ?2 OR c.telefono = ?3")
    Long countByEmailOrDniOrTelefono(String email, String dni, String telefono);

    @Query("SELECT COUNT(c) FROM ClienteEntity c WHERE (c.email = ?1 OR c.dni = ?2 OR c.telefono = ?3) AND (:id IS NULL OR c.id <> ?4)")
    Long countByEmailOrDniOrTelefonoAndId(String email, String dni, String telefono, String id);
}
