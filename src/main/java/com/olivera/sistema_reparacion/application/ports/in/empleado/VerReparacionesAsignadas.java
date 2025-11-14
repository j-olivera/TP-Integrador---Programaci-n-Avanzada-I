package com.olivera.sistema_reparacion.application.ports.in.empleado;

import com.olivera.sistema_reparacion.application.dto.reparacion.ReparacionResponse;
import com.olivera.sistema_reparacion.domain.entities.Reparacion;

import java.util.List;

public interface VerReparacionesAsignadas {
    List<ReparacionResponse> reparacionesAsignadasPorId(Long id);
}
