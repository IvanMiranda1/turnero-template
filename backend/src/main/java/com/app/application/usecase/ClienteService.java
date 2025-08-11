package com.app.application.usecase;

import org.springframework.stereotype.Service;

import com.app.domain.model.Cliente;
import com.app.domain.port.ClienteRepository;

@Service
public class ClienteService {
    private final ClienteRepository ClienteRepository;

    public ClienteService(ClienteRepository ClienteRepository) {
        this.ClienteRepository = ClienteRepository;
    }

    //Funciones del crud
    public Cliente create(Cliente cliente) {
        //validaciones de por ejemplo email, telefono, etc
        return ClienteRepository.CreateOrUpdate(cliente);
    }

    public Cliente update(Cliente cliente) {
        return ClienteRepository.CreateOrUpdate(cliente);
    }

    public Cliente findById(String id) {
        return ClienteRepository.FindById(id);
    }

    public void delete(String id) {
        ClienteRepository.Delete(id);
    }
}
