package com.olivera.sistema_reparacion.application.usecases.reparacion;

import com.olivera.sistema_reparacion.application.dto.reparacion.RegistrarReparacionCommand;
import com.olivera.sistema_reparacion.application.dto.reparacion.ReparacionResponse;
import com.olivera.sistema_reparacion.application.ports.in.reparacion.RegistrarReparacion;
import com.olivera.sistema_reparacion.application.ports.out.EmpleadoRepositoryPort;
import com.olivera.sistema_reparacion.application.ports.out.EquipoRepositoryPort;
import com.olivera.sistema_reparacion.application.ports.out.ReparacionRepositoryPort;
import com.olivera.sistema_reparacion.domain.entities.Reparacion;
import com.olivera.sistema_reparacion.domain.exceptions.empleado.EmpleadoNoEncontradoException;
import com.olivera.sistema_reparacion.domain.exceptions.equipo.EquipoNoEncontradoException;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.reparacion.ReparacionMapper;

public class RegistrarReparacionImpl implements RegistrarReparacion {
   private final ReparacionRepositoryPort reparacionRepositoryPort;
   private final EmpleadoRepositoryPort empleadoRepositoryPort;
   private final EquipoRepositoryPort equipoRepositoryPort;
   private final ReparacionMapper reparacionMapper;

    public RegistrarReparacionImpl(ReparacionRepositoryPort reparacionRepositoryPort, EmpleadoRepositoryPort empleadoRepositoryPort, EquipoRepositoryPort equipoRepositoryPort, ReparacionMapper reparacionMapper) {
        this.reparacionRepositoryPort = reparacionRepositoryPort;
        this.empleadoRepositoryPort = empleadoRepositoryPort;
        this.equipoRepositoryPort = equipoRepositoryPort;
        this.reparacionMapper = reparacionMapper;
    }

    @Override
    public ReparacionResponse registrarReparacion(RegistrarReparacionCommand reparacion) {
        reparacion.validar();
        empleadoRepositoryPort.findById(reparacion.getEmpleadoId()).orElseThrow(()-> new EmpleadoNoEncontradoException("Empleado no encontrado"));
        equipoRepositoryPort.findById(reparacion.getEquipoId()).orElseThrow(()-> new EquipoNoEncontradoException("Equipo no encontrado"));
        Reparacion reparacion1 = reparacionMapper.toDomain(reparacion);
        Reparacion reparacion2 = reparacionRepositoryPort.save(reparacion1);
        return reparacionMapper.toResponse(reparacion2);
    }
}
