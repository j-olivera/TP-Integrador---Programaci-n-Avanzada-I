package com.olivera.sistema_reparacion.application.usecases.empleado;

import com.olivera.sistema_reparacion.application.ports.in.empleado.EliminarEmpleado;
import com.olivera.sistema_reparacion.application.ports.out.EmpleadoRepositoryPort;

public class EliminarEmpleadoImpl implements EliminarEmpleado {
    private final EmpleadoRepositoryPort empleadoRepository;

    public EliminarEmpleadoImpl(EmpleadoRepositoryPort empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public void eliminarEmpleado(Long empleadoId) {
        empleadoRepository.deleteById(empleadoId);
    }
}
