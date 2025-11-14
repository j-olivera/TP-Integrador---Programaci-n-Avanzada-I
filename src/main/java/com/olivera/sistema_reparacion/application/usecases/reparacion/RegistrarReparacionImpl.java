package com.olivera.sistema_reparacion.application.usecases.reparacion;

import com.olivera.sistema_reparacion.application.dto.reparacion.RegistrarReparacionCommand;
import com.olivera.sistema_reparacion.application.dto.reparacion.ReparacionResponse;
import com.olivera.sistema_reparacion.application.ports.in.reparacion.RegistrarReparacion;
import com.olivera.sistema_reparacion.application.ports.out.ReparacionRepositoryPort;
import com.olivera.sistema_reparacion.domain.entities.Reparacion;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.reparacion.ReparacionMapper;

public class RegistrarReparacionImpl implements RegistrarReparacion {
   private final ReparacionRepositoryPort reparacionRepositoryPort;
   private final ReparacionMapper reparacionMapper;

    public RegistrarReparacionImpl(ReparacionRepositoryPort reparacionRepositoryPort, ReparacionMapper reparacionMapper) {
        this.reparacionRepositoryPort = reparacionRepositoryPort;
        this.reparacionMapper = reparacionMapper;
    }

    @Override
    public ReparacionResponse registrarReparacion(RegistrarReparacionCommand reparacion) {
        reparacion.validar();
        Reparacion reparacion1 = reparacionMapper.toDomain(reparacion);
        Reparacion reparacion2 = reparacionRepositoryPort.save(reparacion1);
        return reparacionMapper.toResponse(reparacion2);
    }
}
