package com.app.cliente;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.app.cliente.ClienteDTO.OnCreate;
import com.app.cliente.ClienteDTO.OnUpdate;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService service;

    public ClienteController(ClienteService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Validated(OnCreate.class)@RequestBody ClienteDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ClienteDTO> update(@Validated(OnUpdate.class)@RequestBody ClienteDTO dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<ClienteDTO>> findByNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(service.findByNombre(nombre));
    }
    @GetMapping("/apellido/{apellido}")
    public ResponseEntity<List<ClienteDTO>> findByApellido(@PathVariable String apellido) {
        return ResponseEntity.ok(service.findByApellido(apellido));
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<List<ClienteDTO>> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(service.findByEmail(email));
    }
    @GetMapping("/telefono/{telefono}")
    public ResponseEntity<List<ClienteDTO>> findByTelefono(@PathVariable String telefono) {
        return ResponseEntity.ok(service.findByTelefono(telefono));
    }
}
