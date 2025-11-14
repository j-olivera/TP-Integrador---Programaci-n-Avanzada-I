package com.olivera.sistema_reparacion.application.usecases.equipo;

import com.olivera.sistema_reparacion.application.dto.equipo.EquipoResponse;
import com.olivera.sistema_reparacion.application.dto.equipo.RegistrarEquipoCommand;
import com.olivera.sistema_reparacion.application.ports.in.equipo.RegistrarEquipo;
import com.olivera.sistema_reparacion.application.ports.out.EquipoRepositoryPort;
import com.olivera.sistema_reparacion.domain.entities.Equipo;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.equipo.EquipoMapper;

public class RegistrarEquipoImpl implements RegistrarEquipo {

    private final EquipoRepositoryPort equipoRepositoryPort;
    private final EquipoMapper equipoMapper;

    public RegistrarEquipoImpl(EquipoRepositoryPort equipoRepositoryPort, EquipoMapper equipoMapper) {
        this.equipoRepositoryPort = equipoRepositoryPort;
        this.equipoMapper = equipoMapper;
    }

    @Override
    public EquipoResponse registrarEquipo(RegistrarEquipoCommand command) {
        command.validar();
        Equipo equipo = equipoMapper.toDomain(command);
        Equipo equipo1 = equipoRepositoryPort.save(equipo);
        return  equipoMapper.toResponse(equipo1);
    }
}
