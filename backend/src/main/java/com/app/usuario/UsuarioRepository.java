package com.app.usuario;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID>  {
    @Query("SELECT u FROM UsuarioEntity u WHERE u.dni LIKE %?1%")
    List<Usuario> findByDni(String dni);
    @Query("SELECT u FROM UsuarioEntity u WHERE u.nombre LIKE %?1%")
    List<Usuario> findByNombre(String nombre);
    @Query("SELECT u FROM UsuarioEntity u WHERE u.apellido LIKE %?1%")
    List<Usuario> findByApellido(String apellido);
}
