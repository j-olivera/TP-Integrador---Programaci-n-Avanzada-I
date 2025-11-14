package com.olivera.sistema_reparacion.infrastucture.adapaters.controllers;

import com.olivera.sistema_reparacion.application.dto.equipo.RegistrarEquipoCommand;
import com.olivera.sistema_reparacion.application.dto.reparacion.ActualizarEstadoReparacionCommand;
import com.olivera.sistema_reparacion.application.dto.reparacion.RegistrarReparacionCommand;
import com.olivera.sistema_reparacion.application.dto.reparacion.ReparacionResponse;
import com.olivera.sistema_reparacion.application.ports.in.reparacion.*;
import com.olivera.sistema_reparacion.domain.entities.Empleado;
import com.olivera.sistema_reparacion.domain.entities.Equipo;
import com.olivera.sistema_reparacion.domain.entities.Reparacion;
import com.olivera.sistema_reparacion.domain.enums.Estado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reparaciones")
public class ReparacionController {
    private final RegistrarReparacion registrarReparacion;
    private final BuscarReparacionPorId buscarReparacionPorId;
    private final BuscarReparacionPorEstado buscarReparacionPorEstado;
    private final ListarTodasReparaciones listarTodasReparaciones;
    private final ActualizarEstadoReparacion actualizarEstadoReparacion;
    private final EliminarReparacionPorId eliminarReparacionPorId;
    private final BuscarReparacionPorEmpleado  buscarReparacionPorEmpleado;

    public ReparacionController(RegistrarReparacion registrarReparacion, BuscarReparacionPorId buscarReparacionPorId, BuscarReparacionPorEstado buscarReparacionPorEstado, ListarTodasReparaciones listarTodasReparaciones, ActualizarEstadoReparacion actualizarEstadoReparacion, EliminarReparacionPorId eliminarReparacionPorId, BuscarReparacionPorEmpleado buscarReparacionPorEmpleado) { //error por no ponerlos en el bean?
        this.registrarReparacion = registrarReparacion;
        this.buscarReparacionPorId = buscarReparacionPorId;
        this.buscarReparacionPorEstado = buscarReparacionPorEstado;
        this.listarTodasReparaciones = listarTodasReparaciones;
        this.actualizarEstadoReparacion = actualizarEstadoReparacion;
        this.eliminarReparacionPorId = eliminarReparacionPorId;
        this.buscarReparacionPorEmpleado = buscarReparacionPorEmpleado;
    }

    @PostMapping
    public ResponseEntity<ReparacionResponse> registrarReparacion(@RequestBody RegistrarReparacionCommand command) {
        ReparacionResponse response = registrarReparacion.registrarReparacion(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    //get
    @GetMapping
    public ResponseEntity<List<ReparacionResponse>> listarReparaciones() {
        List<ReparacionResponse> reparacionResponses = listarTodasReparaciones.listarTodasReparaciones();
        return ResponseEntity.ok(reparacionResponses);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReparacionResponse> getReparacionPorId(@PathVariable Long id) {
        ReparacionResponse response =buscarReparacionPorId.buscarReparacionPorId(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/empleado/{empleadoId}")
    public ResponseEntity<List<ReparacionResponse>> getReparacionPorEmpleado(@PathVariable Long empleadoId) {
        List<ReparacionResponse> reparacionResponses = buscarReparacionPorEmpleado.findByEmpleado(empleadoId);
        return ResponseEntity.ok(reparacionResponses);
    }
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ReparacionResponse>> getReparacionPorEstado(@PathVariable Estado estado) {
        List<ReparacionResponse> reparacionResponses = buscarReparacionPorEstado.buscarReparacionPorEstado(estado);
        return ResponseEntity.ok(reparacionResponses);
    }
    //put
    @PutMapping("/{id}/estado")
    public ResponseEntity<ReparacionResponse> updateEstado(@PathVariable Long id, @RequestBody ActualizarEstadoReparacionCommand command) {
        ReparacionResponse actEstado = actualizarEstadoReparacion.actualizarEstado(id, command);
        return ResponseEntity.ok(actEstado);
    }
    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarReparacionPorId(@PathVariable Long id) {
        eliminarReparacionPorId.eliminarReparacionPorId(id);
        return ResponseEntity.ok("Reparacion eliminada");
    }
}
