package com.app.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.domain.model.Usuario;
import com.app.domain.port.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario create(Usuario usuario) {
        return usuarioRepository.createOrUpdate(usuario);
    }

    public Usuario update(Usuario usuario) {
        return usuarioRepository.createOrUpdate(usuario);
    }
    
    public void delete(String id) {
        usuarioRepository.delete(id);
    }

    public Usuario findById(String id) {
        return usuarioRepository.findById(id);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> findByDni(String dni) {
        return usuarioRepository.findByDni(dni);
    }

    public List<Usuario> findByNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }

    public List<Usuario> findByApellido(String apellido) {
        return usuarioRepository.findByApellido(apellido);
    }
}
