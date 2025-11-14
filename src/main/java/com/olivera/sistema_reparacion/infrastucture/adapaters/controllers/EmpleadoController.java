package com.olivera.sistema_reparacion.infrastucture.adapaters.controllers;

import com.olivera.sistema_reparacion.application.dto.empleado.ActualizarEmpleadoCommand;
import com.olivera.sistema_reparacion.application.dto.empleado.EmpleadoResponse;
import com.olivera.sistema_reparacion.application.dto.empleado.RegistrarEmpleadoCommand;
import com.olivera.sistema_reparacion.application.dto.reparacion.ReparacionResponse;
import com.olivera.sistema_reparacion.application.ports.in.empleado.*;
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
    private final EliminarEmpleado eliminarEmpleado;
    private final VerificarSiExisteEmpleadoPorId verificarSiExisteEmpleadoPorId;
    private final VerReparacionesAsignadas verReparacionesAsignadas;
    private final ActualizarInformacionEmpleado actualizarInformacionEmpleado;

    public EmpleadoController(RegistrarEmpleado registrarEmpleado,
                              BuscarEmpleadoPorId buscarEmpleadoPorId,
                              ListarTodosLosEmpleados listarTodosLosEmpleados, EliminarEmpleado eliminarEmpleado, VerificarSiExisteEmpleadoPorId verificarSiExisteEmpleadoPorId, VerReparacionesAsignadas verReparacionesAsignadas, ActualizarInformacionEmpleado actualizarInformacionEmpleado) {
        this.registrarEmpleado = registrarEmpleado;
        this.buscarEmpleadoPorId = buscarEmpleadoPorId;
        this.listarTodosLosEmpleados = listarTodosLosEmpleados;
        this.eliminarEmpleado = eliminarEmpleado;
        this.verificarSiExisteEmpleadoPorId = verificarSiExisteEmpleadoPorId;
        this.verReparacionesAsignadas = verReparacionesAsignadas;
        this.actualizarInformacionEmpleado = actualizarInformacionEmpleado;
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
    public ResponseEntity<EmpleadoResponse> buscarPorId(@PathVariable("id") Long id) {
        EmpleadoResponse response = buscarEmpleadoPorId.buscarEmpleadoPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoResponse>> listarTodos() {
        List<EmpleadoResponse> response = listarTodosLosEmpleados.listarTodosLosEmpleados();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoResponse> actualizar(@PathVariable Long id, @RequestBody Empleado request){
        ActualizarEmpleadoCommand command = new ActualizarEmpleadoCommand(request.getId(),
                request.getNombre(),
                request.getApellido(),
                request.getEspecialidad(),
                request.getEmail()
        );
        EmpleadoResponse nuevo = actualizarInformacionEmpleado.actualizarEmpleado(command);
        return ResponseEntity.ok(nuevo);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id){
        eliminarEmpleado.eliminarEmpleado(id);
        return ResponseEntity.ok("Empleado de id "+id+" eliminado correctamente");
    }

    //relaciones prueba

    @GetMapping("/{id}/reparaciones")
    public ResponseEntity<List<ReparacionResponse>> verReparacionesPorId(@PathVariable("id") Long empleadoId){
        List<ReparacionResponse> reparaciones = verReparacionesAsignadas.reparacionesAsignadasPorId(empleadoId);
        return ResponseEntity.ok(reparaciones);
    }

}
