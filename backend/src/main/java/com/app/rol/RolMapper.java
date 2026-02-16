package com.app.rol;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class RolMapper {
    public Rol toEntity(RolDTO dto) {
        if(dto==null)return null;
        Rol entity = new Rol();
        if(dto.getId()!=null && !dto.getId().isBlank())
            entity.setId(UUID.fromString(dto.getId()));
        entity.setNombreRol(dto.getNombreRol());
        return entity;
    }

    public RolDTO toDTO(Rol entity) {
        if(entity==null)return null;
        RolDTO dto = new RolDTO();
        if(entity.getId()!=null)
            dto.setId(entity.getId().toString());
        dto.setNombreRol(entity.getNombreRol());
        return dto;
    }
}
