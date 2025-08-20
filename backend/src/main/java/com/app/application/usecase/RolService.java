package com.app.application.usecase;

import com.app.domain.model.Rol;
import com.app.domain.port.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {
    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public Rol create(Rol rol) {
        // Lógica de negocio para la creación de un rol
        // Por ejemplo: verificar la unicidad del nombre del rol
        return rolRepository.createOrUpdate(rol);
    }

    public Rol update(Rol rol) {
        // Validación para la actualización: el ID debe existir.
        if (rol.getId() == null || rol.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del rol es obligatorio para actualizar.");
        }
        Optional<Rol> existente = rolRepository.findById(rol.getId());
        if (!existente.isPresent()) {
            throw new IllegalArgumentException("No existe un rol con el ID proporcionado para actualizar.");
        }
        return rolRepository.createOrUpdate(rol);
    }

    public void delete(String id) {
        // Validación para la eliminación: el ID debe existir.
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del rol es obligatorio para eliminar.");
        }
        if (!rolRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("No existe un rol con el ID proporcionado para eliminar.");
        }
        rolRepository.delete(id);
    }

    public Rol findById(String id) {
        // Validación para la búsqueda por ID.
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del rol es obligatorio para buscar.");
        }
        return rolRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe un rol con el ID proporcionado."));
    }

    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    public List<Rol> findByNombre(String nombre) {
        return rolRepository.findByNombre(nombre);
    }
}