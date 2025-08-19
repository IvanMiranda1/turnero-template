package com.app.infrastructure.adapter.rest;

import java.util.List;

import com.app.domain.model.Rol;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.application.usecase.UsuarioService;
import com.app.domain.model.Usuario;
import com.app.infrastructure.dto.UsuarioDTO;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
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

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<Usuario> usuarios = usuarioService.findAll();
        List<UsuarioDTO> usuarioDTOs = usuarios.stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(usuarioDTOs);
    }

    @GetMapping("/dni")
    public ResponseEntity<List<UsuarioDTO>> findByDni(String dni) {
        List<Usuario> usuarios = usuarioService.findByDni(dni);
        if (usuarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<UsuarioDTO> usuarioDTOs = usuarios.stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(usuarioDTOs);
    }

    @GetMapping("/nombre")
    public ResponseEntity<List<UsuarioDTO>> findByNombre(String nombre) {
        List<Usuario> usuarios = usuarioService.findByNombre(nombre);
        if (usuarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<UsuarioDTO> usuarioDTOs = usuarios.stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(usuarioDTOs);
    }

    @GetMapping("/apellido")
    public ResponseEntity<List<UsuarioDTO>> findByApellido(String apellido) {
        List<Usuario> usuarios = usuarioService.findByApellido(apellido);
        if (usuarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<UsuarioDTO> usuarioDTOs = usuarios.stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(usuarioDTOs);
    }

//    @GetMapping("/id")
//    public ResponseEntity<UsuarioDTO> findById(String id) {
//        Usuario usuario = usuarioService.findById(id);
//        if (usuario == null) {
//            return ResponseEntity.notFound().build();
//        }
//        UsuarioDTO usuarioDTO = toDTO(usuario);
//        return ResponseEntity.ok(usuarioDTO);
//    }
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



    //metodos aux
    private Usuario toDomain(UsuarioDTO dto) {
        // 1. IMPORTANTE: Necesitas importar la clase Rol para crear un objeto.
        // import com.app.domain.model.Rol;

        // 2. Antes de crear el Usuario, crea un objeto Rol a partir del String del DTO.
        // Asume que tienes un constructor o un setter para el nombre del rol.
        Rol rol = new Rol();
        rol.setNombre(dto.getRol());

        // 3. Pasa el objeto 'rol' recién creado al constructor de Usuario.
        return new Usuario(
                dto.getId(),
                dto.getNombre(),
                dto.getApellido(),
                dto.getDni(),
                dto.getEmail(),
                rol, // Aquí pasas el objeto Rol, no el String
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
                usuario.getRol().getNombre(), // Obtienes el String del objeto Rol
                usuario.getFecha_creacion(),
                usuario.getUltima_sesion()
        );
    }
}
