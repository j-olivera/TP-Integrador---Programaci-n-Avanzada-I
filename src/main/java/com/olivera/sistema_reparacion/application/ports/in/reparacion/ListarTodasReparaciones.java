package com.olivera.sistema_reparacion.application.ports.in.reparacion;

import com.olivera.sistema_reparacion.application.dto.reparacion.ReparacionResponse;

import java.util.List;

public interface ListarTodasReparaciones {
    public List<ReparacionResponse> listarTodasReparaciones();
}
