package com.olivera.sistema_reparacion.application.usecases.empleado;

import com.olivera.sistema_reparacion.application.dto.empleado.EmpleadoResponse;
import com.olivera.sistema_reparacion.application.dto.empleado.RegistrarEmpleadoCommand;
import com.olivera.sistema_reparacion.application.ports.in.empleado.RegistrarEmpleado;
import com.olivera.sistema_reparacion.application.ports.out.EmpleadoRepositoryPort;
import com.olivera.sistema_reparacion.domain.entities.Empleado;
import com.olivera.sistema_reparacion.domain.exceptions.empleado.EmailYaRegistradoException;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.empleado.EmpleadoMapper;

public class RegistrarEmpleadoImpl implements RegistrarEmpleado {
    private final EmpleadoRepositoryPort empleadoRepository;
    private final EmpleadoMapper empleadoMapper;

    public RegistrarEmpleadoImpl(EmpleadoRepositoryPort empleadoRepository, EmpleadoMapper empleadoMapper) {
        this.empleadoRepository = empleadoRepository;
        this.empleadoMapper = empleadoMapper;
    }

    @Override
    public EmpleadoResponse saveEmpleado(RegistrarEmpleadoCommand empleado) {
        empleado.validar();
        //nueva verificacin pq por las dudas
        if(empleadoRepository.findByEmail(empleado.getEmail()).isPresent()){
            throw new EmailYaRegistradoException("Email "+ empleado.getEmail()+" ya existente");
        }
        Empleado empleado1 = empleadoMapper.toDomain(empleado);
        Empleado empleado2 = empleadoRepository.save(empleado1);
        return empleadoMapper.toResponse(empleado2);
    }
}
