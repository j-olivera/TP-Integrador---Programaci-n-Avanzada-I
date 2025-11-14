package com.olivera.sistema_reparacion.application.ports.in.equipo;

import com.olivera.sistema_reparacion.application.dto.equipo.EquipoResponse;

public interface BuscarEquipoPorId {
    EquipoResponse buscarEquipoPorId(Long id);
}
