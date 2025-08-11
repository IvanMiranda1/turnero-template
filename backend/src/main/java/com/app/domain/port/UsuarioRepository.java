package com.app.domain.port;

import java.util.List;

import com.app.domain.model.Usuario;

public interface UsuarioRepository {
    Usuario createOrUpdate(Usuario usuario);
    void delete(String id);
    Usuario findById(String id);
    List<Usuario> findAll();
    List<Usuario> findByDni(String dni);
    List<Usuario> findByNombre(String nombre);
    List<Usuario> findByApellido(String apellido);
}
