package com.app.domain.port;

import java.util.List;
import java.util.Optional;

import com.app.domain.model.Usuario;

public interface UsuarioRepository {
    Usuario createOrUpdate(Usuario usuario);
    void delete(String id);
    Optional<Usuario> findById(String id); //Antes era de tipo Usuario, ahora es Optional<Usuario>
    List<Usuario> findAll();
    List<Usuario> findByDni(String dni);
    List<Usuario> findByNombre(String nombre);
    List<Usuario> findByApellido(String apellido);
}
