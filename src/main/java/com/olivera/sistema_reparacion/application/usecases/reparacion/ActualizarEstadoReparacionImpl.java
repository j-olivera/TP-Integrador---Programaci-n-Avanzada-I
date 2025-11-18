package com.olivera.sistema_reparacion.application.usecases.reparacion;

import com.olivera.sistema_reparacion.application.dto.reparacion.ActualizarEstadoReparacionCommand;
import com.olivera.sistema_reparacion.application.dto.reparacion.ReparacionResponse;
import com.olivera.sistema_reparacion.application.ports.in.reparacion.ActualizarEstadoReparacion;
import com.olivera.sistema_reparacion.application.ports.out.ReparacionRepositoryPort;
import com.olivera.sistema_reparacion.domain.entities.Reparacion;
import com.olivera.sistema_reparacion.domain.enums.Estado;
import com.olivera.sistema_reparacion.domain.exceptions.reparacion.ReparacionNoEncontradaException;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.reparacion.ReparacionMapper;

public class ActualizarEstadoReparacionImpl implements ActualizarEstadoReparacion {
    private final ReparacionMapper reparacionMapper;
    private final ReparacionRepositoryPort reparacionRepositoryPort;

    public ActualizarEstadoReparacionImpl(ReparacionMapper reparacionMapper, ReparacionRepositoryPort reparacionRepositoryPort) {
        this.reparacionMapper = reparacionMapper;
        this.reparacionRepositoryPort = reparacionRepositoryPort;
    }


    @Override
    public ReparacionResponse actualizarEstado(Long id, ActualizarEstadoReparacionCommand estado) {
        estado.validar(); //aca se valida
        Reparacion nueva = reparacionRepositoryPort.findById(id).orElseThrow(()-> new ReparacionNoEncontradaException("Reparacion no encontrada")); //aca se busca
        nueva.setEstado(estado.getEstado());
        Reparacion guardar = reparacionRepositoryPort.save(nueva); //y aqui guardamos
        return reparacionMapper.toResponse(guardar );
    }
}
