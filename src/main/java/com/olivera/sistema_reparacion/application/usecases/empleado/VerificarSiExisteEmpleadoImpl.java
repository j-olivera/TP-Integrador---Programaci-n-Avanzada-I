package com.olivera.sistema_reparacion.application.usecases.empleado;

import com.olivera.sistema_reparacion.application.ports.in.empleado.VerificarSiExisteEmpleadoPorId;
import com.olivera.sistema_reparacion.application.ports.out.EmpleadoRepository;

public class VerificarSiExisteEmpleadoImpl implements VerificarSiExisteEmpleadoPorId {
    private final EmpleadoRepository empleadoRepository;

    public VerificarSiExisteEmpleadoImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public boolean existById(Long id) {
        return empleadoRepository.existeById(id);
    }
}
