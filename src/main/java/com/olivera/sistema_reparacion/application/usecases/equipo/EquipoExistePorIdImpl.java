package com.olivera.sistema_reparacion.application.usecases.equipo;

import com.olivera.sistema_reparacion.application.ports.in.equipo.EquipoExistePorId;
import com.olivera.sistema_reparacion.application.ports.out.EquipoRepositoryPort;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.equipo.EquipoMapper;

public class EquipoExistePorIdImpl implements EquipoExistePorId {

    private final EquipoRepositoryPort equipoRepositoryPort;
    //private final EquipoMapper equipoMapper;

    public EquipoExistePorIdImpl(EquipoRepositoryPort equipoRepositoryPort /*EquipoMapper equipoMapper*/) {
        this.equipoRepositoryPort = equipoRepositoryPort;
        //this.equipoMapper = equipoMapper;
    }

    @Override
    public boolean existeEquipoPorId(Long id) {
        return equipoRepositoryPort.existsById(id);
    }
}
