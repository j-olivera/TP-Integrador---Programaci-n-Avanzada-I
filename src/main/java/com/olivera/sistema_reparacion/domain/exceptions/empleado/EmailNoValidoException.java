package com.olivera.sistema_reparacion.domain.exceptions.empleado;

public class EmailNoValidoException extends RuntimeException {
    public EmailNoValidoException(String message) {
        super(message);
    }
}
