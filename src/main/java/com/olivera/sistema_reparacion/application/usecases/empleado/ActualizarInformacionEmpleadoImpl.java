package com.olivera.sistema_reparacion.application.usecases.empleado;

import com.olivera.sistema_reparacion.application.dto.empleado.ActualizarEmpleadoCommand;
import com.olivera.sistema_reparacion.application.dto.empleado.EmpleadoResponse;
import com.olivera.sistema_reparacion.application.ports.in.empleado.ActualizarInformacionEmpleado;
import com.olivera.sistema_reparacion.application.ports.out.EmpleadoRepositoryPort;
import com.olivera.sistema_reparacion.domain.entities.Empleado;
import com.olivera.sistema_reparacion.domain.exceptions.empleado.EmpleadoNoEncontradoException;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.empleado.EmpleadoMapper;

public class ActualizarInformacionEmpleadoImpl implements ActualizarInformacionEmpleado {

    private final EmpleadoRepositoryPort empleadoRepository;
    private final EmpleadoMapper empleadoMapper;
    public ActualizarInformacionEmpleadoImpl(EmpleadoRepositoryPort empleadoRepository, EmpleadoMapper empleadoMapper) {
        this.empleadoRepository = empleadoRepository;
        this.empleadoMapper = empleadoMapper;
    }

    @Override
    public EmpleadoResponse actualizarEmpleado(ActualizarEmpleadoCommand empleado) {
        empleado.validar();
        Empleado empleado1 = empleadoRepository.findById(empleado.getId())
                .orElseThrow(() -> new EmpleadoNoEncontradoException("Empleado de id "+empleado.getId()+" no encontrado"));
        Empleado reconstruido= Empleado.reconstruir(empleado1.getId(),empleado1.getNombre(),empleado1.getApellido(),empleado1.getEspecialidad(),empleado1.getEmail());
        Empleado actualizado= empleadoRepository.save(reconstruido);
        return empleadoMapper.toResponse(actualizado);
    }

}
