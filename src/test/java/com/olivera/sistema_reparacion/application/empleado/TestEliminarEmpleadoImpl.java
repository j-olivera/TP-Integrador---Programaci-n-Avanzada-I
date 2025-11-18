package com.olivera.sistema_reparacion.application.empleado;

import com.olivera.sistema_reparacion.application.ports.out.EmpleadoRepositoryPort;
import com.olivera.sistema_reparacion.application.usecases.empleado.EliminarEmpleadoImpl;
import com.olivera.sistema_reparacion.domain.exceptions.empleado.EmpleadoNoEncontradoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestEliminarEmpleadoImpl {
    @Mock
    EmpleadoRepositoryPort  empleadoRepositoryPort;
    @InjectMocks
    EliminarEmpleadoImpl eliminarEmpleadoImpl;

    @Test
    void testEliminarEmpleadoExito(){
        Long id=1L;
        when(empleadoRepositoryPort.existeById(id)).thenReturn(true);
        //
        eliminarEmpleadoImpl.eliminarEmpleado(id);
        //
        verify(empleadoRepositoryPort,times(1)).deleteById(id);
    }
    @Test
    void testEliminarEmpleadoNoExito(){
        Long id=1L;
        when(empleadoRepositoryPort.existeById(id)).thenReturn(false);
        //
        Assertions.assertThrows(EmpleadoNoEncontradoException.class, () -> eliminarEmpleadoImpl.eliminarEmpleado(id));
        //
        verify(empleadoRepositoryPort,never()).deleteById(id);
    }
}
