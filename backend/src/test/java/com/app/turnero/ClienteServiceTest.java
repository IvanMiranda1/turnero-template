package com.app.turnero;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import com.app.domain.model.Cliente;
import com.app.domain.port.ClienteRepository;

import com.app.application.usecase.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;
    
    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test // se testea unicidad de datos, retorna 0 y se procede a testear el createorupdate
    void testCreateCliente_Succes() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Pepe");
        cliente.setApellido("Alonso");
        cliente.setEmail("pepealonso@gmail.com");
        cliente.setDni("12345678");
        cliente.setTelefono("2964123456");

        //se prepara el comportamiento del mock, cuando llame al clienteService.create, los llamados al repositorio tendran este comportamiento
        when(clienteRepository.countByEmailOrDniOrTelefonoAndId(anyString(), anyString(), anyString(), anyString())).thenReturn(0L);
        when(clienteRepository.createOrUpdate(any(Cliente.class))).thenReturn(cliente);
        //si quisiera que el createorupdate retorne un error colocaria .thenThrow(new RuntimeException("Error al crear cliente"));

        Cliente resultado = clienteService.create(cliente);
        
        // Verifica que se haya llamado al método countByEmailOrDniOrTelefono del repositorio
        verify(clienteRepository, times(1)).countByEmailOrDniOrTelefonoAndId(cliente.getEmail(), cliente.getDni(), cliente.getTelefono(), null);
        // Verifica que se haya llamado al método createOrUpdate del repositorio
        verify(clienteRepository, times(1)).createOrUpdate(cliente);

        assertNotNull(resultado);
        assertEquals("Pepe", resultado.getNombre());
        assertSame(cliente, resultado);
    }

    @Test // como se testea  validacion de campo obligatorio, nunca se llega a llamar al repo
    //entonces como no hay un resultado no usamos assertNotNull, ETC, para comprobar el resutlado
    //nomas verificamos que no se haya llamado al repo con verify()
    void testCreateCliente_CampoObligatorioVacio_Nombre() {
        Cliente cliente = new Cliente();
        cliente.setNombre("");
        cliente.setApellido("Alonso");
        cliente.setEmail("pepealonso@gmail.com");
        cliente.setDni("12345678");
        cliente.setTelefono("2964123456");

        assertThrows(IllegalArgumentException.class, () -> clienteService.create(cliente)); // Debe lanzar excepción
        verify(clienteRepository, never()).createOrUpdate(any());// Verifica que no se haya llamado al método createOrUpdate del repositorio

    }
    @Test
    void testCreateCliente_CampoObligatorioVacio_Apellido() {
        Cliente cliente = new Cliente();
        cliente.setNombre("pepe");
        cliente.setApellido("");
        cliente.setEmail("pepealonso@gmail.com");
        cliente.setDni("12345678");
        cliente.setTelefono("2964123456");

        assertThrows(IllegalArgumentException.class, () -> clienteService.create(cliente)); // Debe lanzar excepción
        verify(clienteRepository, never()).createOrUpdate(any());// Verifica que no se haya llamado al método createOrUpdate del repositorio
    }
    @Test
    void testCreateCliente_CampoObligatorioVacio_Email() {
        Cliente cliente = new Cliente();
        cliente.setNombre("pepe");
        cliente.setApellido("alonso");
        cliente.setEmail("");
        cliente.setDni("12345678");
        cliente.setTelefono("2964123456");

        assertThrows(IllegalArgumentException.class, () -> clienteService.create(cliente)); // Debe lanzar excepción
        verify(clienteRepository, never()).createOrUpdate(any());// Verifica que no se haya llamado al método createOrUpdate del repositorio
    }
    @Test
    void testCreateCliente_FormatoDeEmail() {
        Cliente cliente = new Cliente();
        cliente.setNombre("pepe");
        cliente.setApellido("alonso");
        cliente.setEmail("pepealonsogmail.com");
        cliente.setDni("12345678");
        cliente.setTelefono("2964123456");

        assertThrows(IllegalArgumentException.class, () -> clienteService.create(cliente)); // Debe lanzar excepción
        verify(clienteRepository, never()).createOrUpdate(any());// Verifica que no se haya llamado al método createOrUpdate del repositorio
    }
    @Test
    void testCreateCliente_CampoObligatorioVacio_Dni() {
        Cliente cliente = new Cliente();
        cliente.setNombre("pepe");
        cliente.setApellido("alonso");
        cliente.setEmail("pepealonso@gmail.com");
        cliente.setDni("");
        cliente.setTelefono("2964123456");

        assertThrows(IllegalArgumentException.class, () -> clienteService.create(cliente)); // Debe lanzar excepción
        verify(clienteRepository, never()).createOrUpdate(any());// Verifica que no se haya llamado al método createOrUpdate del repositorio
    }
    @Test
    void testCreateCliente_CampoObligatorioVacio_Telefono() {
        Cliente cliente = new Cliente();
        cliente.setNombre("pepe");
        cliente.setApellido("alonso");
        cliente.setEmail("pepealonso@gmail.com");
        cliente.setDni("12345678");
        cliente.setTelefono("");

        assertThrows(IllegalArgumentException.class, () -> clienteService.create(cliente)); // Debe lanzar excepción
        verify(clienteRepository, never()).createOrUpdate(any());// Verifica que no se haya llamado al método createOrUpdate del repositorio
    }

    //Test de update
    @Test
    void testUpdateCliente_Success() {
        //se simula un cliente existente
        Cliente clienteExistente = new Cliente();
        clienteExistente.setId("1");
        clienteExistente.setNombre("Pepito");
        clienteExistente.setApellido("Rodriguez");
        clienteExistente.setEmail("PepitoRodriguez@gmail.com");
        clienteExistente.setDni("12345678");
        clienteExistente.setTelefono("2964123456");

        //cliente modificado
        Cliente clienteModificado = new Cliente();
        clienteModificado.setId("1");
        clienteModificado.setNombre("Juan");
        clienteModificado.setApellido("Perez");
        clienteModificado.setEmail("JuanPerez@gmail.com");
        clienteModificado.setDni("12345678");
        clienteModificado.setTelefono("2964123456");

        //se configuran mocks del update
        when(clienteRepository.findById("1")).thenReturn(Optional.of(clienteExistente)); // se simula que existe el cliente con id "1"
        when(clienteRepository.countByEmailOrDniOrTelefonoAndId("JuanPerez@gmail.com", "12345678", "2964123456", "1")).thenReturn(0L);
        when(clienteRepository.createOrUpdate(clienteModificado)).thenReturn(clienteModificado);
        

        Cliente clienteActualizado = clienteService.update(clienteModificado);

        //verificar que se llamaron los metodos del repositorio
        verify(clienteRepository, times(1)).findById("1");
        verify(clienteRepository, times(1)).countByEmailOrDniOrTelefonoAndId(
            "JuanPerez@gmail.com", "12345678", "2964123456", "1"
        );
        verify(clienteRepository, times(1)).createOrUpdate(clienteModificado);


        assertNotNull(clienteActualizado);
        assertEquals("Juan", clienteActualizado.getNombre());
        assertEquals("Perez", clienteActualizado.getApellido());
        assertEquals("JuanPerez@gmail.com", clienteActualizado.getEmail());
        assertEquals(clienteExistente.getId(), clienteActualizado.getId());
    }

    @Test
    void testUpdate_validacion_de_campo_obligatorio_Id() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Pepe");
        cliente.setApellido("Alonso");
        cliente.setEmail("PepeAlonso@gmail.com");
        cliente.setDni("12345678");
        cliente.setTelefono("2964123456");
        
        assertThrows(IllegalArgumentException.class, () -> clienteService.update(cliente));
        verify(clienteRepository, never()).createOrUpdate(any());
        verify(clienteRepository, never()).findById(anyString());
    }

    @Test
    void testUpdate_cliente_no_existente() {
        Cliente cliente = new Cliente();
        cliente.setId("1");
        cliente.setNombre("Pepe");
        cliente.setApellido("Alonso");
        cliente.setEmail("PepeAlonso@gmail.com");
        cliente.setDni("12345678");
        cliente.setTelefono("2964123456");

        //se simula que no existe el cliente con id "1"
        when(clienteRepository.findById("1")).thenReturn(null);

        //se espera que se lance una excepcion
        assertThrows(IllegalArgumentException.class, () -> clienteService.update(cliente));
        //se verifica que se llamo una vez al findbyid (que da error), y nunca se llega a llamar al createorupdate
        verify(clienteRepository, times(1)).findById("1");
        verify(clienteRepository, never()).countByEmailOrDniOrTelefonoAndId(any(), any(), any(), any());
        verify(clienteRepository, never()).createOrUpdate(any());
    }

    @Test
    void testUpdate_error_validar_unicidad() 
    {
         Cliente clienteExistente = new Cliente();
        clienteExistente.setId("1");
        clienteExistente.setNombre("Pepito");
        clienteExistente.setApellido("Rodriguez");
        clienteExistente.setEmail("pepito@gmail.com");
        clienteExistente.setDni("12345678");
        clienteExistente.setTelefono("2964123456");

        Cliente clienteModificado = new Cliente();
        clienteModificado.setId("1");
        clienteModificado.setNombre("Pepito");
        clienteModificado.setApellido("Rodriguez");
        clienteModificado.setEmail("duplicado@gmail.com"); // Cambia el email
        clienteModificado.setDni("12345678");
        clienteModificado.setTelefono("2964123456");

        //findbyid devuelve el cliente existente
        when(clienteRepository.findById("1")).thenReturn(Optional.of(clienteExistente));
        //se simula que ya existe un cliente con el email duplicado, por eso devuelve 1, quiere decir que hay un duplicado de email, dni o telefono
        when(clienteRepository.countByEmailOrDniOrTelefonoAndId(eq("duplicado@gmail.com"), eq("12345678"), eq("2964123456"), eq("1"))).thenReturn(1L);

        //se espera que se lance una excepcion
        assertThrows(IllegalArgumentException.class, () -> clienteService.update(clienteModificado));
    }

    @Test
    void testUpdate_validacion_de_campos_obligatorios_y_formato_correcto()
    {
        Cliente clienteExistente = new Cliente(); 
        clienteExistente.setId("1");
        clienteExistente.setNombre("Pepito");
        clienteExistente.setApellido("Rodriguez");
        clienteExistente.setEmail("pepito@gmail.com");
        clienteExistente.setDni("12345678");
        clienteExistente.setTelefono("2964123456");
        
        Cliente clienteModificado = new Cliente();
        clienteModificado.setId("1");
        clienteModificado.setNombre("");
        clienteModificado.setApellido("Alonso");
        clienteModificado.setEmail("formatoincorrecto");
        clienteModificado.setDni("12345678");
        clienteModificado.setTelefono("2964123456");
        
        when(clienteRepository.findById("1"))
            .thenReturn(Optional.of(clienteExistente));
        assertThrows(IllegalArgumentException.class,
            () -> clienteService.update(clienteModificado));
        //se verifica que se llamo al findbyid
        verify(clienteRepository, times(1)).findById("1");
        //se verifica que no se llamo al createorupdate
        verify(clienteRepository, never()).createOrUpdate(any());
    }

    @Test
    void testDeleteCliente_Success() {
        Cliente cliente = new Cliente();
        cliente.setId("1");
        cliente.setNombre("Pepe");
        cliente.setApellido("Alonso");
        cliente.setEmail("PepeAlonso@gmail.com");
        cliente.setDni("12345678");
        cliente.setTelefono("2964123456");

        String id = "1";

        when(clienteRepository.findById("1")).thenReturn(Optional.of(cliente));
        doNothing().when(clienteRepository).delete(id);

        clienteService.delete(id);

        //verifica que se llamo al metodo delete del repositorio una vez
        verify(clienteRepository, times(1)).delete(id);
    }

    @Test
    void testDeleteCliente_IdVacio() {
        String id = "";
        //se espera que se lance una excepcion
        assertThrows(IllegalArgumentException.class, () -> clienteService.delete(id));
        //verifica que no se llamo al metodo delete del repositorio
        verify(clienteRepository, never()).delete(anyString());
    }

    @Test
    void testDeleteCliente_IdNulo() {
        String id = null;
        //se espera que se lance una excepcion
        assertThrows(IllegalArgumentException.class, () -> clienteService.delete(id));
        //verifica que no se llamo al metodo delete del repositorio
        verify(clienteRepository, never()).delete(anyString());
    }

    @Test
    void testDeleteCliente_NoExiste() {
        String id = "1";
        //se simula que no existe el cliente con id "1"
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        //se espera que se lance una excepcion
        assertThrows(IllegalArgumentException.class, () -> clienteService.delete(id));
        //verifica que no se llamo al metodo delete del repositorio
        verify(clienteRepository, never()).delete(id);
    }

    @Test
    void testFindById_Success() {
        String id = "1";
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNombre("Pepe");
        cliente.setApellido("Alonso");
        cliente.setEmail("PepeAlonso@gmail.com");
        cliente.setDni("12345678");
        cliente.setTelefono("2964123456");
        //se simula que existe el cliente con id "1"
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));
        
        Cliente resultado = clienteService.findById(id);

        verify(clienteRepository, times(1)).findById(id);
        assertNotNull(resultado);
        assertEquals("12345678", resultado.getDni());
    }

    @Test
    void testFindById_IdVacio() {
        String id = "";
        //se espera que se lance una excepcion
        assertThrows(IllegalArgumentException.class, () -> clienteService.findById(id));
        //verifica que no se llamo al metodo findbyid del repositorio
        verify(clienteRepository, never()).findById(anyString());
    }

    @Test
    void testFindById_IdNulo() {
        String id = null;
        //se espera que se lance una excepcion
        assertThrows(IllegalArgumentException.class, () -> clienteService.findById(id));
        //verifica que no se llamo al metodo findbyid del repositorio
        verify(clienteRepository, never()).findById(anyString());
    }

    @Test
    void testFindById_NoExiste() {
        String id = "1";
        //se simula que no existe el cliente con id "1"
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        //se espera que se lance una excepcion
        assertThrows(IllegalArgumentException.class, () -> clienteService.findById(id));
        //verifica que no se llamo al metodo findbyid del repositorio
        verify(clienteRepository, times(1)).findById(id);
    }

    // Tests para findByNombre
    @Test
    void testFindByNombre_Success() {
        String nombre = "Pepe";
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        when(clienteRepository.findByNombre(nombre)).thenReturn(java.util.List.of(cliente));

        var resultado = clienteService.findByNombre(nombre);

        verify(clienteRepository, times(1)).findByNombre(nombre);
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(nombre, resultado.get(0).getNombre());
    }

    @Test
    void testFindByNombre_NombreVacio() {
        String nombre = "";
        assertThrows(IllegalArgumentException.class, () -> clienteService.findByNombre(nombre));
        verify(clienteRepository, never()).findByNombre(anyString());
    }

    @Test
    void testFindByNombre_NombreNulo() {
        String nombre = null;
        assertThrows(IllegalArgumentException.class, () -> clienteService.findByNombre(nombre));
        verify(clienteRepository, never()).findByNombre(anyString());
    }

    // Tests para findByApellido
    @Test
    void testFindByApellido_Success() {
        String apellido = "Alonso";
        Cliente cliente = new Cliente();
        cliente.setApellido(apellido);
        when(clienteRepository.findByApellido(apellido)).thenReturn(java.util.List.of(cliente));

        var resultado = clienteService.findByApellido(apellido);

        verify(clienteRepository, times(1)).findByApellido(apellido);
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(apellido, resultado.get(0).getApellido());
    }

    @Test
    void testFindByApellido_ApellidoVacio() {
        String apellido = "";
        assertThrows(IllegalArgumentException.class, () -> clienteService.findByApellido(apellido));
        verify(clienteRepository, never()).findByApellido(anyString());
    }

    @Test
    void testFindByApellido_ApellidoNulo() {
        String apellido = null;
        assertThrows(IllegalArgumentException.class, () -> clienteService.findByApellido(apellido));
        verify(clienteRepository, never()).findByApellido(anyString());
    }

    // Tests para findByEmail
    @Test
    void testFindByEmail_Success() {
        String email = "pepealonso@gmail.com";
        Cliente cliente = new Cliente();
        cliente.setEmail(email);
        when(clienteRepository.findByEmail(email)).thenReturn(java.util.List.of(cliente));

        var resultado = clienteService.findByEmail(email);

        verify(clienteRepository, times(1)).findByEmail(email);
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(email, resultado.get(0).getEmail());
    }

    @Test
    void testFindByEmail_EmailVacio() {
        String email = "";
        assertThrows(IllegalArgumentException.class, () -> clienteService.findByEmail(email));
        verify(clienteRepository, never()).findByEmail(anyString());
    }

    @Test
    void testFindByEmail_EmailInvalido() {
        String email = "pepealonsogmail.com";
        assertThrows(IllegalArgumentException.class, () -> clienteService.findByEmail(email));
        verify(clienteRepository, never()).findByEmail(anyString());
    }

    // Tests para findByTelefono
    @Test
    void testFindByTelefono_Success() {
        String telefono = "2964123456";
        Cliente cliente = new Cliente();
        cliente.setTelefono(telefono);
        when(clienteRepository.findByTelefono(telefono)).thenReturn(java.util.List.of(cliente));

        var resultado = clienteService.findByTelefono(telefono);

        verify(clienteRepository, times(1)).findByTelefono(telefono);
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(telefono, resultado.get(0).getTelefono());
    }

    @Test
    void testFindByTelefono_TelefonoVacio() {
        String telefono = "";
        assertThrows(IllegalArgumentException.class, () -> clienteService.findByTelefono(telefono));
        verify(clienteRepository, never()).findByTelefono(anyString());
    }

    @Test
    void testFindByTelefono_TelefonoInvalido() {
        String telefono = "abc123";
        assertThrows(IllegalArgumentException.class, () -> clienteService.findByTelefono(telefono));
        verify(clienteRepository, never()).findByTelefono(anyString());
    }

    @Test
    void testFindByTelefono_TelefonoSoloCeros() {
        String telefono = "0000000";
        // Simula que pasa el regex pero es solo ceros
        when(clienteRepository.findByTelefono(telefono)).thenReturn(java.util.List.of());
        assertThrows(IllegalArgumentException.class, () -> clienteService.findByTelefono(telefono));
        verify(clienteRepository, never()).findByTelefono(anyString());
    }
}

/*
// Verifica que el objeto no sea null
assertNotNull(result); // El resultado no debe ser null

// Verifica que dos valores sean iguales
assertEquals("Pepe", result.getNombre()); // El nombre debe ser "Pepe"

// Verifica que dos valores NO sean iguales
assertNotEquals("Juan", result.getNombre()); // El nombre no debe ser "Juan"

// Verifica que una condición sea verdadera
assertTrue(result.getEmail().contains("@")); // El email debe contener "@"

// Verifica que una condición sea falsa
assertFalse(result.getDni().isEmpty()); // El DNI no debe estar vacío

// Verifica que se lance una excepción al ejecutar el método
assertThrows(IllegalArgumentException.class, () -> clienteService.create(cliente)); // Debe lanzar excepción

// Verifica que dos objetos sean el mismo (misma referencia)
assertSame(cliente, result); // Ambos deben ser el mismo objeto

// Verifica que dos objetos NO sean el mismo (diferente referencia)
assertNotSame(cliente, result); // No deben ser el mismo objeto
 */