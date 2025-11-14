package com.olivera.sistema_reparacion.application.ports.in.reparacion;

import com.olivera.sistema_reparacion.application.dto.reparacion.ReparacionResponse;
import com.olivera.sistema_reparacion.domain.enums.Estado;

public interface ActualizarEstadoReparacion {
    public ReparacionResponse actualizarEstado(Long id, Estado estado);
}
