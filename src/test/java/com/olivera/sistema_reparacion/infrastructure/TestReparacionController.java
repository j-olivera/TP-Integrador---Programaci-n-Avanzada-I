package com.olivera.sistema_reparacion.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olivera.sistema_reparacion.application.dto.empleado.EmpleadoResponse;
import com.olivera.sistema_reparacion.application.dto.reparacion.RegistrarReparacionCommand;
import com.olivera.sistema_reparacion.application.dto.reparacion.ReparacionResponse;
import com.olivera.sistema_reparacion.application.ports.in.reparacion.*;
import com.olivera.sistema_reparacion.domain.enums.Estado;
import com.olivera.sistema_reparacion.domain.exceptions.DatosNoValidosException;
import com.olivera.sistema_reparacion.infrastucture.adapaters.controllers.ReparacionController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReparacionController.class)
public class TestReparacionController {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private RegistrarReparacion registrarReparacion;
    @MockitoBean
    private BuscarReparacionPorId buscarReparacionPorId;
    @MockitoBean
    private BuscarReparacionPorEmpleado buscarReparacionPorEmpleado;
    @MockitoBean
    private BuscarReparacionPorEstado buscarReparacionPorEstado;
    @MockitoBean
    private ListarTodasReparaciones  listarTodasReparaciones;
    @MockitoBean
    private ActualizarEstadoReparacion actualizarEstadoReparacion;
    @MockitoBean
    private EliminarReparacionPorId eliminarReparacionPorId;
    //post
    @Test
    void testDeberiaRegistrarReparacionYDevolverStatus201() throws Exception {
        //
        RegistrarReparacionCommand command = new RegistrarReparacionCommand(
                "La batería se descarga muy rápido y el botón de encendido falla.",
                "Se requiere reemplazo de batería y reparación del circuito de encendido",
                Estado.EN_ESPERA,
                LocalDate.now(),
                null,
                15000.0,
                1L,
                2L
        );
        ReparacionResponse responseEsperada = new ReparacionResponse(
                10L,
                "La batería se descarga muy rápido y el botón de encendido falla.",
                "Se requiere reemplazo de batería y reparación del circuito de encendido",
                Estado.EN_ESPERA,
                LocalDate.now(),
                null,
                15000.0,
                1L,
                2L
        );
        when(registrarReparacion.registrarReparacion(any())).thenReturn(responseEsperada);
        //
        mockMvc.perform(post("/api/reparaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.estado").value("EN_ESPERA"))
                .andExpect(jsonPath("$.costo").value(15000.0));
        verify(registrarReparacion, times(1)).registrarReparacion(any());
    }
    @Test
    void testNoDeberiaRegistrarReparacionPorDatosInvalidos() throws Exception {
        RegistrarReparacionCommand command = new RegistrarReparacionCommand(
                "",
                "Se requiere reemplazo de batería y reparación del circuito de encendido",
                Estado.EN_ESPERA,
                LocalDate.now(),
                null,
                15000.0,
                1L,
                2L
        );
        when(registrarReparacion.registrarReparacion(any())).thenThrow(new DatosNoValidosException("Descripcion obligatoria"));
        mockMvc.perform(post("/api/reparaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isBadRequest()); //seria el 400
        verify(registrarReparacion, times(1)).registrarReparacion(any());


    }
    //GeT
    @Test
    void testDeberiaEncontrarReparacionPorId() throws Exception {
        ReparacionResponse responseEsperada = new ReparacionResponse(
                10L,
                "La batería se descarga muy rápido y el botón de encendido falla.",
                "Se requiere reemplazo de batería y reparación del circuito de encendido",
                Estado.EN_ESPERA,
                LocalDate.now(),
                null,
                15000.0,
                1L,
                2L
        );
        when(buscarReparacionPorId.buscarReparacionPorId(any())).thenReturn(responseEsperada);
        mockMvc.perform(get("/api/reparaciones/{id}",1L))
                .andExpect(status().isOk());
        verify(buscarReparacionPorId, times(1)).buscarReparacionPorId(any());
    }
    @Test
    void testDeberíaEncontrarReparacionPorEstado() throws Exception {
        ReparacionResponse responseEsperada = new ReparacionResponse(
                10L,
                "La batería se descarga muy rápido y el botón de encendido falla.",
                "Se requiere reemplazo de batería y reparación del circuito de encendido",
                Estado.EN_ESPERA,
                LocalDate.now(),
                null,
                15000.0,
                1L,
                2L
        );
        ReparacionResponse responseEsperada2 = new ReparacionResponse(
                10L,
                "La batería se muy rápido y el botón de encendido falla.",
                "Se requiere de batería y reparación del circuito de encendido",
                Estado.EN_ESPERA,
                LocalDate.now(),
                null,
                53000.0, //la diff
                1L,
                2L
        );
        List<ReparacionResponse> reparaciones = Arrays.asList(responseEsperada, responseEsperada2);
        when(buscarReparacionPorEstado.buscarReparacionPorEstado(any())).thenReturn(reparaciones);
        mockMvc.perform(get("/api/reparaciones/estado/{estado}",Estado.EN_ESPERA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].costo").value(15000.0)) //[indice].atributo?-> valor esperado
                .andExpect(jsonPath("$[1].costo").value(53000.0));
        verify(buscarReparacionPorEstado, times(1)).buscarReparacionPorEstado(any());
    }
    @Test
    void testDeberíaListarTodasLasReparaciones() throws Exception{
        ReparacionResponse responseEsperada = new ReparacionResponse(
                10L,
                "La batería se descarga muy rápido y el botón de encendido falla.",
                "Se requiere reemplazo de batería y reparación del circuito de encendido",
                Estado.EN_ESPERA,
                LocalDate.now(),
                null,
                15000.0,
                1L,
                2L
        );
        ReparacionResponse responseEsperada2 = new ReparacionResponse(
                10L,
                "La batería se muy rápido y el botón de encendido falla.",
                "Se requiere de batería y reparación del circuito de encendido",
                Estado.EN_ESPERA,
                LocalDate.now(),
                null,
                53000.0, //la diff
                1L,
                2L
        );
        List<ReparacionResponse> reparaciones = Arrays.asList(responseEsperada, responseEsperada2);
        when(listarTodasReparaciones.listarTodasReparaciones()).thenReturn(reparaciones);
        mockMvc.perform(get("/api/reparaciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
        verify(listarTodasReparaciones, times(1)).listarTodasReparaciones();
    }
    @Test
    void testDeberíaDevolverReparacionPorEmpleado()  throws Exception{
        ReparacionResponse responseEsperada = new ReparacionResponse(
                10L,
                "La batería se descarga muy rápido y el botón de encendido falla.",
                "Se requiere reemplazo de batería y reparación del circuito de encendido",
                Estado.EN_ESPERA,
                LocalDate.now(),
                null,
                15000.0,
                1L,
                2L
        );
        Long id=1L;
        List<ReparacionResponse> reparaciones = Arrays.asList(responseEsperada);
        when(buscarReparacionPorEmpleado.findByEmpleado(id)).thenReturn(reparaciones);
        //
        mockMvc.perform(get("/api/reparaciones/empleado/{empleadoId}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].empleadoId").value(1L));
        verify(buscarReparacionPorEmpleado, times(1)).findByEmpleado(id);
    }
    @Test
    void testDeberiaEliminarReparacion() throws Exception{
        Long id=1L;
        doNothing().when(eliminarReparacionPorId).eliminarReparacionPorId(id);

        mockMvc.perform(delete("/api/reparaciones/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("Reparacion eliminada"));

        verify(eliminarReparacionPorId, times(1)).eliminarReparacionPorId(id);
    }
}
