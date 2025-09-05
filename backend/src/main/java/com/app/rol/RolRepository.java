package com.app.rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.List;


@Repository
public interface RolRepository extends JpaRepository<Rol, UUID> {
    List<Rol> findByNombreRol(String nombreRol);
}
