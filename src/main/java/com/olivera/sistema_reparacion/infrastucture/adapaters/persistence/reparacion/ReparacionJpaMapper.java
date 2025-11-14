package com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.reparacion;

import com.olivera.sistema_reparacion.application.dto.reparacion.ReparacionResponse;
import com.olivera.sistema_reparacion.domain.entities.Reparacion;
import com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.empleado.EmpleadoEntity;
import com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.equipo.EquipoEntity;
import org.springframework.stereotype.Component;

@Component
public class ReparacionJpaMapper {
    public Reparacion toEntity(ReparacionEntity reparacion) {
        return Reparacion.reconstituir(
                reparacion.getId(),
                reparacion.getDescripcionProblema(),
                reparacion.getDiagnostico(),
                reparacion.getEstado(),
                reparacion.getFechaIngreso(),
                reparacion.getFechaEntrega(),
                reparacion.getCosto(),
                reparacion.getEmpleado().getId(),
                reparacion.getEquipo().getId());

    }
    public ReparacionEntity toEntity(Reparacion reparacion,
                                     EmpleadoEntity empleado,
                                     EquipoEntity equipo) {
        ReparacionEntity entity = new ReparacionEntity(
                reparacion.getId(),
                reparacion.getDescripcionProblema(),
                reparacion.getDiagnostico(),
                reparacion.getEstado(),
                reparacion.getFechaIngreso(),
                reparacion.getFechaEntrega(),
                reparacion.getCosto(),
                empleado,
                equipo
        );
        return entity;
    }

}
