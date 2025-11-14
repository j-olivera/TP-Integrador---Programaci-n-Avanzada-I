package com.olivera.sistema_reparacion.application.ports.in.equipo;

import com.olivera.sistema_reparacion.application.dto.equipo.EquipoResponse;
import com.olivera.sistema_reparacion.application.dto.equipo.RegistrarEquipoCommand;

public interface RegistrarEquipo {
    EquipoResponse registrarEquipo(RegistrarEquipoCommand command);
}
