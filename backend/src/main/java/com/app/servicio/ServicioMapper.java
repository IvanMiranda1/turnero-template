package com.app.servicio;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class ServicioMapper {
    public Servicio toEntity(ServicioDTO dto) {
        if(dto==null) return null;
        Servicio servicio = new Servicio();
        if(dto.getId()!=null && !dto.getId().isBlank())
            servicio.setId(UUID.fromString(dto.getId()));
        servicio.setNombre(dto.getNombre());
        servicio.setDuracionEstimada(dto.getDuracionEstimada());
        servicio.setPrecio(dto.getPrecio());
        servicio.setDetalle(dto.getDetalle());
        return servicio;
    }

    public ServicioDTO toDTO(Servicio s) {
        if (s==null) return null;
        ServicioDTO dto = new ServicioDTO();
        if (s.getId()!=null)
            dto.setId(s.getId().toString());
        dto.setNombre(s.getNombre());
        dto.setDuracionEstimada(s.getDuracionEstimada());
        dto.setPrecio(s.getPrecio());
        dto.setDetalle(s.getDetalle());
        return dto;
    }
}
