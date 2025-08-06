package com.app.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.infrastructure.persistence.entity.ClienteEntity;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, String> {
    
}
