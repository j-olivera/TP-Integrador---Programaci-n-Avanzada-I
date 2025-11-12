package com.olivera.sistema_reparacion.domain.exceptions.empleado;

public class EmpleadoYaExisteException extends RuntimeException {
    public EmpleadoYaExisteException(String message) {
        super(message);
    }
}
