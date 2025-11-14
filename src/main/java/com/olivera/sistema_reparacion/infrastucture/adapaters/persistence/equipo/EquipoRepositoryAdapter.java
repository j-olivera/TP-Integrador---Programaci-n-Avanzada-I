package com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.equipo;

import com.olivera.sistema_reparacion.application.ports.out.EquipoRepositoryPort;
import com.olivera.sistema_reparacion.domain.entities.Equipo;
import com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.reparacion.ReparacionJpaMapper;
import com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.reparacion.ReparacionJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Component
public class EquipoRepositoryAdapter implements EquipoRepositoryPort {

    private final EquipoJpaMapper equipoJpaMapper;
    private final EquipoJpaRepository equipoJpaRepository;
    private final ReparacionJpaMapper reparacionJpaMapper;
    private final ReparacionJpaRepository reparacionJpaRepository;

    public EquipoRepositoryAdapter(EquipoJpaMapper equipoJpaMapper, EquipoJpaRepository equipoJpaRepository, ReparacionJpaMapper reparacionJpaMapper, ReparacionJpaRepository reparacionJpaRepository) {
        this.equipoJpaMapper = equipoJpaMapper;
        this.equipoJpaRepository = equipoJpaRepository;
        this.reparacionJpaMapper = reparacionJpaMapper;
        this.reparacionJpaRepository = reparacionJpaRepository;
    }

    @Override
    public Equipo save(Equipo equipo) {
        EquipoEntity equipoEntity = equipoJpaMapper.toEntity(equipo);
        EquipoEntity saveEntity = equipoJpaRepository.save(equipoEntity);
        return equipoJpaMapper.toDomain(saveEntity);
    }

    @Override
    public Optional<Equipo> findById(Long id) {
        return equipoJpaRepository.findById(id)
                .map(equipoJpaMapper::toDomain);
    }

    @Override
    public Optional<Equipo> findByNumeroSerie(String numeroSerie) {
        return equipoJpaRepository.findByNumeroSerie(numeroSerie)
                .map(equipoJpaMapper::toDomain);
    }

    @Override
    public List<Equipo> findAll() {
        return equipoJpaRepository.findAll()
                .stream().map(equipoJpaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        equipoJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return equipoJpaRepository.existsById(id);
    }
}
