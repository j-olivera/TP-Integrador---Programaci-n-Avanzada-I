package com.olivera.sistema_reparacion.domain.exceptions.reparacion;

public class ReparacionNoEncontradaException extends RuntimeException {
    public ReparacionNoEncontradaException(String message) {
        super(message);
    }
}
