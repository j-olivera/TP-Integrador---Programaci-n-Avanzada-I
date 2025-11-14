package com.olivera.sistema_reparacion.infrastucture.adapaters.controllers;

import com.olivera.sistema_reparacion.application.dto.empleado.EmpleadoResponse;
import com.olivera.sistema_reparacion.application.dto.equipo.EquipoResponse;
import com.olivera.sistema_reparacion.application.dto.equipo.RegistrarEquipoCommand;
import com.olivera.sistema_reparacion.application.ports.in.equipo.*;
import com.olivera.sistema_reparacion.domain.entities.Equipo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {
    private final RegistrarEquipo registrarEquipo;
    private final BuscarEquipoPorId buscarEquipoPorId;
    private final EliminarEquipoPorId  eliminarEquipoPorId;
    private final ListarTodosLosEquipos  listarTodosLosEquipos;
    private final BuscarEquipoPorNumeroSerie buscarEquipoPorNumeroSerie;

    public EquipoController(RegistrarEquipo registrarEquipo, BuscarEquipoPorId buscarEquipoPorId, EliminarEquipoPorId eliminarEquipoPorId, ListarTodosLosEquipos listarTodosLosEquipos, BuscarEquipoPorNumeroSerie buscarEquipoPorNumeroSerie) {
        this.registrarEquipo = registrarEquipo;
        this.buscarEquipoPorId = buscarEquipoPorId;
        this.eliminarEquipoPorId = eliminarEquipoPorId;
        this.listarTodosLosEquipos = listarTodosLosEquipos;
        this.buscarEquipoPorNumeroSerie = buscarEquipoPorNumeroSerie;
    }
    //post
    @PostMapping
    public ResponseEntity<EquipoResponse> registrar(@RequestBody RegistrarEquipoCommand command) {
//
        EquipoResponse response = registrarEquipo.registrarEquipo(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    //get
    @GetMapping
    public ResponseEntity<List<EquipoResponse>> listarTodos(){
        List<EquipoResponse> equipos = listarTodosLosEquipos.listarEquipos();
        return ResponseEntity.ok(equipos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EquipoResponse> buscarPorId(@PathVariable("id") Long id){
        EquipoResponse response = buscarEquipoPorId.buscarEquipoPorId(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/serie/{numeroSerie}")
    public ResponseEntity<EquipoResponse> buscarPorNumeroSerie(@PathVariable("numeroSerie") String numeroSerie){
        EquipoResponse response = buscarEquipoPorNumeroSerie.buscarEquipoPorNumeroSerie(numeroSerie);
        return ResponseEntity.ok(response);
    }
    //delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>  eliminar(@PathVariable("id") Long id){
        eliminarEquipoPorId.eliminarEquipo(id);
        return ResponseEntity.ok("Equipo eliminado");
    }
}
//podria agregar para ver el historial de reparaciones por equipo...?