package com.app.domain.port;

import java.util.List;

import com.app.domain.model.Servicio;

public interface ServicioRepository {
    Servicio createOrUpdate(Servicio servicio);
    void delete(String id);
    Servicio findById(String id);
    List<Servicio> findAll();
    List<Servicio> findByNombre(String nombre);
}
