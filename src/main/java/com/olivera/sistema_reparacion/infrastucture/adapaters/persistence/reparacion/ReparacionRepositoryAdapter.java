package com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.reparacion;

import com.olivera.sistema_reparacion.application.ports.out.ReparacionRepositoryPort;
import com.olivera.sistema_reparacion.domain.entities.Reparacion;
import com.olivera.sistema_reparacion.domain.enums.Estado;
import com.olivera.sistema_reparacion.domain.exceptions.empleado.EmpleadoNoEncontradoException;
import com.olivera.sistema_reparacion.domain.exceptions.equipo.EquipoNoEncontradoException;
import com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.empleado.EmpleadoEntity;
import com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.empleado.EmpleadoJpaRepository;
import com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.equipo.EquipoEntity;
import com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.equipo.EquipoJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ReparacionRepositoryAdapter implements ReparacionRepositoryPort {
    private final ReparacionJpaRepository reparacionJpaRepository;
    private final EmpleadoJpaRepository empleadoJpaRepository;//r
    private final EquipoJpaRepository equipoJpaRepository;//r
    private final ReparacionJpaMapper reparacionJpaMapper;

    public ReparacionRepositoryAdapter(ReparacionJpaRepository reparacionJpaRepository, EmpleadoJpaRepository empleadoJpaRepository, EquipoJpaRepository equipoJpaRepository, ReparacionJpaMapper reparacionJpaMapper) {
        this.reparacionJpaRepository = reparacionJpaRepository;
        this.empleadoJpaRepository = empleadoJpaRepository;
        this.equipoJpaRepository = equipoJpaRepository;
        this.reparacionJpaMapper = reparacionJpaMapper;
    }


    @Override
    public Reparacion save(Reparacion reparacion) {
        //ver si tiene empleado y equipo asignado ?¡?¡?¡?
        EmpleadoEntity empleado = empleadoJpaRepository.findById(reparacion.getEmpleadoId())
                .orElseThrow(() -> new EmpleadoNoEncontradoException("Empleado no encontrado"));
        EquipoEntity equipo = equipoJpaRepository.findById(reparacion.getEquipoId())
                .orElseThrow(()-> new EquipoNoEncontradoException("Equipo no encontrado"));


        ReparacionEntity entity = reparacionJpaMapper.toEntity(reparacion, empleado, equipo);
        ReparacionEntity entitySaved = reparacionJpaRepository.save(entity);

        return reparacionJpaMapper.toDomain(entitySaved); //debería andar
    }

    @Override
    public List<Reparacion> findAll() {
        return reparacionJpaRepository.findAll().stream()
                .map(reparacionJpaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Reparacion> findById(Long id) {
        return reparacionJpaRepository.findById(id).map(reparacionJpaMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        reparacionJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return reparacionJpaRepository.existsById(id);
    }

    @Override
    public List<Reparacion> findByEstado(Estado estado) {
        return reparacionJpaRepository.findByEstado(estado).stream()
                .map(reparacionJpaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Reparacion> findByEmpleadoId(Long empleadoId) {
        return reparacionJpaRepository.findByEmpleadoId(empleadoId).stream()
                .map(reparacionJpaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Reparacion> findByEquipoId(Long equipoId) {
        return reparacionJpaRepository.findByEquipoId(equipoId).stream()
                .map(reparacionJpaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarReparacionPorId(Long id) {
        reparacionJpaRepository.deleteById(id);
    }

    @Override
    public Reparacion actualizarEstado(Long id, Estado estado) {
        ReparacionEntity nv = reparacionJpaRepository.findById(id).
                orElseThrow(()-> new RuntimeException("Reparacion no econtrada"));
        nv.setEstado(estado);
        return reparacionJpaMapper.toDomain(nv);
    }
}
//.map(mapper::toDomain) PARA TODO