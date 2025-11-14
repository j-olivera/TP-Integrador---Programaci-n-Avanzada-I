package com.olivera.sistema_reparacion.application.usecases.equipo;

import com.olivera.sistema_reparacion.application.dto.equipo.EquipoResponse;
import com.olivera.sistema_reparacion.application.ports.in.equipo.ListarTodosLosEquipos;
import com.olivera.sistema_reparacion.application.ports.out.EquipoRepositoryPort;
import com.olivera.sistema_reparacion.domain.entities.Equipo;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.equipo.EquipoMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ListarTodosLosEquiposImpl implements ListarTodosLosEquipos {

    private final EquipoRepositoryPort equipoRepositoryPort;
    private final EquipoMapper equipoMapper;

    public ListarTodosLosEquiposImpl(EquipoRepositoryPort equipoRepositoryPort, EquipoMapper equipoMapper) {
        this.equipoRepositoryPort = equipoRepositoryPort;
        this.equipoMapper = equipoMapper;
    }

    @Override
    public List<EquipoResponse> listarEquipoPorId(Long id) {
        List<Equipo> equipos = equipoRepositoryPort.findAll();
        if (equipos.isEmpty()) {
            throw new RuntimeException("No hay equipos");
        }
        return equipos.stream()
                .map(equipoMapper::toResponse)
                .collect(Collectors.toList());
    }
}
