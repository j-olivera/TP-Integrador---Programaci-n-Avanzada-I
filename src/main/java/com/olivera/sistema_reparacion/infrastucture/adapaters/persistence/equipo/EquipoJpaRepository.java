package com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.equipo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EquipoJpaRepository extends JpaRepository<EquipoEntity, Long> {
    Optional<EquipoEntity> findByNumeroSerie(String numeroSerie);
}
