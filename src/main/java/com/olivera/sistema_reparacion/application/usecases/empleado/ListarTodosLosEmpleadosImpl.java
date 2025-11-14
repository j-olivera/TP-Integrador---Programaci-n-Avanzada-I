package com.olivera.sistema_reparacion.application.usecases.empleado;

import com.olivera.sistema_reparacion.application.dto.empleado.EmpleadoResponse;
import com.olivera.sistema_reparacion.application.ports.in.empleado.ListarTodosLosEmpleados;
import com.olivera.sistema_reparacion.application.ports.out.EmpleadoRepositoryPort;
import com.olivera.sistema_reparacion.domain.entities.Empleado;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.empleado.EmpleadoMapper;

import java.util.List;

public class ListarTodosLosEmpleadosImpl implements ListarTodosLosEmpleados {
    private final EmpleadoRepositoryPort empleadoRepository;
    private final EmpleadoMapper empleadoMapper;

    public ListarTodosLosEmpleadosImpl(EmpleadoRepositoryPort empleadoRepository, EmpleadoMapper empleadoMapper) {
        this.empleadoRepository = empleadoRepository;
        this.empleadoMapper = empleadoMapper;
    }

    @Override
    public List<EmpleadoResponse> listarTodosLosEmpleados() {
        List<Empleado> empleados = empleadoRepository.findAll();
        return empleadoMapper.toResponseList(empleados);
    }
}
