package com.olivera.sistema_reparacion.application.ports.out;

import com.olivera.sistema_reparacion.domain.entities.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoRepository {
    //funciones genericas
    Empleado save(Empleado empleado);
    Optional<Empleado> findById(Long id);
    Optional<Empleado> findByEmail(String email);
    List<Empleado> findAll();
    void deleteById(Long id);
    boolean existeById(Long id);
    boolean findByEspecialidad(String especialidad);
}
