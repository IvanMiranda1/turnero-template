package com.app.infrastructure.adapter.persistence;

import java.util.List;

import org.springframework.stereotype.Component;

import com.app.domain.model.Cliente;
import com.app.domain.port.ClienteRepository;
import com.app.infrastructure.persistence.entity.ClienteEntity;
import com.app.infrastructure.persistence.repository.ClienteJpaRepository;

//Se implementas los metodos definidos en el repository interface
//Los 
/*Cliente CreateOrUpdate(Cliente cliente);
    Cliente FindById(String id);
    void Delete(String id);
    List<Cliente> FindAll();
    List<Cliente> FindByNombre(String nombre);
    List<Cliente> FindByApellido(String apellido);
    List<Cliente> FindByEmail(String email);
    List<Cliente> FindByTelefono(String telefono);*/
@Component
public class ClientePostgresAdapter implements ClienteRepository {
    
    private final ClienteJpaRepository clienteJpaRepository;

    public ClientePostgresAdapter(ClienteJpaRepository clienteJpaRepository) {
        this.clienteJpaRepository = clienteJpaRepository;
    }

    @Override
    public Cliente createOrUpdate(Cliente cliente) {
        ClienteEntity entity = toEntity(cliente);
        ClienteEntity savedEntity = clienteJpaRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Cliente findById(String id) {
        return clienteJpaRepository.findById(id)
                .map(this::toDomain)
                .orElse(null);
    }

    @Override
    public void delete(String id) {
        clienteJpaRepository.deleteById(id);
    }

     @Override
    public List<Cliente> findAll() {
        return clienteJpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Cliente> findByApellido(String apellido) {
        return clienteJpaRepository.findByApellido(apellido)
                .stream()
                .map(this::toDomain)
                .toList();  
    }

    @Override
    public List<Cliente> findByEmail(String email) {
        return clienteJpaRepository.findByEmail(email)
                .stream()
                .map(this::toDomain)
                .toList();        
    }

    @Override
    public List<Cliente> findByNombre(String nombre) {
        return clienteJpaRepository.findByNombre(nombre)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Cliente> findByTelefono(String telefono) {
        return clienteJpaRepository.findByTelefono(telefono)
                .stream()
                .map(this::toDomain)
                .toList();
    }


    private ClienteEntity toEntity(Cliente cliente) {
        // Usa el constructor generado por Lombok para crear la entidad en una sola línea
        return new ClienteEntity(
            cliente.getId(),
            cliente.getNombre(),
            cliente.getApellido(),
            cliente.getEmail(),
            cliente.getTelefono(),
            cliente.getDni()
        );
    }

    private Cliente toDomain(ClienteEntity entity) {
        // Lógica de conversión de ClienteEntity a Cliente
        return new Cliente(
            entity.getId(),
            entity.getNombre(),
            entity.getApellido(),
            entity.getEmail(),
            entity.getTelefono(),
            entity.getDni()
        );
    }

}

//El dominio, jamas ve ni una entidad de JPA
//La capa de persistencia, convierte el return del JPA a Dominio
// 