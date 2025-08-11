package com.app.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.infrastructure.persistence.entity.ClienteEntity;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, String> {
    @Query("SELECT c FROM ClienteEntity c WHERE c.nombre = ?1")
    List<ClienteEntity> findByNombre(String nombre);
    @Query("SELECT c FROM ClienteEntity c WHERE c.apellido = ?1")
    List<ClienteEntity> findByApellido(String apellido);
    @Query("SELECT c FROM ClienteEntity c WHERE c.email = ?1")
    List<ClienteEntity> findByEmail(String email);
    @Query("SELECT c FROM ClienteEntity c WHERE c.telefono = ?1")   
    List<ClienteEntity> findByTelefono(String telefono);
}
