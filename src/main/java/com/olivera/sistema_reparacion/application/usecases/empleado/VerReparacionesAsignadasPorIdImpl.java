package com.olivera.sistema_reparacion.application.usecases.empleado;

import com.olivera.sistema_reparacion.application.dto.reparacion.ReparacionResponse;
import com.olivera.sistema_reparacion.application.ports.in.empleado.VerReparacionesAsignadas;
import com.olivera.sistema_reparacion.application.ports.out.EmpleadoRepositoryPort;
import com.olivera.sistema_reparacion.application.ports.out.ReparacionRepositoryPort;
import com.olivera.sistema_reparacion.domain.entities.Reparacion;
import com.olivera.sistema_reparacion.domain.exceptions.empleado.EmpleadoNoEncontradoException;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.reparacion.ReparacionMapper;

import java.util.List;

public class VerReparacionesAsignadasPorIdImpl implements VerReparacionesAsignadas {
    private final EmpleadoRepositoryPort empleadoRepositoryPort;
    private final ReparacionRepositoryPort reparacionRepositoryPort;
    private final ReparacionMapper reparacionMapper;
    public VerReparacionesAsignadasPorIdImpl(EmpleadoRepositoryPort empleadoRepositoryPort, ReparacionRepositoryPort reparacionRepositoryPort, ReparacionMapper reparacionMapper) {
        this.empleadoRepositoryPort = empleadoRepositoryPort;
        this.reparacionRepositoryPort = reparacionRepositoryPort;
        this.reparacionMapper = reparacionMapper;
    }

    @Override
    public List<ReparacionResponse> reparacionesAsignadasPorId(Long id) {
        if(!empleadoRepositoryPort.existeById(id)){
            throw new EmpleadoNoEncontradoException("El empleado no existe");
        }
        List<Reparacion> reparaciones = reparacionRepositoryPort.findByEmpleadoId(id);
        return reparacionMapper.toResponseList(reparaciones);
    }
}
