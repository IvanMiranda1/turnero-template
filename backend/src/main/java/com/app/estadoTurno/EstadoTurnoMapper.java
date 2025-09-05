package com.app.estadoTurno;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class EstadoTurnoMapper {
    
    public EstadoTurno toEntity(EstadoTurnoDTO dto) {
        if(dto==null) return null;
        EstadoTurno estadoTurno = new EstadoTurno();
        if(dto.getId()!=null && !dto.getId().isBlank())
            estadoTurno.setId(UUID.fromString(dto.getId()));
        estadoTurno.setNombre(dto.getNombre());
        return estadoTurno;
    }

    public EstadoTurnoDTO toDTO(EstadoTurno e) {
        if (e==null) return null;
        EstadoTurnoDTO dto = new EstadoTurnoDTO();
        if (dto.getId()!=null)
            dto.setId(e.getId().toString());
        dto.setNombre(e.getNombre());
        return dto;
    }
}
