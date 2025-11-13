package com.olivera.sistema_reparacion.application.ports.in.empleado;

import com.olivera.sistema_reparacion.domain.entities.Empleado;

public interface RegistrarEmpleado {
    public Empleado saveEmpleado(Empleado empleado);
}
