package com.app.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.domain.model.Cliente;
import com.app.domain.port.ClienteRepository;

@Service
public class ClienteService {
    private final ClienteRepository ClienteRepository;

    public ClienteService(ClienteRepository ClienteRepository) {
        this.ClienteRepository = ClienteRepository;
    }

    public Cliente create(Cliente cliente) {
        return ClienteRepository.createOrUpdate(cliente);
    }

    public Cliente update(Cliente cliente) {
        return ClienteRepository.createOrUpdate(cliente);
    }
    
    public void delete(String id) {
        ClienteRepository.delete(id);
    }

    public Cliente findById(String id) {
        return ClienteRepository.findById(id);
    }

    public List<Cliente> findAll() {
        return ClienteRepository.findAll();
    }

    public List<Cliente> findByNombre(String nombre) {
        return ClienteRepository.findByNombre(nombre);
    }

    public List<Cliente> findByApellido(String apellido) {
        return ClienteRepository.findByApellido(apellido);
    }

    public List<Cliente> findByEmail(String email) {
        return ClienteRepository.findByEmail(email);
    }

    public List<Cliente> findByTelefono(String telefono) {
        return ClienteRepository.findByTelefono(telefono);
    }

}
