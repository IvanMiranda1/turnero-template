package com.app.domain.port;

import com.app.domain.model.Rol;

import java.util.List;
import java.util.Optional;

public interface RolRepository {
    Rol createOrUpdate(Rol rol);
    void delete(String id);
    Optional<Rol> findById(String id);
    List<Rol> findAll();
    List<Rol> findByNombre(String nombre);
}
