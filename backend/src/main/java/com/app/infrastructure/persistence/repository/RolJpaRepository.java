package com.app.infrastructure.persistence.repository;

import com.app.infrastructure.persistence.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RolJpaRepository extends JpaRepository<RolEntity, String> {
    @Query("SELECT r FROM RolEntity r WHERE r.nombre = ?1")
    Optional<RolEntity> findByNombre(String nombre);


}
