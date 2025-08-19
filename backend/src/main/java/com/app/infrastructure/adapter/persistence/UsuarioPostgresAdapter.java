package com.app.infrastructure.adapter.persistence;

import java.util.List;
import java.util.Optional;

import com.app.domain.model.Rol;
import org.springframework.stereotype.Component;

import com.app.domain.model.Usuario;
import com.app.domain.port.UsuarioRepository;
import com.app.infrastructure.persistence.entity.UsuarioEntity;
import com.app.infrastructure.persistence.repository.UsuarioJpaRepository;

@Component
public class UsuarioPostgresAdapter implements UsuarioRepository {
    private final UsuarioJpaRepository usuarioJpaRepository;

    public UsuarioPostgresAdapter(UsuarioJpaRepository usuarioJpaRepository) {
        this.usuarioJpaRepository = usuarioJpaRepository;
    }

    @Override
    public Usuario createOrUpdate(Usuario usuario) {
        UsuarioEntity entity = toEntity(usuario);
        UsuarioEntity savedEntity = usuarioJpaRepository.save(entity);
        return toDomain(savedEntity);   
    }

    @Override
    public Optional<Usuario> findById(String id) {
        // No hace falta usar .orElse(null) ya que ahora el metodo devuelve Optional<Usuario>
        return usuarioJpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public void delete(String id) {
        usuarioJpaRepository.deleteById(id);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioJpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }
    
    @Override
    public List<Usuario> findByDni(String dni) {
        return usuarioJpaRepository.findByDni(dni)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Usuario> findByNombre(String nombre) {
        return usuarioJpaRepository.findByNombre(nombre)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Usuario> findByApellido(String apellido) {
        return usuarioJpaRepository.findByApellido(apellido)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    //metodos aux
    private UsuarioEntity toEntity(Usuario usuario) {
        return new UsuarioEntity(
            usuario.getId(),
            usuario.getNombre(),
            usuario.getApellido(),
            usuario.getDni(),
            usuario.getEmail(),
            usuario.getRol().getNombre(), //obtengo el nombre del rol como "string"
            usuario.getFecha_creacion(),
            usuario.getUltima_sesion()
        );
    }
    private Usuario toDomain(UsuarioEntity entity) {
        // Usamos el constructor vacío y luego el setter
        Rol rol = new Rol();
        rol.setNombre(entity.getRol()); // El entity.getRol() devuelve el String del nombre

        return new Usuario(
            entity.getId(),
            entity.getNombre(),
            entity.getApellido(),
            entity.getDni(),
            entity.getEmail(),
            rol, //envio un objeto Rol, no un String
            entity.getFecha_creacion(),
            entity.getUltima_sesion()
        );
    }
    
}
