package com.olivera.sistema_reparacion.infrastucture.adapaters.controllers;

import com.olivera.sistema_reparacion.application.dto.empleado.EmpleadoResponse;
import com.olivera.sistema_reparacion.application.dto.empleado.RegistrarEmpleadoCommand;
import com.olivera.sistema_reparacion.application.ports.in.empleado.BuscarEmpleadoPorId;
import com.olivera.sistema_reparacion.application.ports.in.empleado.ListarTodosLosEmpleados;
import com.olivera.sistema_reparacion.application.ports.in.empleado.RegistrarEmpleado;
import com.olivera.sistema_reparacion.domain.entities.Empleado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final RegistrarEmpleado registrarEmpleado;
    private final BuscarEmpleadoPorId buscarEmpleadoPorId;
    private final ListarTodosLosEmpleados listarTodosLosEmpleados;

    public EmpleadoController(RegistrarEmpleado registrarEmpleado,
                              BuscarEmpleadoPorId buscarEmpleadoPorId,
                              ListarTodosLosEmpleados listarTodosLosEmpleados) {
        this.registrarEmpleado = registrarEmpleado;
        this.buscarEmpleadoPorId = buscarEmpleadoPorId;
        this.listarTodosLosEmpleados = listarTodosLosEmpleados;
    }

    @PostMapping
    public ResponseEntity<EmpleadoResponse> registrar(@RequestBody Empleado request) {
        RegistrarEmpleadoCommand command = new RegistrarEmpleadoCommand(
                request.getNombre(),
                request.getApellido(),
                request.getEspecialidad(),
                request.getEmail()
        );

        EmpleadoResponse response = registrarEmpleado.saveEmpleado(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoResponse> buscarPorId(@PathVariable Long id) {
        EmpleadoResponse response = buscarEmpleadoPorId.buscarEmpleadoPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoResponse>> listarTodos() {
        List<EmpleadoResponse> response = listarTodosLosEmpleados.listarTodosLosEmpleados();
        return ResponseEntity.ok(response);
    }
}
//porfavor dios q ande xd