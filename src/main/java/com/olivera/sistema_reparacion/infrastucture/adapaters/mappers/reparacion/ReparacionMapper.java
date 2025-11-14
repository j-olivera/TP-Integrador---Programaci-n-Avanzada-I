package com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.reparacion;

import com.olivera.sistema_reparacion.application.dto.reparacion.RegistrarReparacionCommand;
import com.olivera.sistema_reparacion.application.dto.reparacion.ReparacionResponse;
import com.olivera.sistema_reparacion.domain.entities.Reparacion;
import com.olivera.sistema_reparacion.domain.enums.Estado;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ReparacionMapper {
    public Reparacion toDomain(RegistrarReparacionCommand registrarReparacionCommand) {
        return Reparacion.crearReparacion(
                registrarReparacionCommand.getDescripcionProblema(),
                registrarReparacionCommand.getDiagnostico(),
                registrarReparacionCommand.getEstado(),
                registrarReparacionCommand.getFechaIngreso(),
                registrarReparacionCommand.getFechaEntrega(),
                registrarReparacionCommand.getCosto(),
                null,
                null);
    }


    public ReparacionResponse toResponse(Reparacion reparacion) {
        return new ReparacionResponse(
                reparacion.getId(),
                reparacion.getDescripcionProblema(),
                reparacion.getDiagnostico(),
                reparacion.getEstado(),
                reparacion.getFechaIngreso(),
                reparacion.getFechaEntrega(),
                reparacion.getCosto());
    }

    public List<ReparacionResponse> toResponseList(List<Reparacion> reparaciones) {
        return reparaciones.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
