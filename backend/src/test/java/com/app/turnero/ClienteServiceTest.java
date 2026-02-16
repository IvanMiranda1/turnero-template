package com.app.turnero;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

import com.app.cliente.Cliente;
import com.app.cliente.ClienteMapper;
import com.app.cliente.ClienteDTO;
import com.app.cliente.ClienteRepository;
import com.app.cliente.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository; // ahora se mockea directamente el JpaRepository

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteMapper clienteMapper;


    @BeforeEach
    void setUp() { //se ejecuta antes de cada test
        MockitoAnnotations.openMocks(this);
    }

    @Test // se testea unicidad de datos, retorna 0 y se procede a testear el createorupdate
    void testCreateCliente_Succes() {
        ClienteDTO cliente = new ClienteDTO();
        cliente.setNombre("Pepe");
        cliente.setApellido("Alonso");
        cliente.setEmail("pepealonso@gmail.com");
        cliente.setDni("12345678");
        cliente.setTelefono("2964123456");

        Cliente clienteEntity = new Cliente();
        clienteEntity.setNombre("Pepe");
        clienteEntity.setApellido("Alonso");

        //se prepara el comportamiento del mock, cuando llame al clienteService.create, los llamados al repositorio tendran este comportamiento
        when(clienteRepository.countByEmailOrDniOrTelefonoAndId(cliente.getEmail(), cliente.getDni(), cliente.getTelefono(), null)).thenReturn(0L);
        when(clienteMapper.toEntity(cliente)).thenReturn(clienteEntity);
        when(clienteRepository.save(clienteEntity)).thenReturn(clienteEntity);
        when(clienteMapper.toDTO(clienteEntity)).thenReturn(cliente);

        ClienteDTO resultado = clienteService.create(cliente);
        
        // Verifica que se haya llamado al método countByEmailOrDniOrTelefono del repositorio
        verify(clienteRepository, times(1)).countByEmailOrDniOrTelefonoAndId(cliente.getEmail(), cliente.getDni(), cliente.getTelefono(), null);
        // Verifica que se haya llamado al método createOrUpdate del repositorio
        verify(clienteRepository, times(1)).save(clienteEntity);

        assertNotNull(resultado);
        assertEquals("Pepe", resultado.getNombre()); // El nombre se capitaliza en el servicio
        assertEquals(cliente, resultado);
    }

    @Test // como se testea  validacion de campo obligatorio, nunca se llega a llamar al repo
    //entonces como no hay un resultado no usamos assertNotNull, ETC, para comprobar el resutlado
    //nomas verificamos que no se haya llamado al repo con verify()
    void testCreateCliente_CampoObligatorioVacio_Nombre() {
        ClienteDTO cliente = new ClienteDTO();
        cliente.setNombre("");
        cliente.setApellido("Alonso");
        cliente.setEmail("pepealonso@gmail.com");
        cliente.setDni("12345678");
        cliente.setTelefono("2964123456");

        // La validación de campos vacíos ahora se hace en el DTO con @NotBlank,
        // por lo que el servicio no debería recibir un DTO con nombre vacío si la validación del controller funciona.
        // Este test ahora prueba la capitalización.
        clienteService.capitalizacionDeCliente(cliente);
        assertEquals("", cliente.getNombre());
        verify(clienteRepository, never()).save(any());
    }

    //Test de update
    @Test
    void testUpdateCliente_Success() {
        UUID clienteId = UUID.randomUUID();
        //se simula un cliente existente
        Cliente clienteExistente = new Cliente(clienteId, "Pepito", "Rodriguez", "pepito@gmail.com", "2964123456", "12345678", null);

        //cliente modificado
        ClienteDTO clienteModificado = new ClienteDTO();
        clienteModificado.setId(clienteId.toString());
        clienteModificado.setNombre("Juan");
        clienteModificado.setApellido("Perez");
        clienteModificado.setEmail("JuanPerez@gmail.com");
        clienteModificado.setDni("12345678");
        clienteModificado.setTelefono("2964123456");

        Cliente clienteModificadoEntity = new Cliente(clienteId, "Juan", "Perez", "JuanPerez@gmail.com", "2964123456", "12345678", null);

        //se configuran mocks del update
        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(clienteExistente));
        when(clienteRepository.countByEmailOrDniOrTelefonoAndId(clienteModificado.getEmail(), clienteModificado.getDni(), clienteModificado.getTelefono(), clienteId)).thenReturn(0L);
        when(clienteMapper.toEntity(clienteModificado)).thenReturn(clienteModificadoEntity);
        when(clienteRepository.save(clienteModificadoEntity)).thenReturn(clienteModificadoEntity);
        when(clienteMapper.toDTO(clienteModificadoEntity)).thenReturn(clienteModificado);

        ClienteDTO clienteActualizado = clienteService.update(clienteModificado);

        //verificar que se llamaron los metodos del repositorio
        verify(clienteRepository, times(1)).findById(clienteId);
        verify(clienteRepository, times(1)).countByEmailOrDniOrTelefonoAndId(
            clienteModificado.getEmail(), clienteModificado.getDni(), clienteModificado.getTelefono(), clienteId
        );
        verify(clienteRepository, times(1)).save(clienteModificadoEntity);


        assertNotNull(clienteActualizado);
        assertEquals("Juan", clienteActualizado.getNombre());
        assertEquals("Perez", clienteActualizado.getApellido());
        assertEquals("JuanPerez@gmail.com", clienteActualizado.getEmail());
        assertEquals(clienteExistente.getId().toString(), clienteActualizado.getId());
    }

    @Test
    void testUpdate_validacion_de_campo_obligatorio_Id() {
        ClienteDTO cliente = new ClienteDTO();
        cliente.setNombre("Pepe");
        cliente.setApellido("Alonso");
        cliente.setEmail("PepeAlonso@gmail.com");
        cliente.setDni("12345678");
        cliente.setTelefono("2964123456");
        
        // La validación de ID nulo para update se hace en el DTO con @NotBlank.
        // El servicio espera un ID, si es inválido lanzará una excepción al hacer UUID.fromString()
        assertThrows(NullPointerException.class, () -> clienteService.update(cliente));
        verify(clienteRepository, never()).save(any());
        verify(clienteRepository, never()).findById(any(UUID.class));
    }

    @Test
    void testUpdate_cliente_no_existente() {
        UUID clienteId = UUID.randomUUID();
        ClienteDTO cliente = new ClienteDTO();
        cliente.setId(clienteId.toString());
        cliente.setNombre("Pepe");
        cliente.setApellido("Alonso");
        cliente.setEmail("PepeAlonso@gmail.com");
        cliente.setDni("12345678");
        cliente.setTelefono("2964123456");

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.empty());

        //se espera que se lance una excepcion
        assertThrows(IllegalArgumentException.class, () -> clienteService.update(cliente));
        //se verifica que se llamo una vez al findbyid (que da error), y nunca se llega a llamar al createorupdate
        verify(clienteRepository, times(1)).findById(clienteId);
        verify(clienteRepository, never()).countByEmailOrDniOrTelefonoAndId(any(), any(), any(), any());
    verify(clienteRepository, never()).save(any());
    }

    @Test
    void testUpdate_error_validar_unicidad() 
    {
        UUID clienteId = UUID.randomUUID();
        Cliente clienteExistente = new Cliente();
        clienteExistente.setId(clienteId);
        clienteExistente.setNombre("Pepito");
        clienteExistente.setApellido("Rodriguez");
        clienteExistente.setEmail("pepito@gmail.com");
        clienteExistente.setDni("12345678");
        clienteExistente.setTelefono("2964123456");

        ClienteDTO clienteModificado = new ClienteDTO();
        clienteModificado.setId(clienteId.toString());
        clienteModificado.setNombre("Pepito");
        clienteModificado.setApellido("Rodriguez");
        clienteModificado.setEmail("duplicado@gmail.com"); // Cambia el email
        clienteModificado.setDni("12345678");
        clienteModificado.setTelefono("2964123456");

        //findbyid devuelve el cliente existente
        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(clienteExistente));
        //se simula que ya existe un cliente con el email duplicado, por eso devuelve 1, quiere decir que hay un duplicado de email, dni o telefono
        when(clienteRepository.countByEmailOrDniOrTelefonoAndId(clienteModificado.getEmail(), clienteModificado.getDni(), clienteModificado.getTelefono(), clienteId)).thenReturn(1L);

        //se espera que se lance una excepcion
        assertThrows(IllegalArgumentException.class, () -> clienteService.update(clienteModificado));
    }

    @Test
    void testDeleteCliente_Success() {
        UUID id = UUID.randomUUID();

        when(clienteRepository.existsById(id)).thenReturn(true);
        when(clienteRepository.turnosPendientes(id)).thenReturn(0L);
        doNothing().when(clienteRepository).deleteById(id);

        clienteService.delete(id);

        //verifica que se llamo al metodo delete del repositorio una vez
        verify(clienteRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteCliente_ConTurnosPendientes() {
        UUID id = UUID.randomUUID();

        when(clienteRepository.existsById(id)).thenReturn(true);
        when(clienteRepository.turnosPendientes(id)).thenReturn(1L);

        assertThrows(IllegalArgumentException.class, () -> clienteService.delete(id));

        verify(clienteRepository, never()).deleteById(id);
    }

    @Test
    void testDeleteCliente_NoExiste() {
        UUID id = UUID.randomUUID();
        when(clienteRepository.existsById(id)).thenReturn(false);

        //se espera que se lance una excepcion
        assertThrows(IllegalArgumentException.class, () -> clienteService.delete(id));
        //verifica que no se llamo al metodo delete del repositorio
        verify(clienteRepository, never()).deleteById(id);
    }

    @Test
    void testFindById_Success() {
        UUID id = UUID.randomUUID();
        Cliente clienteEntity = new Cliente(id, "Pepe", "Alonso", "PepeAlonso@gmail.com", "2964123456", "12345678", null);
        ClienteDTO clienteDTO = new ClienteDTO(id.toString(), "Pepe", "Alonso", "PepeAlonso@gmail.com", "2964123456", "12345678");

        when(clienteRepository.findById(id)).thenReturn(Optional.of(clienteEntity));
        when(clienteMapper.toDTO(clienteEntity)).thenReturn(clienteDTO);
        
        ClienteDTO resultado = clienteService.findById(id);

        verify(clienteRepository, times(1)).findById(id);
        assertNotNull(resultado);
        assertEquals("12345678", resultado.getDni());
    }


    @Test
    void testFindById_NoExiste() {
        UUID id = UUID.randomUUID();
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        //se espera que se lance una excepcion
        assertThrows(IllegalArgumentException.class, () -> clienteService.findById(id));
        //verifica que no se llamo al metodo findbyid del repositorio
        verify(clienteRepository, times(1)).findById(id);
        verify(clienteMapper, never()).toDTO(any());
    }

    // Tests para findByNombre
    @Test
    void testFindByNombre_Success() {
        String nombre = "Pepe";
        UUID id = UUID.randomUUID();
        Cliente clienteEntity = new Cliente(id, "Pepe", "Alonso", "pepealonso@gmail.com", "2964123456", "12345678", null);
        ClienteDTO clienteDTO = new ClienteDTO(id.toString(), "Pepe", "Alonso", "pepealonso@gmail.com", "2964123456", "12345678");

        when(clienteRepository.findByNombre(nombre)).thenReturn(List.of(clienteEntity));
        when(clienteMapper.toDTO(clienteEntity)).thenReturn(clienteDTO);

        List<ClienteDTO> resultado = clienteService.findByNombre(nombre);

        verify(clienteRepository, times(1)).findByNombre(nombre);
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(nombre, resultado.get(0).getNombre());
    }

    @Test
    void testFindByNombre_NombreVacio() {
        String nombre = "";
        assertThrows(IllegalArgumentException.class, () -> clienteService.findByNombre(nombre));
        verify(clienteRepository, never()).findByNombre(any());
    }

    @Test
    void testFindByNombre_NombreNulo() {
        String nombre = null;
        assertThrows(IllegalArgumentException.class, () -> clienteService.findByNombre(nombre));
        verify(clienteRepository, never()).findByNombre(any());
    }

    // Tests para findByApellido
    @Test
    void testFindByApellido_Success() {
        String apellido = "Alonso";
        Cliente cliente = new Cliente();
        UUID id = UUID.randomUUID();
        cliente.setApellido(apellido);
        Cliente clienteEntity = new Cliente(id, "Pepe", "Alonso", "pepealonso@gmail.com", "2964123456", "12345678", null);
        ClienteDTO clienteDTO = new ClienteDTO(id.toString(), "Pepe", "Alonso", "pepealonso@gmail.com", "2964123456", "12345678");
        when(clienteRepository.findByApellido(apellido)).thenReturn(List.of(clienteEntity));
        when(clienteMapper.toDTO(clienteEntity)).thenReturn(clienteDTO);
        List<ClienteDTO> resultado = clienteService.findByApellido(apellido);

        verify(clienteRepository, times(1)).findByApellido(apellido);
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(apellido, resultado.get(0).getApellido());
    }

    @Test
    void testFindByApellido_ApellidoVacio() {
        String apellido = "";
        assertThrows(IllegalArgumentException.class, () -> clienteService.findByApellido(apellido));
        verify(clienteRepository, never()).findByApellido(any());
    }

    @Test
    void testFindByApellido_ApellidoNulo() {
        String apellido = null;
        assertThrows(IllegalArgumentException.class, () -> clienteService.findByApellido(apellido));
        verify(clienteRepository, never()).findByApellido(any());
    }

    // Tests para findByEmail
    @Test
    void testFindByEmail_Success() {
        String email = "pepealonso@gmail.com";
        UUID id = UUID.randomUUID();
        Cliente clienteEntity = new Cliente(id, "Pepe", "Alonso", email, "2964123456", "12345678", null);
        ClienteDTO clienteDTO = new ClienteDTO(id.toString(), "Pepe", "Alonso", email, "2964123456", "12345678");

        when(clienteRepository.findByEmail(email)).thenReturn(List.of(clienteEntity));
        when(clienteMapper.toDTO(clienteEntity)).thenReturn(clienteDTO);
        List<ClienteDTO> resultado = clienteService.findByEmail(email);

        verify(clienteRepository, times(1)).findByEmail(email);
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(email, resultado.get(0).getEmail());
    }

    @Test
    void testFindByEmail_EmailVacio() {
        String email = "";
        assertThrows(IllegalArgumentException.class, () -> clienteService.findByEmail(email));
        verify(clienteRepository, never()).findByEmail(any());
    }

    // Tests para findByTelefono
    @Test
    void testFindByTelefono_Success() {
        String telefono = "2964123456";
        UUID id = UUID.randomUUID();
        Cliente clienteEntity = new Cliente(id, "Pepe", "Alonso", "pepe@a.com", telefono, "12345678", null);
        ClienteDTO clienteDTO = new ClienteDTO(id.toString(), "Pepe", "Alonso", "pepe@a.com", telefono, "12345678");

        when(clienteRepository.findByTelefono(telefono)).thenReturn(List.of(clienteEntity));
        when(clienteMapper.toDTO(clienteEntity)).thenReturn(clienteDTO);
        List<ClienteDTO> resultado = clienteService.findByTelefono(telefono);

        verify(clienteRepository, times(1)).findByTelefono(telefono);
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(telefono, resultado.get(0).getTelefono());
    }
    

    @Test
    void testFindByTelefono_TelefonoSoloCeros() {
        String telefono = "0000000";
        // La validación de solo ceros se hace en el DTO con @NoSoloCeros
        // El servicio recibe la validación.
        assertThrows(IllegalArgumentException.class, () -> clienteService.findByTelefono(telefono));
        verify(clienteRepository, never()).findByTelefono(any());
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