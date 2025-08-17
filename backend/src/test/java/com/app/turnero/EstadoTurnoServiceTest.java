package com.app.turnero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.app.application.usecase.EstadoTurnoService;
import com.app.domain.port.EstadoTurnoRepository;

import com.app.domain.model.EstadoTurno;

public class EstadoTurnoServiceTest {
    @Mock
    private EstadoTurnoRepository estadoTurnoRepository;

    @InjectMocks
    private EstadoTurnoService estadoTurnoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    // Test crear estado de turno exitosamente
    @Test
    void testCreateEstadoTurnoSuccess() {
        EstadoTurno estado = new EstadoTurno(null, "pendiente");
        when(estadoTurnoRepository.existByNombre("Pendiente")).thenReturn(0L);
        when(estadoTurnoRepository.createOrUpdate(any())).thenReturn(estado);
        EstadoTurno creado = estadoTurnoService.create(estado);
        assertEquals("Pendiente", creado.getNombre());
    }

    // Test crear estado con ID existente lanza excepción
    @Test
    void testCreateEstadoTurnoWithIdThrows() {
        EstadoTurno estado = new EstadoTurno("123", "pendiente");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            estadoTurnoService.create(estado);
        });
        assertTrue(ex.getMessage().contains("No puede tener ID al crear"));
    }

    // Test crear estado con nombre vacío lanza excepción
    @Test
    void testCreateEstadoTurnoWithEmptyNameThrows() {
        EstadoTurno estado = new EstadoTurno(null, " ");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            estadoTurnoService.create(estado);
        });
        assertTrue(ex.getMessage().contains("No puede tener ID al crear"));
    }

    // Test crear estado con nombre duplicado lanza excepción
    @Test
    void testCreateEstadoTurnoNombreDuplicadoThrows() {
        EstadoTurno estado = new EstadoTurno(null, "pendiente");
        when(estadoTurnoRepository.existByNombre("Pendiente")).thenReturn(1L);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            estadoTurnoService.create(estado);
        });
        assertTrue(ex.getMessage().contains("Ya existe un estado de turno"));
    }

    // Test update exitoso
    @Test
    void testUpdateEstadoTurnoSuccess() {
        EstadoTurno existente = new EstadoTurno("1", "Pendiente");
        EstadoTurno actualizado = new EstadoTurno("1", "finalizado");
        when(estadoTurnoRepository.findById("1")).thenReturn(Optional.of(existente));
        when(estadoTurnoRepository.existByNombre("Finalizado")).thenReturn(0L);
        when(estadoTurnoRepository.createOrUpdate(any())).thenAnswer(i -> i.getArgument(0));
        EstadoTurno result = estadoTurnoService.update(actualizado);
        assertEquals("Finalizado", result.getNombre());
    }

    // Test update sin ID lanza excepción
    @Test
    void testUpdateEstadoTurnoSinIdThrows() {
        EstadoTurno estado = new EstadoTurno(null, "pendiente");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            estadoTurnoService.update(estado);
        });
        assertTrue(ex.getMessage().contains("ID del estado de turno es obligatorio"));
    }

    // Test update con ID inexistente lanza excepción
    @Test
    void testUpdateEstadoTurnoIdInexistenteThrows() {
        EstadoTurno estado = new EstadoTurno("99", "pendiente");
        when(estadoTurnoRepository.findById("99")).thenReturn(Optional.empty());
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            estadoTurnoService.update(estado);
        });
        assertTrue(ex.getMessage().contains("No existe un estado de turno"));
    }

    // Test update con nombre duplicado lanza excepción
    @Test
    void testUpdateEstadoTurnoNombreDuplicadoThrows() {
        EstadoTurno existente = new EstadoTurno("1", "Pendiente");
        EstadoTurno actualizado = new EstadoTurno("1", "finalizado");
        when(estadoTurnoRepository.findById("1")).thenReturn(Optional.of(existente));
        when(estadoTurnoRepository.existByNombre("Finalizado")).thenReturn(1L);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            estadoTurnoService.update(actualizado);
        });
        assertTrue(ex.getMessage().contains("Ya existe un estado de turno"));
        verify(estadoTurnoRepository, never()).createOrUpdate(any());
    }

    // Test findById exitoso
    @Test
    void testFindByIdSuccess() {
        EstadoTurno existente = new EstadoTurno("1", "Pendiente");
        String id = "1";

        when(estadoTurnoRepository.findById("1")).thenReturn(Optional.of(existente));
        EstadoTurno result = estadoTurnoService.findById(id);

        verify(estadoTurnoRepository, times(1)).findById(id);
        assertNotNull(result);
        assertEquals("Pendiente", result.getNombre());
    }

    // Test findById con ID vacío lanza excepción
    @Test
    void testFindByIdEmptyThrows() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            estadoTurnoService.findById(" ");
        });
        assertTrue(ex.getMessage().contains("ID del estado de turno es obligatorio"));
        verify(estadoTurnoRepository, never()).findById(" ");
    }

    // Test findById con ID inexistente lanza excepción
    @Test
    void testFindByIdNotFoundThrows() {
        when(estadoTurnoRepository.findById("99")).thenReturn(Optional.empty());
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            estadoTurnoService.findById("99");
        });
        assertTrue(ex.getMessage().contains("No existe un estado de turno"));
        verify(estadoTurnoRepository).findById("99");
    }

    // Test delete exitoso
    @Test
    void testDeleteSuccess() {
        EstadoTurno existente = new EstadoTurno("1", "Pendiente");
        when(estadoTurnoRepository.findById("1")).thenReturn(Optional.of(existente));
        when(estadoTurnoRepository.existUsoDelEstado("1")).thenReturn(0L);
        estadoTurnoService.delete("1");
        verify(estadoTurnoRepository).delete("1");
    }

    // Test delete con ID vacío lanza excepción
    @Test
    void testDeleteEmptyIdThrows() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            estadoTurnoService.delete(" ");
        });
        assertTrue(ex.getMessage().contains("ID del estado de turno es obligatorio"));
        verify(estadoTurnoRepository, never()).delete(" ");
    }

    // Test delete con ID inexistente lanza excepción
    @Test
    void testDeleteIdNotFoundThrows() {
        when(estadoTurnoRepository.findById("99")).thenReturn(Optional.empty());
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            estadoTurnoService.delete("99");
        });
        assertTrue(ex.getMessage().contains("No existe un estado de turno"));
        verify(estadoTurnoRepository, never()).delete("99");
    }

    // Test delete cuando el estado está en uso lanza excepción
    @Test
    void testDeleteEstadoEnUsoThrows() {
        EstadoTurno existente = new EstadoTurno("1", "Pendiente");
        when(estadoTurnoRepository.findById("1")).thenReturn(Optional.of(existente));
        when(estadoTurnoRepository.existUsoDelEstado("1")).thenReturn(1L);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            estadoTurnoService.delete("1");
        });
        assertTrue(ex.getMessage().contains("No se puede eliminar el estado de turno porque está en uso."));

        verify(estadoTurnoRepository, never()).delete("1");
    }
}
