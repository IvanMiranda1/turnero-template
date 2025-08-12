 package com.app.infrastructure.adapter.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.application.usecase.ClienteService;
import com.app.domain.model.Cliente;
import com.app.infrastructure.dto.ClienteDTO;

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



// Métodos de conversión (DTO <-> Dominio)
    private Cliente toDomain(ClienteDTO dto) {
        return new Cliente(dto.getId(), dto.getNombre(), dto.getApellido(), dto.getEmail(), dto.getTelefono(), dto.getDni());
    }

    private ClienteDTO toDTO(Cliente cliente) {
        return new ClienteDTO(cliente.getId(), cliente.getNombre(), cliente.getApellido(), cliente.getEmail(), cliente.getTelefono(), cliente.getDni());
    }

}