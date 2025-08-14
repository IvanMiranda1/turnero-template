package com.app.domain.port;

import java.util.List;

import com.app.domain.model.Cliente;

public interface ClienteRepository{
    Cliente createOrUpdate(Cliente cliente);
    void delete(String id);
    Cliente findById(String id);
    List<Cliente> findAll();
    List<Cliente> findByNombre(String nombre);
    List<Cliente> findByApellido(String apellido);
    List<Cliente> findByEmail(String email);
    List<Cliente> findByTelefono(String telefono);
    Long countByEmailOrDniOrTelefonoAndId(String email, String dni, String telefono, String id);
}

// repository interface define los metodos que se implementaran en el persistence/ClientePostgresAdapter.java