package com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.empleado;

import com.olivera.sistema_reparacion.application.ports.out.EmpleadoRepositoryPort;
import com.olivera.sistema_reparacion.domain.entities.Empleado;
import com.olivera.sistema_reparacion.domain.entities.Reparacion;
import com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.reparacion.ReparacionJpaMapper;
import com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.reparacion.ReparacionJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Component
public class EmpleadoRepositoryAdapter implements EmpleadoRepositoryPort {
    private final ReparacionJpaRepository reparacionRepository;
    private final ReparacionJpaMapper reparacionMapper;
    private final EmpleadoJpaRepository jpaRepository;
    private final EmpleadoJpaMapper mapper;

    public EmpleadoRepositoryAdapter(ReparacionJpaRepository reparacionRepository, ReparacionJpaMapper reparacionMapper, EmpleadoJpaRepository jpaRepository,
                                     EmpleadoJpaMapper mapper) {
        this.reparacionRepository = reparacionRepository;
        this.reparacionMapper = reparacionMapper;
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Empleado save(Empleado empleado) {
        EmpleadoEntity entity = mapper.toEntity(empleado);
        EmpleadoEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Empleado> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Empleado> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(mapper::toDomain);
    }

    @Override
    public List<Empleado> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existeById(Long id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public List<Empleado> findByEspecialidad(String especialidad) {
        return jpaRepository.findAll().stream()
                .filter(e -> e.getEspecialidad().equalsIgnoreCase(especialidad))
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Reparacion> findReparacionesAsignadasPorId(Long id) {
        return reparacionRepository.findAll().stream()
                .filter(e-> e.getEmpleado().getId().equals(id))
                .map(reparacionMapper::toEntity)
                .collect(Collectors.toList());
    }


}
