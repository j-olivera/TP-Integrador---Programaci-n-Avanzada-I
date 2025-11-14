package com.olivera.sistema_reparacion.application.ports.in.equipo;

import com.olivera.sistema_reparacion.application.dto.equipo.EquipoResponse;

import java.util.List;

public interface ListarTodosLosEquipos {
    List<EquipoResponse> listarEquipoPorId(Long id);
}
