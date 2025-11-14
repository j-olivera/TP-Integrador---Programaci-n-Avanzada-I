package com.olivera.sistema_reparacion.application.usecases.reparacion;

import com.olivera.sistema_reparacion.application.dto.reparacion.ReparacionResponse;
import com.olivera.sistema_reparacion.application.ports.in.reparacion.BuscarReparacionPorEmpleado;
import com.olivera.sistema_reparacion.application.ports.out.ReparacionRepositoryPort;
import com.olivera.sistema_reparacion.domain.entities.Reparacion;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.reparacion.ReparacionMapper;

import java.util.List;
import java.util.stream.Collectors;

public class BuscarReparacionPorEmpleadoImpl implements BuscarReparacionPorEmpleado {
    private final ReparacionRepositoryPort reparacionRepositoryPort;
    private final ReparacionMapper reparacionMapper;

    public BuscarReparacionPorEmpleadoImpl(ReparacionRepositoryPort reparacionRepositoryPort, ReparacionMapper reparacionMapper) {
        this.reparacionRepositoryPort = reparacionRepositoryPort;
        this.reparacionMapper = reparacionMapper;
    }

    @Override
    public List<ReparacionResponse> findByEmpleado(Long id) {
        List<Reparacion> reparaciones = reparacionRepositoryPort.findByEmpleadoId(id);
        return reparaciones.stream()
                .map(reparacionMapper::toResponse)
                .collect(Collectors.toList());
    }
}
