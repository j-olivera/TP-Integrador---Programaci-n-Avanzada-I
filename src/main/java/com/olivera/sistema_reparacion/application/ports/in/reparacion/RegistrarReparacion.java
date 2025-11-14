package com.olivera.sistema_reparacion.application.ports.in.reparacion;

import com.olivera.sistema_reparacion.application.dto.reparacion.RegistrarReparacionCommand;
import com.olivera.sistema_reparacion.application.dto.reparacion.ReparacionResponse;
import com.olivera.sistema_reparacion.domain.entities.Reparacion;

public interface RegistrarReparacion {
    public ReparacionResponse registrarReparacion(RegistrarReparacionCommand reparacion);
}
