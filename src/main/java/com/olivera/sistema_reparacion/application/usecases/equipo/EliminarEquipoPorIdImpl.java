package com.olivera.sistema_reparacion.application.usecases.equipo;

import com.olivera.sistema_reparacion.application.ports.in.equipo.EliminarEquipoPorId;
import com.olivera.sistema_reparacion.application.ports.out.EquipoRepositoryPort;
import com.olivera.sistema_reparacion.domain.exceptions.equipo.EquipoNoEncontradoException;

public class EliminarEquipoPorIdImpl implements EliminarEquipoPorId {
    private final EquipoRepositoryPort  equipoRepositoryPort;

    public EliminarEquipoPorIdImpl(EquipoRepositoryPort equipoRepositoryPort) {
        this.equipoRepositoryPort = equipoRepositoryPort;
    }

    @Override
    public void eliminarEquipo(Long id) {
        if(!equipoRepositoryPort.existsById(id)){
            throw new EquipoNoEncontradoException("Equipo no encontrado");
        }
        equipoRepositoryPort.deleteById(id);
    }
}
