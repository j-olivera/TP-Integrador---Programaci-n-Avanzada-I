package com.olivera.sistema_reparacion.application.empleado;

import com.olivera.sistema_reparacion.application.dto.empleado.EmpleadoResponse;
import com.olivera.sistema_reparacion.application.dto.empleado.RegistrarEmpleadoCommand;
import com.olivera.sistema_reparacion.application.ports.out.EmpleadoRepositoryPort;
import com.olivera.sistema_reparacion.application.usecases.empleado.RegistrarEmpleadoImpl;
import com.olivera.sistema_reparacion.domain.entities.Empleado;
import com.olivera.sistema_reparacion.domain.exceptions.DatosNoValidosException;
import com.olivera.sistema_reparacion.domain.exceptions.empleado.EmailYaRegistradoException;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.empleado.EmpleadoMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestRegistrarEmpleadoImpl {
    @Mock
    private EmpleadoRepositoryPort empleadoRepository;
    @Mock
    private EmpleadoMapper empleadoMapper;
    @InjectMocks
    private RegistrarEmpleadoImpl registrarEmpleadoImpl;

    @Test
    public void testRegistrarEmpleadoExitosamente() {
        RegistrarEmpleadoCommand command = new RegistrarEmpleadoCommand("Juan", "Olivera", "Programador", "Juan@gmail.com");
        Empleado empleado = Empleado.crearEmpleado("Juan", "Olivera", "Programador", "Juan@gmail.com");
        //Empleado empleadoGuardado = Empleado.reconstruir(1L,"Juan", "Olivera", "Programador", "Juan@gmail.com");
        EmpleadoResponse empResponse = new EmpleadoResponse(1L,"Juan", "Olivera", "Programador", "Juan@gmail.com");

        when(empleadoRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(empleadoMapper.toDomain(any(RegistrarEmpleadoCommand.class))).thenReturn(empleado);
//        when(empleadoRepository.save(any(Empleado.class))).thenReturn(empleadoGuardado); MAVEN DIJO QUE ES INNECESARIO ¿?
        when(empleadoMapper.toResponse(any(Empleado.class))).thenReturn(empResponse);
        when(empleadoRepository.save(any(Empleado.class))).thenReturn(empleado);
        //w h e n
        EmpleadoResponse response = registrarEmpleadoImpl.saveEmpleado(command);
        //assert
       Assertions.assertNotNull(response);
        Assertions.assertEquals("Juan@gmail.com", response.getEmail());
        Assertions.assertEquals(1L, response.getId());

        verify(empleadoRepository).findByEmail("Juan@gmail.com");
        verify(empleadoMapper).toDomain(any(RegistrarEmpleadoCommand.class));
        verify(empleadoRepository).save(any(Empleado.class));
        verify(empleadoMapper).toResponse(any(Empleado.class));
    }

    @Test
    void testEmailYaExisteNoSePuedeRegistrar(){
        RegistrarEmpleadoCommand command = new RegistrarEmpleadoCommand("Juan", "Olivera", "Programador", "Juan@gmail.com");
        Empleado empleadoGuardado = Empleado.reconstruir(2L,"Juan", "Olivera", "Programador", "Juan@gmail.com");
        when(empleadoRepository.findByEmail(command.getEmail())).thenReturn(Optional.of(empleadoGuardado));
        //aasert
        Assertions.assertThrows(EmailYaRegistradoException.class,()-> registrarEmpleadoImpl.saveEmpleado(command));
        //verify donde con never() , indica q NO se guardo
        verify(empleadoRepository).findByEmail("Juan@gmail.com");
        verify(empleadoMapper, never()).toDomain(any());
        verify(empleadoRepository, never()).save(any());
    }

    @Test
    void testDatosNoValidosNoSePuedeRegistrar(){
        RegistrarEmpleadoCommand command = new RegistrarEmpleadoCommand("Juan", "", "Programador", "Juan@gmail.com");

        Assertions.assertThrows(DatosNoValidosException.class, ()->  registrarEmpleadoImpl.saveEmpleado(command));

        verify(empleadoRepository, never()).findByEmail(anyString());
        verify(empleadoRepository, never()).save(any());
    }

}
