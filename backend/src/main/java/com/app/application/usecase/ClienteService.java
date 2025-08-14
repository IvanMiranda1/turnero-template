package com.app.application.usecase;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.app.domain.model.Cliente;
import com.app.domain.port.ClienteRepository;
import com.app.domain.util.RegexUtils;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente create(Cliente cliente) {
        //validaciones de campos obligatorios y formato correcto
        validarCamposDelCliente(cliente);
        //capitalizacion de nombre y apellidos
        capitalizacionDeCliente(cliente);

        if (clienteRepository.countByEmailOrDniOrTelefonoAndId(cliente.getEmail(), cliente.getDni(), cliente.getTelefono(), null) > 0) {
            throw new IllegalArgumentException("Ya existe un cliente con ese email, DNI o teléfono.");
        }

        return clienteRepository.createOrUpdate(cliente);
    }

    public Cliente update(Cliente cliente) {
        if (cliente.getId() == null || cliente.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del cliente es obligatorio para actualizar.");
        }
        Cliente existente = clienteRepository.findById(cliente.getId());
        if (existente == null) {
            throw new IllegalArgumentException("No existe un cliente con el ID proporcionado.");
        }
        // Solo validar unicidad si cambió email/dni/teléfono
        //se pasa el id del cliente para que no se considere a si mismo al verificar unicidad
        if ((!cliente.getEmail().equals(existente.getEmail()) ||
            !cliente.getDni().equals(existente.getDni()) ||
            !cliente.getTelefono().equals(existente.getTelefono())) &&
            clienteRepository.countByEmailOrDniOrTelefonoAndId(cliente.getEmail(), cliente.getDni(), cliente.getTelefono(), cliente.getId()) > 0) {
            throw new IllegalArgumentException("Ya existe un cliente con ese email, DNI o teléfono.");
        }
        //validaciones de campos obligatorios y formato correcto
        validarCamposDelCliente(cliente);
        //capitalizacion de nombre y apellidos
        capitalizacionDeCliente(cliente);
        return clienteRepository.createOrUpdate(cliente);
    }
    
    public void delete(String id) {
        clienteRepository.delete(id);
    }

    public Cliente findById(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del cliente es obligatorio para buscar.");
        }
        return clienteRepository.findById(id);
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public List<Cliente> findByNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente es obligatorio para buscar.");
        }
        return clienteRepository.findByNombre(nombre);
    }

    public List<Cliente> findByApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido del cliente es obligatorio para buscar.");
        }
        return clienteRepository.findByApellido(apellido);
    }

    public List<Cliente> findByEmail(String email) {
        if (email.trim().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio para buscar.");
        }
        if (!Pattern.matches(RegexUtils.EMAIL_REGEX, email)) {
            throw new IllegalArgumentException("Email inválido");
        }
        return clienteRepository.findByEmail(email);
    }

    public List<Cliente> findByTelefono(String telefono) {
        if (telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El telefono del cliente es obligatorio para buscar.");
        }
        if (!Pattern.matches(RegexUtils.TELEFONO_REGEX, telefono)){
            throw new IllegalArgumentException("Telefono inválido");
        }
        if (telefono.matches("^0+$")) {
            throw new IllegalArgumentException("El teléfono no puede ser solo ceros.");
        }
        return clienteRepository.findByTelefono(telefono);
    }


    //metodos aux

    public static String capitalizar(String texto) {
        if (texto == null || texto.isEmpty()) return texto;
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }

    public static String capitalizarPalabras(String texto) {
        if (texto == null || texto.isEmpty()) return texto;
        String[] palabras = texto.trim().split("\\s+");
        StringBuilder resultado = new StringBuilder();
        for (String palabra : palabras) {
            resultado.append(capitalizar(palabra)).append(" ");
        }
        return resultado.toString().trim();
    }

    public void validarCamposDelCliente(Cliente cliente) {
        //validar campos vacios y null
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty() ||
            cliente.getApellido() == null || cliente.getApellido().trim().isEmpty() ||
            cliente.getEmail() == null || cliente.getEmail().trim().isEmpty() ||
            cliente.getDni() == null || cliente.getDni().trim().isEmpty() ||
            cliente.getTelefono() == null || cliente.getTelefono().trim().isEmpty()) {
            throw new IllegalArgumentException("Los campos obligatorios deben estar completos.");
        }
        //validar con regex (expresion regular) // que los nombre no contengan caracteres especiales y el email cumpla con el formato ETC
        if (!Pattern.matches(RegexUtils.NOMBRE_APELLIDO_REGEX, cliente.getNombre())) {
            throw new IllegalArgumentException("Nombre inválido.");
        }
        if (!Pattern.matches(RegexUtils.NOMBRE_APELLIDO_REGEX, cliente.getApellido())) {
            throw new IllegalArgumentException("Apellido inválido");
        }
        if (!Pattern.matches(RegexUtils.DNI_REGEX, cliente.getDni())){
            throw new IllegalArgumentException("DNI inválido");
        }
        if (!Pattern.matches(RegexUtils.EMAIL_REGEX, cliente.getEmail())){
            throw new IllegalArgumentException("Email inválido");
        }
        if (!Pattern.matches(RegexUtils.TELEFONO_REGEX, cliente.getTelefono())){
            throw new IllegalArgumentException("Telefono inválido");
        }
        if (cliente.getTelefono().matches("^0+$")) {
            throw new IllegalArgumentException("El teléfono no puede ser solo ceros.");
        }
        //capitalizar nombres y apellidos
    }

    public void capitalizacionDeCliente(Cliente cliente) {
        cliente.setNombre(capitalizarPalabras(cliente.getNombre()));
        cliente.setApellido(capitalizarPalabras(cliente.getApellido()));
    }
}
