package com.app.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.domain.model.Servicio;
import com.app.domain.port.ServicioRepository;

@Service
public class ServicioService {
    private final ServicioRepository servicioRepository;

    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    public Servicio create(Servicio servicio) {
        //si tiene ID, no puede ser al crear un servicio
        if ((servicio.getId() != null && !servicio.getId().trim().isEmpty()) ||
            servicio.getNombre() == null || servicio.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("No puede tener ID al crear un servicio y el nombre es obligatorio.");
        }
        // Validar unicidad del nombre
        if (servicioRepository.countByNombre(servicio.getNombre()) > 0) {
            throw new IllegalArgumentException("Ya existe un servicio con ese nombre.");
        }
        // Capitalizar el nombre del servicio
        servicio.setNombre(servicio.getNombre().trim().toUpperCase());
        return servicioRepository.createOrUpdate(servicio);
    }

    public Servicio update(Servicio servicio) {
        if (servicio.getId() == null || servicio.getId().trim().isEmpty() ||
            servicio.getNombre() == null || servicio.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El ID y el nombre del servicio son obligatorios para actualizar.");
        }
        Servicio existente = servicioRepository.findById(servicio.getId())
            .orElseThrow(() -> new IllegalArgumentException("No existe un servicio con el ID proporcionado."));
        
        // Validar unicidad del nombre si ha cambiado
        if (!servicio.getNombre().equals(existente.getNombre()) &&
            servicioRepository.countByNombre(servicio.getNombre()) > 0) {
            throw new IllegalArgumentException("Ya existe un servicio con ese nombre.");
        }
        // Capitalizar el nombre del servicio
        servicio.setNombre(servicio.getNombre().trim().toUpperCase());
        // Actualizar el servicio
        return servicioRepository.createOrUpdate(servicio);
    }
    
    public void delete(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del servicio es obligatorio para eliminar.");
        }
        if (!servicioRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("No existe un servicio con el ID proporcionado.");
        }
        if (servicioRepository.TurnosAsociados(id) > 0) {
            throw new IllegalArgumentException("No se puede eliminar el servicio porque tiene turnos asociados.");
        }
        servicioRepository.delete(id);
    }

    public Servicio findById(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del servicio es obligatorio para buscar.");
        }
        return servicioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("No existe un servicio con el ID proporcionado."));
    }

    public List<Servicio> findAll() {
        return servicioRepository.findAll();
    }

    public List<Servicio> findByNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del servicio es obligatorio para buscar.");
        }
        return servicioRepository.findByNombre(nombre);
    }
    
}