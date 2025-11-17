package com.olivera.sistema_reparacion.application.reparacion;

import com.olivera.sistema_reparacion.application.dto.reparacion.ReparacionResponse;
import com.olivera.sistema_reparacion.application.ports.out.ReparacionRepositoryPort;
import com.olivera.sistema_reparacion.application.usecases.reparacion.BuscarReparacionPorEstadoImpl;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestBuscarReparacionPorEstadoImpl {
    @Mock
    ReparacionRepositoryPort reparacionRepositoryPort;
    @Mock
    ReparacionMapper reparacionMapper;
    @InjectMocks
    BuscarReparacionPorEstadoImpl buscarReparacionPorEstadoImpl;

    @Test
    void testBuscarReparacionPorEstadoImplExito() {
        Estado estado = Estado.EN_ESPERA;

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
                2L,
                2L
        );

        List<Reparacion> reparaciones = Arrays.asList(reparacion1, reparacion2);

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
                2L,
                2L
        );
        when(reparacionRepositoryPort.findByEstado(estado)).thenReturn(reparaciones);
        when(reparacionMapper.toResponse(reparacion1)).thenReturn(response1);
        when(reparacionMapper.toResponse(reparacion2)).thenReturn(response2);
        //
        List<ReparacionResponse> resultado = buscarReparacionPorEstadoImpl.buscarReparacionPorEstado(estado);
        //
        Assertions.assertNotNull(resultado);
        Assertions.assertFalse(resultado.isEmpty());
        Assertions.assertEquals(2, resultado.size());
        verify(reparacionRepositoryPort).findByEstado(estado);
        verify(reparacionMapper, times(2)).toResponse(any(Reparacion.class));

        //este test fue pedido a la ia para ahorrar tiempo jeje (lo hizo mal)
    }
    @Test
    void testNoEncuentraReparacionesPorEstado() {
        Estado estado = Estado.EN_ESPERA; //caso nulo es lo mismo
        when(reparacionRepositoryPort.findByEstado(estado)).thenReturn(List.of());
        //
        Assertions.assertThrows(ReparacionNoEncontradaException.class, ()-> buscarReparacionPorEstadoImpl.buscarReparacionPorEstado(estado));
        verify(reparacionRepositoryPort).findByEstado(estado);
        verify(reparacionMapper,never()).toResponse(any());
    }
}
