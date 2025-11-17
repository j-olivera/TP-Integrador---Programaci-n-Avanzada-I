package com.olivera.sistema_reparacion.application.equipo;

import com.olivera.sistema_reparacion.application.dto.equipo.EquipoResponse;
import com.olivera.sistema_reparacion.application.ports.in.equipo.BuscarEquipoPorNumeroSerie;
import com.olivera.sistema_reparacion.application.ports.out.EquipoRepositoryPort;
import com.olivera.sistema_reparacion.application.usecases.empleado.BuscarEmpleadoPorIdImpl;
import com.olivera.sistema_reparacion.application.usecases.equipo.BuscarEquipoPorNumeroSerieImpl;
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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestBuscarEquipoPorNumeroSerieImpl {
    @Mock
    EquipoRepositoryPort equipoRepositoryPort;
    @Mock
    EquipoMapper equipoMapper;
    @InjectMocks
    BuscarEquipoPorNumeroSerieImpl buscarEquipoPorNumeroSerieImpl;
    @Test
    public void testBuscarEquipoPorNumeroSerie(){
        String nroSerie = "ASF212-12";
        Equipo encontrad = Equipo.reconstituir(1L, TipoEquipo.ALL_IN, ModeloEquipo.ASUS, "ASF212-12", "Juan Olivera", "111111111111111");
        EquipoResponse equipoResponse = new EquipoResponse(1L, TipoEquipo.ALL_IN, ModeloEquipo.ASUS, "ASF212-12", "Juan Olivera", "111111111111111");
        when(equipoRepositoryPort.findByNumeroSerie(nroSerie)).thenReturn(Optional.of(encontrad));
        when(equipoMapper.toResponse(encontrad)).thenReturn(equipoResponse);
        //
        EquipoResponse response = buscarEquipoPorNumeroSerieImpl.buscarEquipoPorNumeroSerie(nroSerie);
        //
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getNumeroSerie(), nroSerie);
        verify(equipoRepositoryPort).findByNumeroSerie(nroSerie);
        verify(equipoMapper).toResponse(encontrad);
    }
    @Test
    public void testBuscarEquipoPorNumeroSerieNoExiste(){
        String nroSerie = "ASF212-12";
        when(equipoRepositoryPort.findByNumeroSerie(nroSerie)).thenReturn(Optional.empty());
       //
        Assertions.assertThrows(EquipoNoEncontradoException.class,() -> buscarEquipoPorNumeroSerieImpl.buscarEquipoPorNumeroSerie(nroSerie));
        verify(equipoMapper, never()).toResponse(any(Equipo.class));
    }
}
