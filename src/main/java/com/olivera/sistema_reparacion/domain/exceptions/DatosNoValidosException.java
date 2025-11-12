package com.olivera.sistema_reparacion.domain.exceptions;

public class DatosNoValidosException extends RuntimeException {
    public DatosNoValidosException(String message) {
        super(message);
    }
}
