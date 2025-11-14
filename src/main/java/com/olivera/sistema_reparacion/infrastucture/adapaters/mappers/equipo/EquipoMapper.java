package com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.equipo;

import com.olivera.sistema_reparacion.application.dto.equipo.EquipoResponse;
import com.olivera.sistema_reparacion.application.dto.equipo.RegistrarEquipoCommand;
import com.olivera.sistema_reparacion.domain.entities.Equipo;

import java.util.List;
import java.util.stream.Collectors;

public class EquipoMapper {
    public Equipo toDomain(RegistrarEquipoCommand command) {
        return Equipo.crearEquipo(
                command.getTipo(),
                command.getModelo(),
                command.getNumeroSerie(),
                command.getNombreCliente(),
                command.getNumeroCliente());

    }
    public EquipoResponse toResponse(Equipo equipo) {
        return new EquipoResponse(
                equipo.getId(),
                equipo.getTipo(),
                equipo.getModelo(),
                equipo.getNumeroSerie(),
                equipo.getNombreCliente(),
                equipo.getNumeroCliente()
        );
    }
    public List<EquipoResponse> toResponseList(List<Equipo> equipos) {
        return equipos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
