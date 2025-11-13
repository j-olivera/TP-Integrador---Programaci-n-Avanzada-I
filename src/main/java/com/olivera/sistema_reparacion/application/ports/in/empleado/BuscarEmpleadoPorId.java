package com.olivera.sistema_reparacion.application.ports.in.empleado;

import com.olivera.sistema_reparacion.domain.entities.Empleado;

public interface BuscarEmpleadoPorId {
    public Empleado buscarEmpleadoPorId(Long id);
}
