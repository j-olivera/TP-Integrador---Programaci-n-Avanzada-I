package com.olivera.sistema_reparacion.application.equipo;

import com.olivera.sistema_reparacion.application.ports.out.EquipoRepositoryPort;
import com.olivera.sistema_reparacion.application.usecases.equipo.EliminarEquipoPorIdImpl;
import com.olivera.sistema_reparacion.domain.exceptions.empleado.EmpleadoNoEncontradoException;
import com.olivera.sistema_reparacion.domain.exceptions.equipo.EquipoNoEncontradoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
public class TestEliminarEquipoPorIdImpl {
    @Mock
    EquipoRepositoryPort equipoRepositoryPort;
    @InjectMocks
    EliminarEquipoPorIdImpl  eliminarEquipoPorIdImpl;

    @Test
    void testEliminarEquipoExito(){
        when(equipoRepositoryPort.existsById(anyLong())).thenReturn(true);
        //
        eliminarEquipoPorIdImpl.eliminarEquipo(anyLong());
        //
        verify(equipoRepositoryPort,times(1)).deleteById(anyLong());
    }
    @Test
    void testEliminarEquipoNoExito(){
        when(equipoRepositoryPort.existsById(anyLong())).thenReturn(false);
        //
        Assertions.assertThrows(EquipoNoEncontradoException.class,()-> eliminarEquipoPorIdImpl.eliminarEquipo(anyLong()));
        //
        verify(equipoRepositoryPort,never()).deleteById(anyLong());
    }
}
