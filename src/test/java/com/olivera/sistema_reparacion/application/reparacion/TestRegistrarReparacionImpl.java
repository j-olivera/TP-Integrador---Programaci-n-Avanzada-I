package com.olivera.sistema_reparacion.application.reparacion;

import com.olivera.sistema_reparacion.application.dto.reparacion.RegistrarReparacionCommand;
import com.olivera.sistema_reparacion.application.dto.reparacion.ReparacionResponse;
import com.olivera.sistema_reparacion.application.ports.out.ReparacionRepositoryPort;
import com.olivera.sistema_reparacion.application.usecases.reparacion.RegistrarReparacionImpl;
import com.olivera.sistema_reparacion.domain.entities.Reparacion;
import com.olivera.sistema_reparacion.domain.enums.Estado;
import com.olivera.sistema_reparacion.domain.exceptions.DatosNoValidosException;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.reparacion.ReparacionMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestRegistrarReparacionImpl {
    @Mock
     ReparacionRepositoryPort reparacionRepositoryPort;
    @Mock
     ReparacionMapper reparacionMapper;
    @InjectMocks
     RegistrarReparacionImpl registrarReparacionImpl;

    @Test
    void registrarReparacionExitosa() {
        String descripcionProblema = "La batería se descarga muy rápido y el botón de encendido falla.";
        String diagnostico = "Se requiere reemplazo de batería y reparación del circuito de encendido.";
        Estado estado = Estado.NO_INICIO;
        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFin = null;
        Double costo = 312.11;
        Long idEmpleado = 21L;
        Long idEquipo = 12L;
        RegistrarReparacionCommand command = new RegistrarReparacionCommand(descripcionProblema,diagnostico,estado,fechaInicio,fechaFin,costo,idEmpleado,idEquipo);
        Reparacion reparacion = Reparacion.reconstituir(1L,descripcionProblema,diagnostico,estado,fechaInicio,fechaFin,costo,idEmpleado,idEquipo);
        ReparacionResponse reparacionResponse = new ReparacionResponse(1L,descripcionProblema,diagnostico,estado,fechaInicio,fechaFin,costo,idEmpleado,idEquipo);
        when(reparacionRepositoryPort.save(any(Reparacion.class))).thenReturn(reparacion);
        when(reparacionMapper.toResponse(any(Reparacion.class))).thenReturn(reparacionResponse);
        when(reparacionMapper.toDomain(any(RegistrarReparacionCommand.class))).thenReturn(reparacion);
        //
        ReparacionResponse response = registrarReparacionImpl.registrarReparacion(command);
        //
        Assertions.assertEquals(reparacionResponse,response);
        Assertions.assertNotNull(response);
        verify(reparacionRepositoryPort).save(any(Reparacion.class));
        verify(reparacionMapper).toDomain(any(RegistrarReparacionCommand.class));
        verify(reparacionMapper).toResponse(any(Reparacion.class));
    }
    @Test
    void testRegistrarReparacionNoExito() {
        String descripcionProblema = "";
        String diagnostico = "Se requiere reemplazo de batería y reparación del circuito de encendido.";
        Estado estado = Estado.NO_INICIO;
        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFin = null;
        Double costo = 312.11;
        Long idEmpleado = 21L;
        Long idEquipo = 12L;
        RegistrarReparacionCommand command = new RegistrarReparacionCommand(descripcionProblema,diagnostico,estado,fechaInicio,fechaFin,costo,idEmpleado,idEquipo);
        //
        Assertions.assertThrows(DatosNoValidosException.class,()->registrarReparacionImpl.registrarReparacion(command));
        verify(reparacionRepositoryPort,never()).save(any(Reparacion.class));
    }
}
