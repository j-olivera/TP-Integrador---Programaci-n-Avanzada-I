package com.olivera.sistema_reparacion.application.equipo;

import com.olivera.sistema_reparacion.application.dto.equipo.EquipoResponse;
import com.olivera.sistema_reparacion.application.dto.equipo.RegistrarEquipoCommand;
import com.olivera.sistema_reparacion.application.ports.out.EquipoRepositoryPort;
import com.olivera.sistema_reparacion.application.usecases.equipo.RegistrarEquipoImpl;
import com.olivera.sistema_reparacion.domain.entities.Empleado;
import com.olivera.sistema_reparacion.domain.entities.Equipo;
import com.olivera.sistema_reparacion.domain.enums.ModeloEquipo;
import com.olivera.sistema_reparacion.domain.enums.TipoEquipo;
import com.olivera.sistema_reparacion.domain.exceptions.DatosNoValidosException;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.equipo.EquipoMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestRegistrarEquipoImpl {
    @Mock
    EquipoRepositoryPort  equipoRepositoryPort;
    @Mock
    EquipoMapper equipoMapper;
    @InjectMocks
    RegistrarEquipoImpl registrarEquipoImpl;

    @Test
    void RegistrarEquipoExitosamente(){
        RegistrarEquipoCommand command = new RegistrarEquipoCommand(TipoEquipo.ALL_IN, ModeloEquipo.ASUS, "ASFAF2131-21", "Juan Olivera", "111111111111111");
        Equipo equipo = Equipo.crearEquipo(TipoEquipo.ALL_IN, ModeloEquipo.ASUS, "ASFAF2131-21", "Juan Olivera", "111111111111111");
        EquipoResponse equipoResponse = new EquipoResponse(1L,TipoEquipo.ALL_IN, ModeloEquipo.ASUS, "ASFAF2131-21", "Juan Olivera", "111111111111111");

        when(equipoRepositoryPort.save(any(Equipo.class))).thenReturn(equipo);
        when(equipoMapper.toResponse(any(Equipo.class))).thenReturn(equipoResponse);
        when(equipoMapper.toDomain(any(RegistrarEquipoCommand.class))).thenReturn(equipo);

        EquipoResponse response = registrarEquipoImpl.registrarEquipo(command);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(equipoResponse, response);
        verify(equipoRepositoryPort).save(any(Equipo.class));
        verify(equipoMapper).toDomain(any(RegistrarEquipoCommand.class));
        verify(equipoMapper).toResponse(any(Equipo.class));
    }
    @Test
    void NoSeRegistroEquipoPorDatosInvalidos(){
        RegistrarEquipoCommand command = new RegistrarEquipoCommand(null, ModeloEquipo.ASUS, "ASFAF2131-21", "Juan Olivera", "111111111111111");

        Assertions.assertThrows(DatosNoValidosException.class, () -> registrarEquipoImpl.registrarEquipo(command));

        verify(equipoRepositoryPort,never()).save(any());
    }

}
