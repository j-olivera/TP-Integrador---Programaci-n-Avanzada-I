package com.olivera.sistema_reparacion.application.usecases.reparacion;

import com.olivera.sistema_reparacion.application.dto.reparacion.ReparacionResponse;
import com.olivera.sistema_reparacion.application.ports.in.reparacion.BuscarReparacionPorEstado;
import com.olivera.sistema_reparacion.application.ports.out.ReparacionRepositoryPort;
import com.olivera.sistema_reparacion.domain.entities.Reparacion;
import com.olivera.sistema_reparacion.domain.enums.Estado;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.reparacion.ReparacionMapper;

import java.util.List;
import java.util.stream.Collectors;

public class BuscarReparacionPorEstadoImpl implements BuscarReparacionPorEstado {

    private final ReparacionRepositoryPort reparacionRepositoryPort;
    private final ReparacionMapper reparacionMapper;

    public BuscarReparacionPorEstadoImpl(ReparacionRepositoryPort reparacionRepositoryPort, ReparacionMapper reparacionMapper) {
        this.reparacionRepositoryPort = reparacionRepositoryPort;
        this.reparacionMapper = reparacionMapper;
    }

    @Override
    public List<ReparacionResponse> buscarReparacionPorEstado(Estado estado) {
        List<Reparacion> encontrados = reparacionRepositoryPort.findByEstado(estado);
        return encontrados.stream()
                .map(reparacionMapper::toResponse)
                .collect(Collectors.toList());
    }
}
