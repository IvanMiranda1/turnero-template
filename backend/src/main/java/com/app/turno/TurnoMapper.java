package com.app.turno;


import java.util.UUID;

import org.springframework.stereotype.Component;

import com.app.cliente.Cliente;
import com.app.estadoTurno.EstadoTurno;
import com.app.servicio.Servicio;
import com.app.usuario.Usuario;

@Component
public class TurnoMapper {

    public Turno toEntity(TurnoDTO dto) {
        if(dto==null) return null;
        Turno t = new Turno();
        if(dto.getId()!=null && !dto.getId().isBlank())
            t.setId(UUID.fromString(dto.getId()));
        t.setFechaAlta(dto.getFechaAlta());
        t.setFechaTurno(dto.getFechaTurno());
        EstadoTurno estadoTurno = new EstadoTurno();
        estadoTurno.setId(UUID.fromString(dto.getFkEstado()));
        t.setEstadoTurno(estadoTurno);
        Cliente cliente = new Cliente();
        cliente.setId(UUID.fromString(dto.getFkCliente()));
        t.setCliente(cliente);
        Usuario usuario = new Usuario();
        usuario.setId(UUID.fromString(dto.getFkUsuario()));
        t.setUsuario(usuario);
        Servicio servicio = new Servicio();
        servicio.setId(UUID.fromString(dto.getFkServicio()));
        t.setServicio(servicio);
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
        dto.setFkEstado(t.getEstadoTurno().getId().toString());
        dto.setFkCliente(t.getCliente().getId().toString());
        dto.setFkUsuario(t.getUsuario().getId().toString());
        dto.setFkServicio(t.getServicio().getId().toString());
        dto.setDetalle(t.getDetalle());
        return dto;
    }
}
