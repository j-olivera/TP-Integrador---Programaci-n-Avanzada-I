package com.olivera.sistema_reparacion.application.ports.out;

import com.olivera.sistema_reparacion.domain.entities.Reparacion;
import com.olivera.sistema_reparacion.domain.enums.Estado;

import java.util.List;
import java.util.Optional;

public interface ReparacionRepository {
    Reparacion save(Reparacion reparacion);
    List<Reparacion> findAll();
    Optional<Reparacion> findById(Long id);
    void deleteById(Long id);
    List<Reparacion> findByEstado(Estado estado);
    boolean existsById(Long id);
    List<Reparacion> findByEmpleadoId(Long empleadoId);
}
