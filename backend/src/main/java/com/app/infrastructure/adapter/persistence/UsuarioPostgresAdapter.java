package com.app.infrastructure.adapter.persistence;

import com.app.domain.model.Rol;
import com.app.domain.model.Usuario;
import com.app.domain.port.UsuarioRepository;
import com.app.infrastructure.persistence.entity.RolEntity;
import com.app.infrastructure.persistence.entity.UsuarioEntity;
import com.app.infrastructure.persistence.repository.RolJpaRepository;
import com.app.infrastructure.persistence.repository.UsuarioJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UsuarioPostgresAdapter implements UsuarioRepository {
    private final UsuarioJpaRepository usuarioJpaRepository;
    private final RolJpaRepository rolJpaRepository;

    public UsuarioPostgresAdapter(UsuarioJpaRepository usuarioJpaRepository, RolJpaRepository rolJpaRepository) {
        this.usuarioJpaRepository = usuarioJpaRepository;
        this.rolJpaRepository = rolJpaRepository;
    }

    @Override
    public Usuario createOrUpdate(Usuario usuario) {
        UsuarioEntity entity = toEntity(usuario);
        UsuarioEntity savedEntity = usuarioJpaRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public void delete(String id) {
        usuarioJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Usuario> findById(String id) {
        return usuarioJpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioJpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Usuario> findByDni(String dni) {
        return usuarioJpaRepository.findByDni(dni).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Usuario> findByNombre(String nombre) {
        return usuarioJpaRepository.findByNombre(nombre).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Usuario> findByApellido(String apellido) {
        return usuarioJpaRepository.findByApellido(apellido).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    // Métodos auxiliares de mapeo
    private UsuarioEntity toEntity(Usuario usuario) {
        // Busca la entidad Rol por su ID antes de mapear
        RolEntity rolEntity = rolJpaRepository.findById(usuario.getRol().getId())
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado con ID: " + usuario.getRol().getId()));

        return new UsuarioEntity(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getDni(),
                usuario.getEmail(),
                rolEntity,
                usuario.getFecha_creacion(),
                usuario.getUltima_sesion()
        );
    }

    private Usuario toDomain(UsuarioEntity entity) {
        // Mapea la entidad RolEntity a la clase de dominio Rol
        Rol rol = new Rol(entity.getRol().getId(), entity.getRol().getNombre());
        return new Usuario(
                entity.getId(),
                entity.getNombre(),
                entity.getApellido(),
                entity.getDni(),
                entity.getEmail(),
                rol,
                entity.getFecha_creacion(),
                entity.getUltima_sesion()
        );
    }
}