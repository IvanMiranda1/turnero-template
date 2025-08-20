package com.app.infrastructure.persistence.repository;

import com.app.infrastructure.persistence.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolJpaRepository extends JpaRepository<RolEntity, String> {
    Optional<RolEntity> findByNombre(String nombre);
}
