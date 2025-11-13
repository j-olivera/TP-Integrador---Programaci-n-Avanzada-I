package com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.empleado;

import com.olivera.sistema_reparacion.application.dto.empleado.EmpleadoResponse;
import com.olivera.sistema_reparacion.application.dto.empleado.RegistrarEmpleadoCommand;
import com.olivera.sistema_reparacion.domain.entities.Empleado;

import java.util.List;
import java.util.stream.Collectors;

public class EmpleadoMapper {
    // Command → Domain (para crear)
    public Empleado toDomain(RegistrarEmpleadoCommand command) {
        return Empleado.crearEmpleado(
                command.getNombre(),
                command.getApellido(),
                command.getEspecialidad(),
                command.getEmail()
        );
    }

    // Domain → Response
    public EmpleadoResponse toResponse(Empleado empleado) {
        return new EmpleadoResponse(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getEspecialidad(),
                empleado.getEmail()
        );
    }

    // List<Domain> → List<Response>
    public List<EmpleadoResponse> toResponseList(List<Empleado> empleados) {
        return empleados.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
