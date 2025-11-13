package com.olivera.sistema_reparacion.application.usecases.empleado;

import com.olivera.sistema_reparacion.application.dto.empleado.EmpleadoResponse;
import com.olivera.sistema_reparacion.application.ports.in.empleado.BuscarEmpleadoPorId;
import com.olivera.sistema_reparacion.application.ports.in.empleado.ListarTodosLosEmpleados;
import com.olivera.sistema_reparacion.application.ports.out.EmpleadoRepository;
import com.olivera.sistema_reparacion.domain.entities.Empleado;
import com.olivera.sistema_reparacion.domain.exceptions.empleado.EmpleadoNoEncontradoException;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.empleado.EmpleadoMapper;

public class BuscarEmpleadoPorIdImpl implements BuscarEmpleadoPorId {

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;

    public BuscarEmpleadoPorIdImpl(EmpleadoRepository empleadoRepository, EmpleadoMapper empleadoMapper) {
        this.empleadoRepository = empleadoRepository;
        this.empleadoMapper = empleadoMapper;
    }

    @Override
    public EmpleadoResponse buscarEmpleadoPorId(Long id) {
        Empleado empleado = empleadoRepository.findById(id).orElseThrow(()-> new EmpleadoNoEncontradoException("El empleado no existe"));
        return empleadoMapper.toResponse(empleado);
    }
}
