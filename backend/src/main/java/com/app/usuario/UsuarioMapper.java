package com.app.usuario;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    
    public Usuario toEntity(UsuarioDTO dto) {
        if(dto==null) return null;
        Usuario usuario = new Usuario();
        if(dto.getId()!=null && !dto.getId().isBlank())
            usuario.setId(UUID.fromString(dto.getId()));
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setDni(dto.getDni());
        usuario.setEmail(dto.getEmail());
        usuario.setFkRol(dto.getFkRol());
        usuario.setFechaCreacion(dto.getFechaCreacion());
        usuario.setUltimaSesion(dto.getUltimaSesion());
        return usuario;
    }

    public UsuarioDTO toDTO(Usuario u) {
        if (u==null) return null;
        UsuarioDTO dto = new UsuarioDTO();
        if (dto.getId()!=null)
            dto.setId(u.getId().toString());
        dto.setNombre(u.getNombre());
        dto.setApellido(u.getApellido());
        dto.setDni(u.getDni());
        dto.setEmail(u.getEmail());
        dto.setFechaCreacion(u.getFechaCreacion());
        dto.setUltimaSesion(u.getUltimaSesion());
        return dto;
    }

}
