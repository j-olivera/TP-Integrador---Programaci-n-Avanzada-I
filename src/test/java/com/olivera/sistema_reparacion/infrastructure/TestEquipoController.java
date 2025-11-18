package com.olivera.sistema_reparacion.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olivera.sistema_reparacion.application.dto.equipo.EquipoResponse;
import com.olivera.sistema_reparacion.application.dto.equipo.RegistrarEquipoCommand;
import com.olivera.sistema_reparacion.application.ports.in.equipo.*;
import com.olivera.sistema_reparacion.domain.enums.ModeloEquipo;
import com.olivera.sistema_reparacion.domain.enums.TipoEquipo;
import com.olivera.sistema_reparacion.domain.exceptions.DatosNoValidosException;
import com.olivera.sistema_reparacion.domain.exceptions.empleado.EmpleadoNoEncontradoException;
import com.olivera.sistema_reparacion.domain.exceptions.equipo.EquipoNoEncontradoException;
import com.olivera.sistema_reparacion.domain.exceptions.equipo.NumerosNoValidosExceptions;
import com.olivera.sistema_reparacion.infrastucture.adapaters.controllers.EquipoController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EquipoController.class)
public class TestEquipoController {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private RegistrarEquipo registrarEquipo;
    @MockitoBean
    private BuscarEquipoPorId  buscarEquipoPorId;
    @MockitoBean
    private EliminarEquipoPorId  eliminarEquipoPorId;
    @MockitoBean
    private ListarTodosLosEquipos   listarTodosLosEquipos;
    @MockitoBean
    private BuscarEquipoPorNumeroSerie  buscarEquipoPorNumeroSerie;

    //POST
    @Test
    void testRegistrarEquipoYDevuelveStatus201() throws Exception {
        RegistrarEquipoCommand command = new RegistrarEquipoCommand(
                TipoEquipo.NOTEBOOK,
                ModeloEquipo.ASUS,
                "SN-12345",
                "Maria Gomez",
                "1234567890"
        );
        EquipoResponse responseEsperada = new EquipoResponse(
                1L,
                TipoEquipo.NOTEBOOK,
                ModeloEquipo.ASUS,
                "SN-12345",
                "Maria Gomez",
                "1234567890"
        );
        //aca uso el mapper para pbrar
        when(registrarEquipo.registrarEquipo(any(RegistrarEquipoCommand.class))).thenReturn(responseEsperada);
        //
        mockMvc.perform(post("/api/equipos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.numeroSerie").value("SN-12345"))
                .andExpect(jsonPath("$.nombreCliente").value("Maria Gomez"));
        //
        verify(registrarEquipo, times(1)).registrarEquipo(any());
    }
    @Test
    void testNoDebeRegistrarNumeroDeSerieInvalido() throws Exception{
        RegistrarEquipoCommand command = new RegistrarEquipoCommand(
                TipoEquipo.NOTEBOOK,
                ModeloEquipo.ASUS,
                "SN%!$$12345",
                "Maria Gomez",
                "1234567890"
        );
        when(registrarEquipo.registrarEquipo(any())).thenThrow(new NumerosNoValidosExceptions("El numero de serie solo puede tener letrras, numeros y guiones"));
        mockMvc.perform(post("/api/equipos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void testNoDebeRegistrarDatosInvalidos() throws Exception{
        RegistrarEquipoCommand command = new RegistrarEquipoCommand(
                TipoEquipo.NOTEBOOK,
                ModeloEquipo.ASUS,
                "SN-12345",
                "Ma", //nombro menor a 3 c
                "1234567890"
        );
        when(registrarEquipo.registrarEquipo(any())).thenThrow(new DatosNoValidosException("El nombre del cliente debe tener al menos 3 caracteres"));
        mockMvc.perform(post("/api/equipos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isBadRequest());
    }
    //get
    @Test
    void testDeberiaEncontrarElEquipoPorIdYDevolver200() throws Exception{
        EquipoResponse responseEsperada = new EquipoResponse(
                1L,
                TipoEquipo.NOTEBOOK,
                ModeloEquipo.ASUS,
                "SN-12345",
                "Maria Gomez",
                "1234567890"
        );
        when(buscarEquipoPorId.buscarEquipoPorId(1L)).thenReturn(responseEsperada);
        mockMvc.perform(get("/api/equipos/{id}", 1L))
                .andExpect(status().isOk());
    }
    @Test
    void testDeberiaEncontrarElEquipoPorNumeroSerieYDevolver200() throws Exception{
        EquipoResponse responseEsperada = new EquipoResponse(
                1L,
                TipoEquipo.NOTEBOOK,
                ModeloEquipo.ASUS,
                "SN-12345",
                "Maria Gomez",
                "1234567890"
        );
        when(buscarEquipoPorNumeroSerie.buscarEquipoPorNumeroSerie("SN-12345")).thenReturn(responseEsperada);
        mockMvc.perform(get("/api/equipos/{numeroSerie}", 1L))
                .andExpect(status().isOk());
    }
    @Test
    void testDeberiaEliminarEquipoYDevolverStatus200()  throws  Exception{
        Long idEquipo = 1L;
        doNothing().when(eliminarEquipoPorId).eliminarEquipo(idEquipo);
        mockMvc.perform(delete("/api/equipos/delete/{id}", idEquipo))
                .andExpect(status().isOk());
        verify(eliminarEquipoPorId,times(1)).eliminarEquipo(idEquipo);
    }
    //test not found
    @Test
    void testDeberiaDevolverStatus404() throws Exception {
        when(buscarEquipoPorId.buscarEquipoPorId(1L)).thenThrow(new EquipoNoEncontradoException("Equipo no encontrado"));

        mockMvc.perform(get("/api/equipos/{id}", 1L))
                .andExpect(status().isNotFound());
        verify(buscarEquipoPorId, times(1)).buscarEquipoPorId(1L);
    }
}
