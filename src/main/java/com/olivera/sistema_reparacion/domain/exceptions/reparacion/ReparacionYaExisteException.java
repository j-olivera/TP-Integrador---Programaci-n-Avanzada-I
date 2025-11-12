package com.olivera.sistema_reparacion.domain.exceptions.reparacion;

public class ReparacionYaExisteException extends RuntimeException {
    public ReparacionYaExisteException(String message) {
        super(message);
    }
}
