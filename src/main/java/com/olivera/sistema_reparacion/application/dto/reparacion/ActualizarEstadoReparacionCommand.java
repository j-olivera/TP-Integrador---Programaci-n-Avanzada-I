package com.olivera.sistema_reparacion.application.dto.reparacion;

import com.olivera.sistema_reparacion.domain.enums.Estado;
import com.olivera.sistema_reparacion.domain.exceptions.DatosNoValidosException;

import java.time.LocalDate;

public class ActualizarEstadoReparacionCommand {
    private Long id;
    private Estado estado;

    public ActualizarEstadoReparacionCommand(Long id, Estado estado) {
        this.id = id;
        this.estado = estado;
    }

    public void validar() {
        if(estado==null){
            throw new RuntimeException("El estado no puede ser nulo");
        }
    }

    public Long getId() {
        return id;
    }

    public Estado getEstado() {
        return estado;
    }
}
