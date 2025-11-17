package com.olivera.sistema_reparacion.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olivera.sistema_reparacion.application.ports.in.equipo.*;
import com.olivera.sistema_reparacion.infrastucture.adapaters.controllers.EquipoController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

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

}
