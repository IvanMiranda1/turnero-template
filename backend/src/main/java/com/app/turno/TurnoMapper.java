package com.app.turno;


import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class TurnoMapper {

    public Turno toEntity(TurnoDTO dto) {
        if(dto==null) return null;
        Turno t = new Turno();
        if(dto.getId()!=null && !dto.getId().isBlank())
            t.setId(UUID.fromString(dto.getId()));
        t.setFechaAlta(dto.getFechaAlta());
        t.setFechaTurno(dto.getFechaTurno());
        t.setFkEstado(UUID.fromString(dto.getFkEstado()));
        t.setFkCliente(UUID.fromString(dto.getFkCliente()));
        t.setFkUsuario(UUID.fromString(dto.getFkUsuario()));
        t.setFkServicio(UUID.fromString(dto.getFkServicio()));
        t.setDetalle(dto.getDetalle());
        return t;
    }
    public TurnoDTO toDTO(Turno t) {
        if(t==null) return null;
        TurnoDTO dto = new TurnoDTO();
        if(t.getId()!=null)
            dto.setId(t.getId().toString());
        dto.setFechaAlta(t.getFechaAlta());
        dto.setFechaTurno(t.getFechaTurno());
        dto.setFkEstado(t.getFkEstado().toString());
        dto.setFkCliente(t.getFkCliente().toString());
        dto.setFkUsuario(t.getFkUsuario().toString());
        dto.setFkServicio(t.getFkServicio().toString());
        dto.setDetalle(t.getDetalle());
        return dto;
    }
}
