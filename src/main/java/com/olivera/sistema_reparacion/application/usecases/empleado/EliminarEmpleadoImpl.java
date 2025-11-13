package com.olivera.sistema_reparacion.application.usecases.empleado;

import com.olivera.sistema_reparacion.application.ports.in.empleado.EliminarEmpleado;
import com.olivera.sistema_reparacion.application.ports.out.EmpleadoRepository;

public class EliminarEmpleadoImpl implements EliminarEmpleado {
    private final EmpleadoRepository empleadoRepository;

    public EliminarEmpleadoImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public void eliminarEmpleado(Long empleadoId) {
        empleadoRepository.deleteById(empleadoId);
    }
}
