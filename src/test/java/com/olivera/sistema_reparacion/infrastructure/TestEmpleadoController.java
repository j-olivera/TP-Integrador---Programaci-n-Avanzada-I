package com.olivera.sistema_reparacion.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olivera.sistema_reparacion.application.dto.empleado.EmpleadoResponse;
import com.olivera.sistema_reparacion.application.ports.in.empleado.*;
import com.olivera.sistema_reparacion.domain.exceptions.DatosNoValidosException;
import com.olivera.sistema_reparacion.domain.exceptions.empleado.EmpleadoNoEncontradoException;
import com.olivera.sistema_reparacion.domain.exceptions.empleado.EmpleadoYaExisteException;
import com.olivera.sistema_reparacion.infrastucture.adapaters.controllers.EmpleadoController;
import com.olivera.sistema_reparacion.infrastucture.config.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; //fallo de importaciones, importaba lo que quería para los test GET
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmpleadoController.class)
public class TestEmpleadoController {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private RegistrarEmpleado registrarEmpleado;
    @MockitoBean
    private BuscarEmpleadoPorId buscarEmpleadoPorId;
    @MockitoBean
    private ActualizarInformacionEmpleado actualizarInformacionEmpleado;
    @MockitoBean
    private EliminarEmpleado eliminarEmpleado;
    @MockitoBean
    private ListarTodosLosEmpleados listarTodosLosEmpleados;
    @MockitoBean
    private VerificarSiExisteEmpleadoPorId verificarSiExisteEmpleadoPorId;
    @MockitoBean
    private VerReparacionesAsignadas verReparacionesAsignadas;

    //estan mockeados todos pero no testeados, solo los fundamentales..

    //Test Post
    @Test
    void testRegistraEmpleadoYDevuelveStatus201() throws Exception {
        String requestBody = """
            {
                "nombre": "Juan",
                "apellido": "Olivera",
                "especialidad": "Programador",
                "email": "juan@gmail.com"
            }
        """;
        EmpleadoResponse response = new EmpleadoResponse(
                1L, "Juan", "Olivera", "Programador", "juan@gmail.com"
        );
        when(registrarEmpleado.saveEmpleado(any())).thenReturn(response);
        //
        mockMvc.perform(post("/api/empleados").
                contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellido").value("Olivera"))
                .andExpect(jsonPath("$.especialidad").value("Programador"))
                .andExpect(jsonPath("$.email").value("juan@gmail.com"));
        verify(registrarEmpleado, times(1)).saveEmpleado(any());
    }
    @Test
    void testNoDebeRegistrarEmailDuplicado() throws Exception {
        String requestBody = """
            {
                "nombre": "Juan",
                "apellido": "Olivera",
                "especialidad": "Programador",
                "email": "juan@gmail.com"
            }
        """;
        when(registrarEmpleado.saveEmpleado(any())).thenThrow(new EmpleadoYaExisteException("El email juan@gmail.com ya esta registrado"));
        mockMvc.perform(post("/api/empleados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isInternalServerError()); //seria el 500
        verify(registrarEmpleado, times(1)).saveEmpleado(any());
    }
    @Test
    void testNoDebeRegistrarPorDatosInvalido() throws Exception {
        String requestBody = """
            {
                "nombre": "Juan",
                "apellido": "Olivera",
                "especialidad": "Programador",
                "email": "juancom" 
            }
        """; //el email sería invalido
        when(registrarEmpleado.saveEmpleado(any())).thenThrow(new DatosNoValidosException("Datos no validos"));
        mockMvc.perform(post("/api/empleados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest()); //seria el 400
        verify(registrarEmpleado, times(1)).saveEmpleado(any());
    }
    //GET
    @Test
    void testDeberiaListarTodosLosEmpleadosYDevolverStatus200() throws Exception {
        List<EmpleadoResponse> empleados = Arrays.asList(
                new EmpleadoResponse(1L, "Juan", "Olivera", "Programador", "juan@gmail.com"),
                new EmpleadoResponse(2L, "María", "López", "Diseñadora", "maria@gmail.com"),
                new EmpleadoResponse(3L, "Pedro", "García", "Tester", "pedro@gmail.com")
        );
        when(listarTodosLosEmpleados.listarTodosLosEmpleados()).thenReturn(empleados);

        mockMvc.perform(get("/api/empleados"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].nombre").value("Juan")) //[indice].atributo?-> valor esperado
                .andExpect(jsonPath("$[1].nombre").value("María"))
                .andExpect(jsonPath("$[2].nombre").value("Pedro"));

        verify(listarTodosLosEmpleados, times(1)).listarTodosLosEmpleados();
    }
    @Test
    void testDeberiaEncontrarAlEmpleadoPorIdYDevolver200() throws Exception {
        EmpleadoResponse response= new  EmpleadoResponse(1L, "Juan", "Olivera", "Programador", "juan@gmail.com");
        when(buscarEmpleadoPorId.buscarEmpleadoPorId(1L)).thenReturn(response);

        mockMvc.perform(get("/api/empleados/{id}", 1L))
                .andExpect(status().isOk());
    }
    //delete
    @Test
    void testDeberiaEliminarEmpleadoYDevolverStatus200() throws Exception {
        Long empleadoId = 1L;
        doNothing().when(eliminarEmpleado).eliminarEmpleado(empleadoId);

        mockMvc.perform(delete("/api/empleados/{id}", empleadoId))
                .andExpect(status().isOk()); //200 pq asi lo puse yo

        verify(eliminarEmpleado, times(1)).eliminarEmpleado(empleadoId);
    }
    //put
    @Test
    void testDeberiaActualizarEmpleadoYDevolverStatus200() throws Exception {

        Long empleadoId = 1L;
        String requestBody = """
            {
                "nombre": "Juan Carlos",
                "apellido": "Olivera",
                "especialidad": "Senior Developer",
                "email": "juancarlos@gmail.com"
            }
        """;

        EmpleadoResponse response = new EmpleadoResponse(
                1L, "Juan Carlos", "Olivera", "Senior Developer", "juancarlos@gmail.com"
        );

        when(actualizarInformacionEmpleado.actualizarEmpleado(any())).thenReturn(response);

        mockMvc.perform(put("/api/empleados/{id}", empleadoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Juan Carlos"))
                .andExpect(jsonPath("$.especialidad").value("Senior Developer"))
                .andExpect(jsonPath("$.email").value("juancarlos@gmail.com"));

        verify(actualizarInformacionEmpleado, times(1)).actualizarEmpleado(any());
    }
}
//nota CONECTAR INICIAR SPRING BOOT O EXPLOTA EL TEST
//NOTA 2: PONER BIEN LAS EXCEPCIONES
//test put y delete hechos con ia