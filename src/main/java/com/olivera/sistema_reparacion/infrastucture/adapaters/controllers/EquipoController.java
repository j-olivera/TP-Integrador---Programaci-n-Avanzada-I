package com.olivera.sistema_reparacion.infrastucture.adapaters.controllers;

import com.olivera.sistema_reparacion.application.dto.empleado.EmpleadoResponse;
import com.olivera.sistema_reparacion.application.dto.equipo.EquipoResponse;
import com.olivera.sistema_reparacion.application.dto.equipo.RegistrarEquipoCommand;
import com.olivera.sistema_reparacion.application.ports.in.equipo.BuscarEquipoPorId;
import com.olivera.sistema_reparacion.application.ports.in.equipo.RegistrarEquipo;
import com.olivera.sistema_reparacion.domain.entities.Equipo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {
    private final RegistrarEquipo registrarEquipo;

    public EquipoController(RegistrarEquipo registrarEquipo) {
        this.registrarEquipo = registrarEquipo;
    }

    @PostMapping
    public ResponseEntity<EquipoResponse> registrar(@RequestBody Equipo equipo) {
        RegistrarEquipoCommand command = new RegistrarEquipoCommand(
                equipo.getTipo(),
                equipo.getModelo(),
                equipo.getNumeroSerie(),
                equipo.getNombreCliente(),
                equipo.getNumeroCliente()
        );
        EquipoResponse response = registrarEquipo.registrarEquipo(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
