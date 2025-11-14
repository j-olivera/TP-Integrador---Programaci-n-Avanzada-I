package com.olivera.sistema_reparacion.application.usecases.reparacion;

import com.olivera.sistema_reparacion.application.ports.in.reparacion.EliminarReparacionPorId;
import com.olivera.sistema_reparacion.application.ports.out.ReparacionRepositoryPort;

public class EliminarReparacionPorIdImpl implements EliminarReparacionPorId {

    private final ReparacionRepositoryPort reparacionRepositoryPort;

    public EliminarReparacionPorIdImpl(ReparacionRepositoryPort reparacionRepositoryPort) {
        this.reparacionRepositoryPort = reparacionRepositoryPort;
    }

    @Override
    public void eliminarReparacionPorId(Long id) {
        reparacionRepositoryPort.eliminarReparacionPorId(id);
    }
}
