package com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.equipo;

import com.olivera.sistema_reparacion.domain.entities.Equipo;
import org.springframework.stereotype.Component;

@Component
public class EquipoJpaMapper {
    //entidad a domino
    public EquipoEntity toEntity(Equipo equipo) {
        return new EquipoEntity(
                equipo.getId(),
                equipo.getTipo(),
                equipo.getModelo(),
                equipo.getNumeroSerie(),
                equipo.getNombreCliente(),
                equipo.getNumeroCliente());
    }
    public Equipo toDomain(EquipoEntity equipoEntity) {
        return new Equipo(
                equipoEntity.getId(),
                equipoEntity.getTipo(),
                equipoEntity.getModelo(),
                equipoEntity.getNumeroSerie(),
                equipoEntity.getNombreCliente(),
                equipoEntity.getNumeroCliente()
        );
    }
}
