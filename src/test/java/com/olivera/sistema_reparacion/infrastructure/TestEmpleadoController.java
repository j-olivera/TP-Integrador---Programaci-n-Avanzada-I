package com.olivera.sistema_reparacion.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olivera.sistema_reparacion.application.dto.empleado.EmpleadoResponse;
import com.olivera.sistema_reparacion.application.ports.in.empleado.*;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    void TestRegistraEmpleadoYDevuelveStatus201() throws Exception {
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
    void TestNoDebeRegistrarEmailDuplicado() throws Exception {
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
    void TestNoDebeRegistrarPorDatosInvalido() throws Exception {
        String requestBody = """
            {
                "nombre": "Juan",
                "apellido": "Olivera",
                "especialidad": "Programador",
                "email": "juancom"
            }
        """;
        when(registrarEmpleado.saveEmpleado(any())).thenThrow(new EmpleadoYaExisteException("El email juan@gmail.com ya esta registrado"));
        mockMvc.perform(post("/api/empleados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest()); //seria el 400
        verify(registrarEmpleado, times(1)).saveEmpleado(any());
    }
}
//nota CONECTAR INICIAR SPRING BOOT O EXPLOTA EL TEST