package com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.reparacion;

import com.olivera.sistema_reparacion.application.dto.reparacion.ReparacionResponse;
import com.olivera.sistema_reparacion.domain.enums.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReparacionJpaRepository extends JpaRepository<ReparacionEntity, Long> {
    List<ReparacionEntity> findByEmpleadoId(Long empleadoId);
    List<ReparacionEntity> findByEquipoId(Long equipoId);
    List<ReparacionEntity> findByEstado(Estado estado);
}
