package com.app.application.usecase;

import java.util.List;
import java.util.Optional;

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
        // Lógica de negocio para crear un usuario
        // Por ejemplo, aquí podrías verificar la unicidad de un campo como el DNI o el email si fuera necesario.
        return usuarioRepository.createOrUpdate(usuario);
    }

    public Usuario update(Usuario usuario) {
        // Se valida que el ID del usuario no sea nulo o vacío, ya que es fundamental para la actualización.
        if (usuario.getId() == null || usuario.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del usuario es obligatorio para actualizar.");
        }
        // Se verifica la existencia del usuario antes de intentar actualizar.
        // Esto previene la actualización de un usuario que no existe en la base de datos.
        Optional<Usuario> existente = usuarioRepository.findById(usuario.getId());
        if (!existente.isPresent()) {
            throw new IllegalArgumentException("No existe un usuario con el ID proporcionado para actualizar.");
        }
        return usuarioRepository.createOrUpdate(usuario);
    }

    public void delete(String id) {
        // Se valida que el ID para la eliminación no sea nulo o vacío.
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del usuario es obligatorio para eliminar.");
        }
        // Se verifica la existencia del usuario antes de la eliminación.
        if (!usuarioRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("No existe un usuario con el ID proporcionado para eliminar.");
        }
        usuarioRepository.delete(id);
    }

    public Usuario findById(String id) {
        // Se valida que el ID para la búsqueda no sea nulo o vacío.
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del usuario es obligatorio para buscar.");
        }
        // Se utiliza .orElseThrow() para devolver el objeto o lanzar una excepción si no se encuentra.
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe un usuario con el ID proporcionado."));
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