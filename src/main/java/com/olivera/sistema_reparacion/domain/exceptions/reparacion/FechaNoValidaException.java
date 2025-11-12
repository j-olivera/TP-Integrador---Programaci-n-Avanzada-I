package com.olivera.sistema_reparacion.domain.exceptions.reparacion;

public class FechaNoValidaException extends RuntimeException {
    public FechaNoValidaException(String message) {
        super(message);
    }
}
