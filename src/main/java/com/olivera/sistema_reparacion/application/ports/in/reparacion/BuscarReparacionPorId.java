package com.olivera.sistema_reparacion.application.ports.in.reparacion;

import com.olivera.sistema_reparacion.application.dto.reparacion.ReparacionResponse;
import com.olivera.sistema_reparacion.domain.entities.Reparacion;

public interface BuscarReparacionPorId {
    public ReparacionResponse buscarReparacionPorId(Long id);

}
