package com.olivera.sistema_reparacion.application.ports.in.empleado;

import com.olivera.sistema_reparacion.application.dto.empleado.EmpleadoResponse;
import com.olivera.sistema_reparacion.application.dto.empleado.RegistrarEmpleadoCommand;
import com.olivera.sistema_reparacion.domain.entities.Empleado;

public interface RegistrarEmpleado {
    public EmpleadoResponse saveEmpleado(RegistrarEmpleadoCommand empleado);
}
