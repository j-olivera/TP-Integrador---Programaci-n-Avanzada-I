package com.olivera.sistema_reparacion.application.usecases.equipo;

import com.olivera.sistema_reparacion.application.dto.equipo.EquipoResponse;
import com.olivera.sistema_reparacion.application.ports.in.equipo.BuscarEquipoPorId;
import com.olivera.sistema_reparacion.application.ports.out.EquipoRepositoryPort;
import com.olivera.sistema_reparacion.domain.entities.Equipo;
import com.olivera.sistema_reparacion.domain.exceptions.equipo.EquipoNoEncontradoException;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.equipo.EquipoMapper;

public class BuscarEquipoPorIdImpl implements BuscarEquipoPorId {

    private final EquipoRepositoryPort equipoRepositoryPort;
    private final EquipoMapper equipoMapper;

    public BuscarEquipoPorIdImpl(EquipoRepositoryPort equipoRepositoryPort, EquipoMapper equipoMapper) {
        this.equipoRepositoryPort = equipoRepositoryPort;
        this.equipoMapper = equipoMapper;
    }


    @Override
    public EquipoResponse buscarEquipoPorId(Long id) {
        Equipo equipo = equipoRepositoryPort.findById(id).
                orElseThrow(()-> new EquipoNoEncontradoException("Equipo no encontrado"));
        return equipoMapper.toResponse(equipo);
    }
}
