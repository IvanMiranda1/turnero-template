package com.app.turnero;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.app.estadoTurno.EstadoTurno;
import com.app.estadoTurno.EstadoTurnoDTO;
import com.app.estadoTurno.EstadoTurnoMapper;
import com.app.estadoTurno.EstadoTurnoRepository;
import com.app.estadoTurno.EstadoTurnoService;

class EstadoTurnoServiceTest {
    @Mock
    private EstadoTurnoRepository estadoTurnoRepository;

    @Mock
    private EstadoTurnoMapper estadoTurnoMapper;

    @InjectMocks
    private EstadoTurnoService estadoTurnoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test crear estado de turno exitosamente
    @Test
    void testCreateEstadoTurnoSuccess() {
        EstadoTurnoDTO dto = new EstadoTurnoDTO(null, "pendiente");
        EstadoTurno entity = new EstadoTurno(UUID.randomUUID(), "Pendiente");

        when(estadoTurnoRepository.existsByNombre("Pendiente")).thenReturn(0L);
        when(estadoTurnoMapper.toEntity(any(EstadoTurnoDTO.class))).thenReturn(entity);
        when(estadoTurnoRepository.save(any(EstadoTurno.class))).thenReturn(entity);
        when(estadoTurnoMapper.toDTO(any(EstadoTurno.class))).thenReturn(new EstadoTurnoDTO(entity.getId().toString(), "Pendiente"));

        EstadoTurnoDTO creado = estadoTurnoService.create(dto);

        assertNotNull(creado.getId());
        assertEquals("Pendiente", creado.getNombre());
        verify(estadoTurnoRepository).save(entity);
    }

    // Test crear estado con nombre duplicado lanza excepción
    @Test
    void testCreateEstadoTurnoNombreDuplicadoThrows() {
        EstadoTurnoDTO dto = new EstadoTurnoDTO(null, "pendiente");
        when(estadoTurnoRepository.existsByNombre("Pendiente")).thenReturn(1L);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            estadoTurnoService.create(dto);
        });

        assertEquals("Ya existe un estado de turno con ese nombre.", exception.getMessage());
        verify(estadoTurnoRepository, never()).save(any());
    }

    // Test update exitoso
    @Test
    void testUpdateEstadoTurnoSuccess() {
        UUID id = UUID.randomUUID();
        EstadoTurnoDTO dtoActualizado = new EstadoTurnoDTO(id.toString(), "finalizado");
        EstadoTurno existente = new EstadoTurno(id, "Pendiente");
        EstadoTurno actualizadoEntity = new EstadoTurno(id, "Finalizado");

        when(estadoTurnoRepository.findById(id)).thenReturn(Optional.of(existente));
        when(estadoTurnoRepository.unicidadByNombreConId("Finalizado", id)).thenReturn(0L);
        when(estadoTurnoMapper.toEntity(dtoActualizado)).thenReturn(actualizadoEntity);
        when(estadoTurnoRepository.save(actualizadoEntity)).thenReturn(actualizadoEntity);
        when(estadoTurnoMapper.toDTO(actualizadoEntity)).thenReturn(new EstadoTurnoDTO(id.toString(), "Finalizado"));

        EstadoTurnoDTO result = estadoTurnoService.update(dtoActualizado);

        assertEquals("Finalizado", result.getNombre());
        verify(estadoTurnoRepository).save(actualizadoEntity);
    }

    // Test update con ID inexistente lanza excepción
    @Test
    void testUpdateEstadoTurnoIdInexistenteThrows() {
        UUID id = UUID.randomUUID();
        EstadoTurnoDTO dto = new EstadoTurnoDTO(id.toString(), "pendiente");
        when(estadoTurnoRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            estadoTurnoService.update(dto);
        });

        assertEquals("No existe un estado de turno con el ID proporcionado.", exception.getMessage());
        verify(estadoTurnoRepository, never()).save(any());
    }

    // Test update con nombre duplicado lanza excepción
    @Test
    void testUpdateEstadoTurnoNombreDuplicadoThrows() {
        UUID id = UUID.randomUUID();
        EstadoTurnoDTO dtoActualizado = new EstadoTurnoDTO(id.toString(), "finalizado");
        EstadoTurno existente = new EstadoTurno(id, "Pendiente");

        when(estadoTurnoRepository.findById(id)).thenReturn(Optional.of(existente));
        when(estadoTurnoRepository.unicidadByNombreConId("Finalizado", id)).thenReturn(1L);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            estadoTurnoService.update(dtoActualizado);
        });

        assertEquals("Ya existe un estado de turno con ese nombre.", exception.getMessage());
        verify(estadoTurnoRepository, never()).save(any());
    }

    // Test findById exitoso
    @Test
    void testFindByIdSuccess() {
        UUID id = UUID.randomUUID();
        EstadoTurno existente = new EstadoTurno(id, "Pendiente");
        EstadoTurnoDTO dto = new EstadoTurnoDTO(id.toString(), "Pendiente");

        when(estadoTurnoRepository.findById(id)).thenReturn(Optional.of(existente));
        when(estadoTurnoMapper.toDTO(existente)).thenReturn(dto);

        EstadoTurnoDTO result = estadoTurnoService.findById(id);

        verify(estadoTurnoRepository, times(1)).findById(id);
        assertNotNull(result);
        assertEquals("Pendiente", result.getNombre());
    }

    // Test findById con ID inexistente lanza excepción
    @Test
    void testFindByIdNotFoundThrows() {
        UUID id = UUID.randomUUID();
        when(estadoTurnoRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            estadoTurnoService.findById(id);
        });

        assertEquals("No existe un estado de turno con el ID proporcionado.", exception.getMessage());
        verify(estadoTurnoRepository).findById(id);
    }

    // Test delete exitoso
    @Test
    void testDeleteSuccess() {
        UUID id = UUID.randomUUID();
        when(estadoTurnoRepository.findById(id)).thenReturn(Optional.of(new EstadoTurno()));
        when(estadoTurnoRepository.countByEstadoTurnoEnUso(id)).thenReturn(0L);

        estadoTurnoService.delete(id);

        verify(estadoTurnoRepository).deleteById(id);
    }

    // Test delete con ID inexistente lanza excepción
    @Test
    void testDeleteIdNotFoundThrows() {
        UUID id = UUID.randomUUID();
        when(estadoTurnoRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            estadoTurnoService.delete(id);
        });

        assertEquals("No existe un estado de turno con el ID proporcionado.", exception.getMessage());
        verify(estadoTurnoRepository, never()).deleteById(any());
    }

    // Test delete cuando el estado está en uso lanza excepción
    @Test
    void testDeleteEstadoEnUsoThrows() {
        UUID id = UUID.randomUUID();
        when(estadoTurnoRepository.findById(id)).thenReturn(Optional.of(new EstadoTurno()));
        when(estadoTurnoRepository.countByEstadoTurnoEnUso(id)).thenReturn(1L);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            estadoTurnoService.delete(id);
        });

        assertEquals("No se puede eliminar el estado de turno porque está en uso.", exception.getMessage());
        verify(estadoTurnoRepository, never()).deleteById(any());
    }
}
