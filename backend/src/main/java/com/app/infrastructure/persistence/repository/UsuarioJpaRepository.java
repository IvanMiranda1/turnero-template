package com.app.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.infrastructure.persistence.entity.UsuarioEntity;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, String> {
    @Query("SELECT u FROM UsuarioEntity u WHERE u.dni LIKE %?1%")
    List<UsuarioEntity> findByDni(String dni);
    @Query("SELECT u FROM UsuarioEntity u WHERE u.nombre LIKE %?1%")
    List<UsuarioEntity> findByNombre(String nombre);
    @Query("SELECT u FROM UsuarioEntity u WHERE u.apellido LIKE %?1%")
    List<UsuarioEntity> findByApellido(String apellido);
}
