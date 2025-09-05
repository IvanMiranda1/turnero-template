package com.app.cliente;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    
    public Cliente toEntity(ClienteDTO dto) {
        if(dto==null) return null;
        Cliente c = new Cliente();
        if(dto.getId()!=null && !dto.getId().isBlank())
            c.setId(UUID.fromString(dto.getId()));
        c.setNombre(dto.getNombre());
        c.setApellido(dto.getApellido());
        c.setEmail(dto.getEmail());
        c.setTelefono(dto.getTelefono());
        c.setDni(dto.getDni());
        return c;
    }
    public ClienteDTO toDTO(Cliente c) {
        if(c==null) return null;
        ClienteDTO dto = new ClienteDTO();
        if(c.getId()!=null)
            dto.setId(c.getId().toString());
        dto.setNombre(c.getNombre());
        dto.setApellido(c.getApellido());
        dto.setEmail(c.getEmail());
        dto.setTelefono(c.getTelefono());
        dto.setDni(c.getDni());
        return dto;
    }
}
