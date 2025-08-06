package com.app.domain.port;

import java.util.List;

import com.app.domain.model.Cliente;

public interface ClienteRepository{
    Cliente CreateOrUpdate(Cliente cliente);
    Cliente FindById(String id);
    void Delete(String id);
    List<Cliente> FindAll();
    List<Cliente> FindByNombre(String nombre);
    List<Cliente> FindByApellido(String apellido);
    List<Cliente> FindByEmail(String email);
    List<Cliente> FindByTelefono(String telefono);
}

// repository interface define los metodos que se implementaran en el persistence/ClientePostgresAdapter.java