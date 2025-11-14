package com.olivera.sistema_reparacion.application.usecases.reparacion;

import com.olivera.sistema_reparacion.application.dto.reparacion.ReparacionResponse;
import com.olivera.sistema_reparacion.application.ports.in.reparacion.BuscarReparacionPorId;
import com.olivera.sistema_reparacion.application.ports.out.ReparacionRepositoryPort;
import com.olivera.sistema_reparacion.domain.entities.Reparacion;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.reparacion.ReparacionMapper;

public class BuscarReparacionPorIdImpl implements BuscarReparacionPorId {

    private final ReparacionRepositoryPort reparacionRepositoryPort;
    private final ReparacionMapper reparacionMapper;

    public BuscarReparacionPorIdImpl(ReparacionRepositoryPort reparacionRepositoryPort, ReparacionMapper reparacionMapper) {
        this.reparacionRepositoryPort = reparacionRepositoryPort;
        this.reparacionMapper = reparacionMapper;
    }

    @Override
    public ReparacionResponse buscarReparacionPorId(Long id) {
        Reparacion encontrada = reparacionRepositoryPort.findById(id).orElseThrow(() -> new RuntimeException("Reparacion no encontrada"));
        return reparacionMapper.toResponse(encontrada);
    }
}
