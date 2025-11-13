package com.olivera.sistema_reparacion.application.ports.out;

import com.olivera.sistema_reparacion.domain.entities.Empleado;

import java.util.List;

public interface EmpleadoRepository {
    //funciones genericas
    Empleado save(Empleado empleado);
    Empleado findById(Long id);
    List<Empleado> findAll();
    void deleteById(Long id);
    boolean existeById(Long id);
    boolean findByEspecialidad(String especialidad);
}
