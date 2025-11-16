package com.olivera.sistema_reparacion.domain.exceptions.empleado;

public class EmailYaRegistradoException extends RuntimeException {
    public EmailYaRegistradoException(String message) {
        super(message);
    }
}
