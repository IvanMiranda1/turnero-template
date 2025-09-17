package com.app.usuario;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.app.utils.DataNormalizer;


@Service
public class UsuarioService {
    private final UsuarioRepository repo;
    private final UsuarioMapper mapper;

    public UsuarioService(UsuarioRepository repo, UsuarioMapper mapper) {this.repo = repo; this.mapper = mapper;}

    public UsuarioDTO create(UsuarioDTO dto) {
        normalizar(dto);
        Usuario entity = mapper.toEntity(dto);
        entity = repo.save(entity);
        return mapper.toDTO(entity);
    }

    public UsuarioDTO update(UsuarioDTO dto) {
        UUID id = UUID.fromString(dto.getId());
        repo.findById(id).orElseThrow(() -> new IllegalArgumentException("No existe"));
        normalizar(dto);
        Usuario entity = mapper.toEntity(dto);
        entity = repo.save(entity);
        return mapper.toDTO(entity);
    }

    public void delete(UUID id) {
        repo.deleteById(id);
    }

    public UsuarioDTO findById(UUID id) {
        Usuario u = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("No existe"));
        return mapper.toDTO(u);
    }

    public List<UsuarioDTO> findAll() {
        return repo.findAll().stream().map(mapper::toDTO).toList();
    }

    public List<Usuario> findByDni(String dni) {
        dni = DataNormalizer.normalizeDni(dni);

        return repo.findByDniContaining(dni);
    }

    public List<Usuario> findByNombre(String nombre) {
        return repo.findByNombreContaining(nombre);
    }

    public List<Usuario> findByApellido(String apellido) {
        return repo.findByApellidoContaining(apellido);
    }

    //metodos aux
    private void normalizar(UsuarioDTO d){
        if (d.getNombre()!=null){
            d.setNombre(DataNormalizer.capitalize(d.getNombre()));
        }
        if(d.getApellido()!=null){
            d.setApellido(DataNormalizer.capitalize(d.getApellido()));
        }
        if(d.getDni()!=null) {
            d.setDni(DataNormalizer.normalizeDni(d.getDni()));
        }
        if(d.getEmail()!=null){
            d.setEmail(d.getEmail().trim().toLowerCase());
        }
    }

    /***
        Si ya validas a nivel controller y no hay otros usos: puedes quitar require().
        Para cobertura más amplia sin require(): añade @Validated y @NotNull como arriba.
        Si haces llamadas internas dentro de la misma clase y quieres validar siempre: conserva require() o reestructura.

        Es valido no usar requireId() en findById/delete si solo se llaman desde un controller REST con @PathVariable UUID id.
        Los métodos delete/findById sólo se invocan desde un Controller REST con @PathVariable UUID id (Spring nunca llama al método con id null; si el path no trae valor es 404 antes).
     */
}