package com.olivera.sistema_reparacion.application.reparacion;

import com.olivera.sistema_reparacion.application.ports.out.ReparacionRepositoryPort;
import com.olivera.sistema_reparacion.application.usecases.reparacion.EliminarReparacionPorIdImpl;
import com.olivera.sistema_reparacion.domain.exceptions.reparacion.ReparacionNoEncontradaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestEliminarReparacionImpl {
    @Mock
    ReparacionRepositoryPort reparacionRepositoryPort;
    @InjectMocks
    EliminarReparacionPorIdImpl eliminarReparacionPorIdImpl;

    @Test
    void eliminarReparacionPorIdExito(){
        Long id=1L;
        when(reparacionRepositoryPort.existsById(id)).thenReturn(true);
        //
        eliminarReparacionPorIdImpl.eliminarReparacionPorId(id);
        //
        verify(reparacionRepositoryPort).existsById(id);
        verify(reparacionRepositoryPort,times(1)).eliminarReparacionPorId(id);
    }
    @Test
    void eliminarReparacionPorIdNoExito(){
        Long id=1L;
        when(reparacionRepositoryPort.existsById(id)).thenReturn(false);
        //
        Assertions.assertThrows(ReparacionNoEncontradaException.class,()-> eliminarReparacionPorIdImpl.eliminarReparacionPorId(id));
        //
        verify(reparacionRepositoryPort,times(0)).eliminarReparacionPorId(id);
    }
}
