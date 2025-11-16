package com.olivera.sistema_reparacion.application.empleado;

import com.olivera.sistema_reparacion.application.dto.empleado.EmpleadoResponse;
import com.olivera.sistema_reparacion.application.ports.in.empleado.BuscarEmpleadoPorId;
import com.olivera.sistema_reparacion.application.ports.out.EmpleadoRepositoryPort;
import com.olivera.sistema_reparacion.application.usecases.empleado.BuscarEmpleadoPorIdImpl;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestBuscarEmpleadoPorIdImpl {
    @Mock
    EmpleadoRepositoryPort empleadoRepositoryPort;
    @Mock
    EmpleadoMapper empleadoMapper;
    @InjectMocks
    BuscarEmpleadoPorIdImpl buscarEmpleadoPorId;
    @Test
    public void testBuscarEmpleadoPorIdExito(){
        Long idEmpleado = 1L; //id buscada
        Empleado reconstruido = Empleado.reconstruir(1L,"Juan", "Olivera", "Programador", "Juan@gmail.com");
        EmpleadoResponse empResponse  = new EmpleadoResponse(1L,"Juan", "Olivera", "Programador", "Juan@gmail.com");
        when(empleadoRepositoryPort.findById(idEmpleado)).thenReturn(Optional.of(reconstruido));
        when(empleadoMapper.toResponse(any(Empleado.class))).thenReturn(empResponse);
        //
        EmpleadoResponse response = buscarEmpleadoPorId.buscarEmpleadoPorId(idEmpleado);
        //
        Assertions.assertNotNull(response);
        verify(empleadoRepositoryPort).findById(idEmpleado);
        verify(empleadoMapper).toResponse(any(Empleado.class));
    }

    @Test
    void testBuscarEmpleadoPorIdNoExito(){
        Long idEmpleado = 10L;
        when(empleadoRepositoryPort.findById(idEmpleado)).thenReturn(Optional.empty());
        //salta la excep
        Assertions.assertThrows(EmpleadoNoEncontradoException.class,()->buscarEmpleadoPorId.buscarEmpleadoPorId(idEmpleado));
        //
        verify(empleadoRepositoryPort).findById(idEmpleado); //se uso
        verify(empleadoMapper,never()).toResponse(any(Empleado.class));//no mapeo

    }
}
