package com.olivera.sistema_reparacion.infrastucture.adapaters.controllers;

import com.olivera.sistema_reparacion.application.ports.in.reparacion.BuscarReparacionPorId;
import com.olivera.sistema_reparacion.application.ports.in.reparacion.ListarTodasReparaciones;
import com.olivera.sistema_reparacion.application.ports.in.reparacion.RegistrarReparacion;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reparaciones")
public class ReparacionController {
    private final RegistrarReparacion registrarReparacion;
    private final BuscarReparacionPorId buscarReparacionPorId;
    private final ListarTodasReparaciones listarTodasReparaciones;

    public ReparacionController(RegistrarReparacion registrarReparacion, BuscarReparacionPorId buscarReparacionPorId, ListarTodasReparaciones listarTodasReparaciones) { //error por no ponerlos en el bean?
        this.registrarReparacion = registrarReparacion;
        this.buscarReparacionPorId = buscarReparacionPorId;
        this.listarTodasReparaciones = listarTodasReparaciones;
    }
}
