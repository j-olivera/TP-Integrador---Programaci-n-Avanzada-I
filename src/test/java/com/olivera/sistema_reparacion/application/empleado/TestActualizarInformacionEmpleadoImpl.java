package com.olivera.sistema_reparacion.application.empleado;

import com.olivera.sistema_reparacion.application.dto.empleado.ActualizarEmpleadoCommand;
import com.olivera.sistema_reparacion.application.dto.empleado.EmpleadoResponse;
import com.olivera.sistema_reparacion.application.dto.empleado.RegistrarEmpleadoCommand;
import com.olivera.sistema_reparacion.application.dto.reparacion.ActualizarEstadoReparacionCommand;
import com.olivera.sistema_reparacion.application.ports.out.EmpleadoRepositoryPort;
import com.olivera.sistema_reparacion.application.usecases.empleado.ActualizarInformacionEmpleadoImpl;
import com.olivera.sistema_reparacion.domain.entities.Empleado;
import com.olivera.sistema_reparacion.domain.exceptions.empleado.EmpleadoNoEncontradoException;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.empleado.EmpleadoMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestActualizarInformacionEmpleadoImpl {

    @Mock
    EmpleadoRepositoryPort  empleadoRepositoryPort;
    @Mock
    EmpleadoMapper empleadoMapper;
    @InjectMocks
    ActualizarInformacionEmpleadoImpl actualizarInformacionEmpleadoImpl;

    @Test
    void TestEmpleadoActualizadoCorrectamente(){
        ActualizarEmpleadoCommand command = new ActualizarEmpleadoCommand(1L,"Juan", "Olivera", "Programador", "Juan@gmail.com");
        Empleado reconstruido = Empleado.reconstruir(1L,"Juan", "Olivera", "Programador", "Juan@gmail.com");
        EmpleadoResponse response = new EmpleadoResponse(1L,"Juan", "Olivera", "Programador", "Juan@gmail.com");

        when(empleadoRepositoryPort.findById(anyLong())).thenReturn(Optional.of(reconstruido));
        when(empleadoMapper.toResponse(any())).thenReturn(response);
        when(empleadoRepositoryPort.save(any())).thenReturn(reconstruido);
        //
        EmpleadoResponse responseComparado = actualizarInformacionEmpleadoImpl.actualizarEmpleado(command);
        //
        Assertions.assertNotNull(responseComparado);
        Assertions.assertEquals(1L, responseComparado.getId());
        //
        verify(empleadoRepositoryPort).findById(1L);
        verify(empleadoMapper).toResponse(any());
        verify(empleadoRepositoryPort).save(any());

    }

    @Test
    void TestEmpleadoNoExisteNoSeActualizaLaInfo(){
        ActualizarEmpleadoCommand command = new ActualizarEmpleadoCommand(2L,"Juan", "Olivera", "Programador", "Juan@gmail.com");
        when(empleadoRepositoryPort.findById(command.getId())).thenReturn(Optional.empty());
        //Assert
        Assertions.assertThrows(EmpleadoNoEncontradoException.class,()-> actualizarInformacionEmpleadoImpl.actualizarEmpleado(command));
        //
        verify(empleadoRepositoryPort, never()).save(any());
        verify(empleadoMapper,never()).toResponse(any());
        verify(empleadoRepositoryPort).findById(command.getId());
    }
}
