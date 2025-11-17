package com.olivera.sistema_reparacion.application.reparacion;

import com.olivera.sistema_reparacion.application.dto.reparacion.ReparacionResponse;
import com.olivera.sistema_reparacion.application.ports.out.ReparacionRepositoryPort;
import com.olivera.sistema_reparacion.application.usecases.reparacion.BuscarReparacionPorEmpleadoImpl;
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
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarReparacionPorEmpleadoImplTest {

    @Mock
    private ReparacionRepositoryPort reparacionRepositoryPort;

    @Mock
    private ReparacionMapper reparacionMapper;

    @InjectMocks
    private BuscarReparacionPorEmpleadoImpl buscarReparacionPorEmpleado;

    @Test
    void buscarReparacionPorEmpleadoExito() {
        Long idReparacion = 1L;

        Reparacion reparacion1 = Reparacion.reconstituir(
                1L,
                "La batería se descarga muy rápido y el botón de encendido falla",
                "Se requiere reemplazo de batería y reparación del circuito de encendido.",
                Estado.EN_ESPERA,
                LocalDate.now(),
                null,
                null,
                1L, // empleadoId
                1L  // equipoId
        );

        Reparacion reparacion2 = Reparacion.reconstituir(
                2L,
                "La batería se descarga muy rápido y el botón de encendido falla",
                "Se requiere reemplazo de batería y reparación del circuito de encendido.",
                Estado.EN_ESPERA,
                LocalDate.now(),
                null,
                null,
                1L,
                2L
        );

        ReparacionResponse response1 = new ReparacionResponse(
                1L,
                "La batería se descarga muy rápido y el botón de encendido falla",
                "Se requiere reemplazo de batería y reparación del circuito de encendido.",
                Estado.EN_ESPERA,
                LocalDate.now(),
                null,
                null,
                1L,
                1L
        );

        ReparacionResponse response2 = new ReparacionResponse(
                2L,
                "La batería se descarga muy rápido y el botón de encendido falla",
                "Se requiere reemplazo de batería y reparación del circuito de encendido.",
                Estado.EN_ESPERA,
                LocalDate.now(),
                null,
                null,
                1L,
                2L
        );

        List<Reparacion> reparaciones2 = Arrays.asList(reparacion1, reparacion2);

        when(reparacionRepositoryPort.findByEmpleadoId(idReparacion)).thenReturn(reparaciones2);
        when(reparacionMapper.toResponse(reparacion1)).thenReturn(response1);
        when(reparacionMapper.toResponse(reparacion2)).thenReturn(response2);
        //
        List<ReparacionResponse> reparacionResponses = buscarReparacionPorEmpleado.findByEmpleado(idReparacion);
        //
        Assertions.assertNotNull(reparacionResponses);
        Assertions.assertEquals(2, reparacionResponses.size());
        verify(reparacionRepositoryPort).findByEmpleadoId(idReparacion);
        verify(reparacionMapper,times(2)).toResponse(any(Reparacion.class));
    }
    @Test
    void testNoSeEncontraronReparacionsPorEmpleado(){
        Long idReparacion = 111L;
        when(reparacionRepositoryPort.findByEmpleadoId(idReparacion)).thenReturn(List.of());
        //
        Assertions.assertThrows(ReparacionNoEncontradaException.class, ()-> buscarReparacionPorEmpleado.findByEmpleado(idReparacion));
        verify(reparacionRepositoryPort).findByEmpleadoId(idReparacion);
        verify(reparacionMapper,times(0)).toResponse(any(Reparacion.class));
    }
}
