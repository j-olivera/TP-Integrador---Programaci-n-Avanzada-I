package com.olivera.sistema_reparacion.domain.exceptions.empleado;

public class EmpleadoNoEncontradoException extends RuntimeException {
    public EmpleadoNoEncontradoException(String message) {
        super(message);
    }
}
