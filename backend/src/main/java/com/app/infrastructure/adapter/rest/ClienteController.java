 package com.app.infrastructure.adapter.rest;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.application.usecase.ClienteService;
import com.app.domain.model.Cliente;
import com.app.infrastructure.dto.ClienteDTO;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = toDomain(clienteDTO);
        //llama al servicio
        Cliente createdCliente = clienteService.create(cliente);
        //convierte de dominio a dto
        ClienteDTO createdClienteDTO = toDTO(createdCliente);
        return ResponseEntity.ok(createdClienteDTO);
    }
    
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = toDomain(clienteDTO);
        //llama al servicio
        Cliente updatedCliente = clienteService.update(cliente);
        //convierte de dominio a dto
        ClienteDTO updatedClienteDTO = toDTO(updatedCliente);
        return ResponseEntity.ok(updatedClienteDTO);
    }

    @DeleteMapping("/{id}") 
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (id == null || id.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable String id) {
        Cliente cliente = clienteService.findById(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        ClienteDTO clienteDTO = toDTO(cliente);
        return ResponseEntity.ok(clienteDTO);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> clientes = clienteService.findAll();
        List<ClienteDTO> clienteDTOs = clientes.stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(clienteDTOs);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<ClienteDTO>> findByNombre(@PathVariable String nombre) {
        List<Cliente> clientes = clienteService.findByNombre(nombre);
        List<ClienteDTO> clienteDTOs = clientes.stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(clienteDTOs);
    }

    @GetMapping("/apellido/{apellido}")
    public ResponseEntity<List<ClienteDTO>> findByApellido(@PathVariable String apellido) {
        List<Cliente> clientes = clienteService.findByApellido(apellido);
        List<ClienteDTO> clienteDTOs = clientes.stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(clienteDTOs);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<ClienteDTO>> findByEmail(@PathVariable String email) {
        List<Cliente> clientes = clienteService.findByEmail(email);
        List<ClienteDTO> clienteDTOs = clientes.stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(clienteDTOs);
    }

    @GetMapping("/telefono/{telefono}")
    public ResponseEntity<List<ClienteDTO>> findByTelefono(@PathVariable String telefono) {
        List<Cliente> clientes = clienteService.findByTelefono(telefono);
        List<ClienteDTO> clienteDTOs = clientes.stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(clienteDTOs);
    }
    




// Métodos de conversión (DTO <-> Dominio)
    private Cliente toDomain(ClienteDTO dto) {
        return new Cliente(dto.getId(), dto.getNombre(), dto.getApellido(), dto.getEmail(), dto.getTelefono(), dto.getDni());
    }

    private ClienteDTO toDTO(Cliente cliente) {
        return new ClienteDTO(cliente.getId(), cliente.getNombre(), cliente.getApellido(), cliente.getEmail(), cliente.getTelefono(), cliente.getDni());
    }

}