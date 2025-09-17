package com.app.usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID>  {
    Optional<Usuario> findByEmail(String email);
    // Spring genera automáticamente un "like" si le agregás Containing

    //@Query("SELECT u FROM UsuarioEntity u WHERE u.dni LIKE %?1%")
    List<Usuario> findByDniContaining(String dni);
    //@Query("SELECT u FROM UsuarioEntity u WHERE u.nombre LIKE %?1%")
    List<Usuario> findByNombreContaining(String nombre);
    //@Query("SELECT u FROM UsuarioEntity u WHERE u.apellido LIKE %?1%")
    List<Usuario> findByApellidoContaining(String apellido);
}
