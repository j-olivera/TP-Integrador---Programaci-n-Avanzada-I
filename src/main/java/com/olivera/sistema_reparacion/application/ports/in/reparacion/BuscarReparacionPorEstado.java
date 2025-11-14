package com.olivera.sistema_reparacion.application.ports.in.reparacion;

import com.olivera.sistema_reparacion.application.dto.reparacion.ReparacionResponse;
import com.olivera.sistema_reparacion.domain.enums.Estado;

import java.util.List;

public interface BuscarReparacionPorEstado {
    List<ReparacionResponse> buscarReparacionPorEstado(Estado estado);
}
