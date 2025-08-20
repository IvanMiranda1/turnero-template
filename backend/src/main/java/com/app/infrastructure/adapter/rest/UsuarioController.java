package com.app.infrastructure.adapter.rest;

import com.app.application.usecase.RolService;
import com.app.application.usecase.UsuarioService;
import com.app.domain.model.Rol;
import com.app.domain.model.Usuario;
import com.app.infrastructure.dto.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final RolService rolService;

    public UsuarioController(UsuarioService usuarioService, RolService rolService) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = toDomain(usuarioDTO);
        Usuario createdUsuario = usuarioService.create(usuario);
        UsuarioDTO createdUsuarioDTO = toDTO(createdUsuario);
        return ResponseEntity.ok(createdUsuarioDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<UsuarioDTO> update(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = toDomain(usuarioDTO);
        Usuario updatedUsuario = usuarioService.update(usuario);
        UsuarioDTO updatedUsuarioDTO = toDTO(updatedUsuario);
        return ResponseEntity.ok(updatedUsuarioDTO);
    }

    @GetMapping("/id")
    public ResponseEntity<UsuarioDTO> findById(String id) {
        try {
            Usuario usuario = usuarioService.findById(id);
            UsuarioDTO usuarioDTO = toDTO(usuario);
            return ResponseEntity.ok(usuarioDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(String id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Métodos auxiliares de mapeo
    private Usuario toDomain(UsuarioDTO dto) {
        // Se busca el Rol en la base de datos a través del RolService
        Rol rol = rolService.findById(dto.getRol());

        return new Usuario(
                dto.getId(),
                dto.getNombre(),
                dto.getApellido(),
                dto.getDni(),
                dto.getEmail(),
                rol,
                dto.getFecha_creacion(),
                dto.getUltima_sesion()
        );
    }

    private UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getDni(),
                usuario.getEmail(),
                usuario.getRol().getNombre(),
                usuario.getFecha_creacion(),
                usuario.getUltima_sesion()
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<Usuario> usuarios = usuarioService.findAll();
        List<UsuarioDTO> dtos = usuarios.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}