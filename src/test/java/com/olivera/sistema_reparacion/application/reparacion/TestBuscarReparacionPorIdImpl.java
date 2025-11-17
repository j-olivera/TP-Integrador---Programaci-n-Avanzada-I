package com.olivera.sistema_reparacion.application.reparacion;

import com.olivera.sistema_reparacion.application.dto.reparacion.ReparacionResponse;
import com.olivera.sistema_reparacion.application.ports.out.ReparacionRepositoryPort;
import com.olivera.sistema_reparacion.application.usecases.reparacion.BuscarReparacionPorIdImpl;
import com.olivera.sistema_reparacion.domain.entities.Reparacion;
import com.olivera.sistema_reparacion.domain.enums.Estado;
import com.olivera.sistema_reparacion.domain.exceptions.reparacion.ReparacionNoEncontradaException;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestBuscarReparacionPorIdImpl {
    @Mock
    ReparacionRepositoryPort reparacionRepositoryPort;
    @Mock
    ReparacionMapper reparacionMapper;
    @InjectMocks
    BuscarReparacionPorIdImpl buscarReparacionPorId;
    @Test
    public void testBuscarReparacionPorIdImplExito() {
        Long idReparacion = 1L;
        String descripcionProblema = "La batería se descarga muy rápido y el botón de encendido falla.";
        String diagnostico = "Se requiere reemplazo de batería y reparación del circuito de encendido.";
        Estado estado = Estado.NO_INICIO;
        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFin = null;
        Double costo = 312.11;
        Long idEmpleado = 21L;
        Long idEquipo = 12L;
        Reparacion reparacion = Reparacion.reconstituir(1L,descripcionProblema,diagnostico,estado,fechaInicio,fechaFin,costo,idEmpleado,idEquipo);
        ReparacionResponse reparacionResponse = new ReparacionResponse(1L,descripcionProblema,diagnostico,estado,fechaInicio,fechaFin,costo,idEmpleado,idEquipo);
        //when(reparacionRepositoryPort.save(any(Reparacion.class))).thenReturn(reparacion);
        when(reparacionRepositoryPort.findById(idReparacion)).thenReturn(Optional.of(reparacion));
        when(reparacionMapper.toResponse(any(Reparacion.class))).thenReturn(reparacionResponse);
        //
        ReparacionResponse response = buscarReparacionPorId.buscarReparacionPorId(idReparacion);
        //
        Assertions.assertEquals(reparacionResponse,response);
        Assertions.assertNotNull(response);
        //verify(reparacionRepositoryPort).save(any(Reparacion.class)); no se invoca en el usecase
        verify(reparacionMapper).toResponse(any(Reparacion.class));
        verify(reparacionRepositoryPort).findById(idReparacion);
    }

    @Test
    void testBuscarReparacionPorIdImplExitoNoEncontrado() {
        Long idReparacion = 1L;
        String descripcionProblema = "La batería se descarga muy rápido y el botón de encendido falla.";
        String diagnostico = "Se requiere reemplazo de batería y reparación del circuito de encendido.";
        Estado estado = Estado.NO_INICIO;
        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFin = null;
        Double costo = 312.11;
        Long idEmpleado = 21L;
        Long idEquipo = 12L;
        Reparacion reparacion = Reparacion.reconstituir(2L,descripcionProblema,diagnostico,estado,fechaInicio,fechaFin,costo,idEmpleado,idEquipo);
        when(reparacionRepositoryPort.findById(idReparacion)).thenReturn(Optional.empty());
        //when(reparacionMapper.toResponse(any(Reparacion.class))).thenReturn(null);
        //
        Assertions.assertThrows(ReparacionNoEncontradaException.class, ()-> buscarReparacionPorId.buscarReparacionPorId(idReparacion));
        verify(reparacionRepositoryPort,never()).save(any(Reparacion.class));

    }
}
