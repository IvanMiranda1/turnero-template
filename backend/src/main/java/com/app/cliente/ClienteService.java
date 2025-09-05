package com.app.cliente;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.app.utils.DataNormalizer;

@Service
public class ClienteService {
    private final ClienteRepository repo;
    private final ClienteMapper mapper;

    public ClienteService(ClienteRepository repo, ClienteMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public ClienteDTO create(ClienteDTO cliente) {
        capitalizacionDeCliente(cliente);
        if (repo.countByEmailOrDniOrTelefonoAndId(cliente.getEmail(), cliente.getDni(), cliente.getTelefono(), null) > 0) {
            throw new IllegalArgumentException("Ya existe un cliente con ese email, DNI o teléfono.");
        }
        Cliente entity = mapper.toEntity(cliente);
        entity = repo.save(entity);
        return mapper.toDTO(entity);
    }

    public ClienteDTO update(ClienteDTO dto) {
        Cliente existente = repo.findById(UUID.fromString(dto.getId()))
            .orElseThrow(() -> new IllegalArgumentException("No existe un cliente con el ID proporcionado."));
        if ((!dto.getEmail().equals(existente.getEmail()) ||
            !dto.getDni().equals(existente.getDni()) ||
            !dto.getTelefono().equals(existente.getTelefono())) &&
            repo.countByEmailOrDniOrTelefonoAndId(dto.getEmail(), dto.getDni(), dto.getTelefono(), UUID.fromString(dto.getId())) > 0) {
            throw new IllegalArgumentException("Ya existe un cliente con ese email, DNI o teléfono.");
        }
        validarCamposDelCliente(dto);
        capitalizacionDeCliente(dto);
        Cliente entity = mapper.toEntity(dto);
        entity = repo.save(entity);
        return mapper.toDTO(entity);
    }

    public void delete(UUID id) {
        repo.deleteById(id);
    }

    public ClienteDTO findById(UUID id) {
        return repo.findById(id)
            .map(mapper::toDTO)
            .orElseThrow(() -> new IllegalArgumentException("No existe un cliente con el ID proporcionado."));
    }

    public List<ClienteDTO> findAll() {
        return repo.findAll().stream().map(mapper::toDTO).toList();
    }

    public List<ClienteDTO> findByNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("El nombre del cliente es obligatorio para buscar.");
        return repo.findByNombre(nombre).stream().map(mapper::toDTO).toList();
    }

    public List<ClienteDTO> findByApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty())
            throw new IllegalArgumentException("El apellido del cliente es obligatorio para buscar.");
        return repo.findByApellido(apellido).stream().map(mapper::toDTO).toList();
    }

    public List<ClienteDTO> findByEmail(String email) {
        if (email.trim().isEmpty())
            throw new IllegalArgumentException("El email es obligatorio para buscar.");
        return repo.findByEmail(email).stream().map(mapper::toDTO).toList();
    }

    public List<ClienteDTO> findByTelefono(String telefono) {
        if (telefono.matches("^0+$"))
            throw new IllegalArgumentException("El teléfono no puede ser solo ceros.");
        return repo.findByTelefono(telefono).stream().map(mapper::toDTO).toList();
    }

    // Utils

    public void validarCamposDelCliente(ClienteDTO c) {
        if (c.getTelefono().matches("^0+$")) throw new IllegalArgumentException("El teléfono no puede ser solo ceros.");
    }

    //metodos aux
    public void capitalizacionDeCliente(ClienteDTO c) { 
        c.setNombre(DataNormalizer.capitalizarPalabras(c.getNombre())); 
        c.setApellido(DataNormalizer.capitalizarPalabras(c.getApellido())); 
    }
}
