package com.app.domain.port;

import java.util.List;
import java.util.Optional;

import com.app.domain.model.Servicio;

public interface ServicioRepository {
    Servicio createOrUpdate(Servicio servicio);
    void delete(String id);
    Optional<Servicio> findById(String id);
    List<Servicio> findAll();
    List<Servicio> findByNombre(String nombre);

    Long countByNombre(String nombre);
    Long TurnosAsociados(String idServicio);
}
