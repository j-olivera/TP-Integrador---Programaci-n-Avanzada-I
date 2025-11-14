package com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.empleado;

import com.olivera.sistema_reparacion.domain.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface EmpleadoJpaRepository extends JpaRepository<EmpleadoEntity, Long> {
    Optional<EmpleadoEntity> findByEmail(String email);//por las dudas
}
