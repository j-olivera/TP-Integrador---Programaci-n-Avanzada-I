package com.olivera.sistema_reparacion.application.ports.in.empleado;

import com.olivera.sistema_reparacion.application.dto.empleado.EmpleadoResponse;

import java.util.List;

public interface ListarTodosLosEmpleados {
    List<EmpleadoResponse> listarTodosLosEmpleados();
}
