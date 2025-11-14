package com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.empleado;

import com.olivera.sistema_reparacion.domain.entities.Empleado;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoJpaMapper {

    public EmpleadoEntity toEntity(Empleado empleado) {
        return new EmpleadoEntity(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getEspecialidad(),
                empleado.getEmail()
        );
    }
    public Empleado toDomain(EmpleadoEntity entity) {
        return Empleado.reconstruir(
                entity.getId(),
                entity.getNombre(),
                entity.getApellido(),
                entity.getEspecialidad(),
                entity.getEmail()
        );
    }
}
