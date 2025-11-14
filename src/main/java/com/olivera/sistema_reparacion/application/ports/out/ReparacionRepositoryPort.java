package com.olivera.sistema_reparacion.application.ports.out;

import com.olivera.sistema_reparacion.domain.entities.Reparacion;
import com.olivera.sistema_reparacion.domain.enums.Estado;

import java.util.List;
import java.util.Optional;

public interface ReparacionRepositoryPort {
    Reparacion save(Reparacion reparacion);
    List<Reparacion> findAll();
    Optional<Reparacion> findById(Long id);
    void deleteById(Long id);
    boolean existsById(Long id);
    List<Reparacion> findByEstado(Estado estado);
    List<Reparacion> findByEmpleadoId(Long empleadoId);
    List<Reparacion> findByEquipoId(Long equipoId);
    void eliminarReparacionPorId(Long id);
}
