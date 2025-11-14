package com.olivera.sistema_reparacion.application.usecases.empleado;

import com.olivera.sistema_reparacion.application.ports.in.empleado.EliminarEmpleado;
import com.olivera.sistema_reparacion.application.ports.out.EmpleadoRepositoryPort;
import com.olivera.sistema_reparacion.domain.exceptions.empleado.EmpleadoNoEncontradoException;

public class EliminarEmpleadoImpl implements EliminarEmpleado {
    private final EmpleadoRepositoryPort empleadoRepository;

    public EliminarEmpleadoImpl(EmpleadoRepositoryPort empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public void eliminarEmpleado(Long empleadoId) {
        if(!empleadoRepository.existeById(empleadoId)){
            throw new EmpleadoNoEncontradoException("Empleado no encontrado");
        }
        empleadoRepository.deleteById(empleadoId);
    }
}
