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
        return servicioRepository.createOrUpdate(servicio);
    }

    public Servicio update(Servicio servicio) {
        return servicioRepository.createOrUpdate(servicio);
    }
    
    public void delete(String id) {
        servicioRepository.delete(id);
    }

    public Servicio findById(String id) {
        return servicioRepository.findById(id);
    }

    public List<Servicio> findAll() {
        return servicioRepository.findAll();
    }

    public List<Servicio> findByNombre(String nombre) {
        return servicioRepository.findByNombre(nombre);
    }
    
}