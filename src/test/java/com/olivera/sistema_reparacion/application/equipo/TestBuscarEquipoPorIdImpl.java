package com.olivera.sistema_reparacion.application.equipo;

import com.olivera.sistema_reparacion.application.dto.equipo.EquipoResponse;
import com.olivera.sistema_reparacion.application.ports.out.EquipoRepositoryPort;
import com.olivera.sistema_reparacion.application.usecases.empleado.BuscarEmpleadoPorIdImpl;
import com.olivera.sistema_reparacion.application.usecases.equipo.BuscarEquipoPorIdImpl;
import com.olivera.sistema_reparacion.domain.entities.Equipo;
import com.olivera.sistema_reparacion.domain.enums.ModeloEquipo;
import com.olivera.sistema_reparacion.domain.enums.TipoEquipo;
import com.olivera.sistema_reparacion.domain.exceptions.equipo.EquipoNoEncontradoException;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.equipo.EquipoMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestBuscarEquipoPorIdImpl {
    @Mock
    EquipoRepositoryPort equipoRepositoryPort;
    @Mock
    EquipoMapper equipoMapper;
    @InjectMocks
    BuscarEquipoPorIdImpl buscarEquipoPorIdImpl;

    @Test
    void testBuscarEquipoPorIdExisto(){
        Long idEquipo=1L;
        Equipo encontrad = Equipo.reconstituir(1L, TipoEquipo.ALL_IN, ModeloEquipo.ASUS, "ASF212-12", "Juan Olivera", "111111111111111");
        EquipoResponse equipoResponse = new EquipoResponse(1L, TipoEquipo.ALL_IN, ModeloEquipo.ASUS, "ASF212-12", "Juan Olivera", "111111111111111");
        when(equipoRepositoryPort.findById(idEquipo)).thenReturn(Optional.of(encontrad));
        when(equipoMapper.toResponse(encontrad)).thenReturn(equipoResponse);
        //
        EquipoResponse response = buscarEquipoPorIdImpl.buscarEquipoPorId(idEquipo);
        //
        Assertions.assertNotNull(response);
        Assertions.assertEquals(equipoResponse,response);
        verify(equipoRepositoryPort).findById(idEquipo);
        verify(equipoMapper).toResponse(encontrad);
    }

    @Test
    void testBuscarEquipoPorIdExistoNoExiste(){
        Long idEquipo=1L;
        when(equipoRepositoryPort.findById(idEquipo)).thenReturn(Optional.empty());
        //
        Assertions.assertThrows(EquipoNoEncontradoException.class, ()-> buscarEquipoPorIdImpl.buscarEquipoPorId(idEquipo));
    }
}
