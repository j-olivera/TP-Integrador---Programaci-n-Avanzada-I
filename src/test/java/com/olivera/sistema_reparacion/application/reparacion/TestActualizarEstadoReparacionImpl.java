package com.olivera.sistema_reparacion.application.reparacion;

import com.olivera.sistema_reparacion.application.dto.reparacion.ActualizarEstadoReparacionCommand;
import com.olivera.sistema_reparacion.application.dto.reparacion.ReparacionResponse;
import com.olivera.sistema_reparacion.application.ports.in.reparacion.ActualizarEstadoReparacion;
import com.olivera.sistema_reparacion.application.ports.out.EmpleadoRepositoryPort;
import com.olivera.sistema_reparacion.application.ports.out.ReparacionRepositoryPort;
import com.olivera.sistema_reparacion.application.usecases.reparacion.ActualizarEstadoReparacionImpl;
import com.olivera.sistema_reparacion.domain.entities.Reparacion;
import com.olivera.sistema_reparacion.domain.enums.Estado;
import com.olivera.sistema_reparacion.domain.exceptions.reparacion.ReparacionNoEncontradaException;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.empleado.EmpleadoMapper;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.reparacion.ReparacionMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class TestActualizarEstadoReparacionImpl {
    @Mock
    ReparacionRepositoryPort reparacionRepositoryPort;
    @Mock
    ReparacionMapper reparacionMapper;
    @InjectMocks
    ActualizarEstadoReparacionImpl actualizarEstadoReparacionImpl;

    @Test
    void testActualizarEstadoReparacionImplExito() {
        String descripcionProblema = "La batería se descarga muy rápido y el botón de encendido falla.";
        String diagnostico = "Se requiere reemplazo de batería y reparación del circuito de encendido.";
        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFin = null;
        Double costo = 312.11;
        Long idEmpleado = 21L;
        Long idEquipo = 12L;
        ActualizarEstadoReparacionCommand act = new ActualizarEstadoReparacionCommand(1L, Estado.NO_INICIO);
        Reparacion reparacion = Reparacion.reconstituir(1L,descripcionProblema,diagnostico,Estado.NO_INICIO,fechaInicio,fechaFin,costo,idEmpleado,idEquipo);
        ReparacionResponse reparacionResponse = new ReparacionResponse(1L,descripcionProblema,diagnostico,Estado.NO_INICIO,fechaInicio,fechaFin,costo,idEmpleado,idEquipo);
        when(reparacionRepositoryPort.findById(1L)).thenReturn(Optional.of(reparacion));
        when(reparacionRepositoryPort.save(any(Reparacion.class))).thenReturn(reparacion);
        when(reparacionMapper.toResponse(reparacion)).thenReturn(reparacionResponse);
        //
        ReparacionResponse response = actualizarEstadoReparacionImpl.actualizarEstado(1L, act);
        //
        Assertions.assertNotNull(response);
        Assertions.assertEquals(reparacionResponse, response);
        verify(reparacionRepositoryPort).save(any(Reparacion.class));
        verify(reparacionRepositoryPort).findById(anyLong());
        verify(reparacionMapper).toResponse(any(Reparacion.class));
    }
    @Test
    void testActualizarEstadoReparacionImplNoExito() {
        Long id = 2L;
        ActualizarEstadoReparacionCommand act = new ActualizarEstadoReparacionCommand(1L, Estado.NO_INICIO);
        when(reparacionRepositoryPort.findById(anyLong())).thenReturn(Optional.empty());
        //
        Assertions.assertThrows(ReparacionNoEncontradaException.class, () -> actualizarEstadoReparacionImpl.actualizarEstado(id,act));
        verify(reparacionRepositoryPort,never()).save(any(Reparacion.class));
        verify(reparacionMapper,never()).toResponse(any(Reparacion.class));
    }
}
