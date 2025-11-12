package com.olivera.sistema_reparacion.domain.exceptions.equipo;

public class EquipoYaExisteException extends RuntimeException {
    public EquipoYaExisteException(String message) {
        super(message);
    }
}
