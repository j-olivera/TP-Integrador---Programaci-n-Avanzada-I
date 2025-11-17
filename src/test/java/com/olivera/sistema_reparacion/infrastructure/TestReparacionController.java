package com.olivera.sistema_reparacion.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olivera.sistema_reparacion.application.ports.in.reparacion.*;
import com.olivera.sistema_reparacion.infrastucture.adapaters.controllers.ReparacionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

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


}
