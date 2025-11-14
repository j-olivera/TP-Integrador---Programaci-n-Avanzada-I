package com.olivera.sistema_reparacion.application.usecases.reparacion;

import com.olivera.sistema_reparacion.application.ports.in.reparacion.EliminarReparacionPorId;
import com.olivera.sistema_reparacion.application.ports.out.ReparacionRepositoryPort;
import com.olivera.sistema_reparacion.domain.exceptions.reparacion.ReparacionNoEncontradaException;

public class EliminarReparacionPorIdImpl implements EliminarReparacionPorId {

    private final ReparacionRepositoryPort reparacionRepositoryPort;

    public EliminarReparacionPorIdImpl(ReparacionRepositoryPort reparacionRepositoryPort) {
        this.reparacionRepositoryPort = reparacionRepositoryPort;
    }

    @Override
    public void eliminarReparacionPorId(Long id) {
        if(!reparacionRepositoryPort.existsById(id)){
            throw new ReparacionNoEncontradaException("Reparacion no encontrada");
        }
        reparacionRepositoryPort.eliminarReparacionPorId(id);
    }
}
