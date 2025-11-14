package com.olivera.sistema_reparacion.infrastucture.adapaters.controllers;

import com.olivera.sistema_reparacion.application.dto.reparacion.RegistrarReparacionCommand;
import com.olivera.sistema_reparacion.application.dto.reparacion.ReparacionResponse;
import com.olivera.sistema_reparacion.application.ports.in.reparacion.BuscarReparacionPorId;
import com.olivera.sistema_reparacion.application.ports.in.reparacion.ListarTodasReparaciones;
import com.olivera.sistema_reparacion.application.ports.in.reparacion.RegistrarReparacion;
import com.olivera.sistema_reparacion.domain.entities.Empleado;
import com.olivera.sistema_reparacion.domain.entities.Equipo;
import com.olivera.sistema_reparacion.domain.entities.Reparacion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<ReparacionResponse> registrarReparacion(@RequestBody Reparacion reparacion, Long empleadoId, Long equipoId) {
        RegistrarReparacionCommand command = new RegistrarReparacionCommand(
                reparacion.getDescripcionProblema(),
                reparacion.getDiagnostico(),
                reparacion.getEstado(),
                reparacion.getFechaIngreso(),
                reparacion.getFechaEntrega(),
                reparacion.getCosto(),
                empleadoId,
                equipoId);
        ReparacionResponse response = registrarReparacion.registrarReparacion(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
