package com.app.application.usecase;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.app.domain.model.EstadoTurno;
import com.app.domain.port.EstadoTurnoRepository;
import com.app.domain.util.RegexUtils;

@Service
public class EstadoTurnoService {
    private final EstadoTurnoRepository estadoTurnoRepository;

    public EstadoTurnoService(EstadoTurnoRepository estadoTurnoRepository) {
        this.estadoTurnoRepository = estadoTurnoRepository;
    }

    //funciones crud
    public EstadoTurno create(EstadoTurno estadoTurno) {
        if ((estadoTurno.getId() != null && !estadoTurno.getId().trim().isEmpty()) ||
            estadoTurno.getNombre() == null || estadoTurno.getNombre().trim().isEmpty()
        ) {
            throw new IllegalArgumentException("No puede tener ID al crear, y el nombre es obligatorio.");
        }
        estadoTurno.setNombre(capitalizar(estadoTurno.getNombre()));
        if (estadoTurnoRepository.existByNombre(estadoTurno.getNombre()) > 0) {
            throw new IllegalArgumentException("Ya existe un estado de turno con ese nombre.");
        }
        validarFormatoNombre(estadoTurno.getNombre());
        return estadoTurnoRepository.createOrUpdate(estadoTurno);
    }

    public EstadoTurno update(EstadoTurno estadoTurno) {
        if (estadoTurno.getId() == null || estadoTurno.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del estado de turno es obligatorio para actualizar.");
        }
        EstadoTurno existente = estadoTurnoRepository.findById(estadoTurno.getId())
            .orElseThrow( () -> new IllegalArgumentException("No existe un estado de turno con el ID proporcionado."));
        if (existente == null) {
            throw new IllegalArgumentException("No existe un estado de turno con el ID proporcionado.");
        }
        estadoTurno.setNombre(capitalizar(estadoTurno.getNombre()));
        // Solo validar unicidad si cambió el nombre
        if (!estadoTurno.getNombre().equals(existente.getNombre()) &&
            estadoTurnoRepository.existByNombre(estadoTurno.getNombre()) > 0) {
            throw new IllegalArgumentException("Ya existe un estado de turno con ese nombre.");
        }
        validarFormatoNombre(estadoTurno.getNombre());
        return estadoTurnoRepository.createOrUpdate(estadoTurno);
    }

    public EstadoTurno findById(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del estado de turno es obligatorio para buscar.");
        }
        return estadoTurnoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("No existe un estado de turno con el ID proporcionado."));
    }

    public void delete(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del estado de turno es obligatorio para eliminar.");
        }
        if (!estadoTurnoRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("No existe un estado de turno con el ID proporcionado.");
        }
        // Verificar si el estado de turno está en uso por algún turno
        if (estadoTurnoRepository.existUsoDelEstado(id) > 0) {
            throw new IllegalArgumentException("No se puede eliminar el estado de turno porque está en uso.");
        }
        // Si pasa las validaciones, se procede a eliminar
        estadoTurnoRepository.delete(id);
    }


    //metodo aux
     public static String capitalizar(String texto) {
        if (texto == null || texto.isEmpty()) return texto;
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }

    public static void validarFormatoNombre(String nombre) {
        if (!Pattern.matches(RegexUtils.NOMBRE_APELLIDO_REGEX, nombre)) {
            throw new IllegalArgumentException("El nombre debe contener solo letras, espacios y guiones, y no puede estar vacío.");
        }
    }
}
