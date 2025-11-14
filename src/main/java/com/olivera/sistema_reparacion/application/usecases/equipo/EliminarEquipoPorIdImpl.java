package com.olivera.sistema_reparacion.application.usecases.equipo;

import com.olivera.sistema_reparacion.application.ports.in.equipo.EliminarEquipoPorId;
import com.olivera.sistema_reparacion.application.ports.out.EquipoRepositoryPort;

public class EliminarEquipoPorIdImpl implements EliminarEquipoPorId {
    private final EquipoRepositoryPort  equipoRepositoryPort;

    public EliminarEquipoPorIdImpl(EquipoRepositoryPort equipoRepositoryPort) {
        this.equipoRepositoryPort = equipoRepositoryPort;
    }

    @Override
    public void eliminarEquipo(Long id) {
        equipoRepositoryPort.deleteById(id);
    }
}
