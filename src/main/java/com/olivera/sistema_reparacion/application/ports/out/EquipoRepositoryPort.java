package com.olivera.sistema_reparacion.application.ports.out;

import com.olivera.sistema_reparacion.domain.entities.Equipo;

import java.util.List;
import java.util.Optional;

public interface EquipoRepositoryPort {
    //
    Equipo save(Equipo equipo);
    Optional<Equipo> findById(Long id);
    Optional<Equipo> findByNumeroSerie(String numeroSerie);
    List<Equipo> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
//uso de optional por si no devuelve nada