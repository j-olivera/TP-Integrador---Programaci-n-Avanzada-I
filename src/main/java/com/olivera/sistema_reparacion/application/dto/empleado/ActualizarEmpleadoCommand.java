package com.olivera.sistema_reparacion.application.dto.empleado;

import com.olivera.sistema_reparacion.domain.exceptions.DatosNoValidosException;

public class ActualizarEmpleadoCommand {
    private Long id;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String email;

    public ActualizarEmpleadoCommand(Long id, String nombre, String apellido, String especialidad, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.email = email;
    }
    public void validar() {
        if (id == null) {
            throw new DatosNoValidosException("El ID es requerido");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DatosNoValidosException("El nombre es requerido");
        }
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new DatosNoValidosException("El apellido es requerido");
        }
        if (especialidad == null || especialidad.trim().isEmpty()) {
            throw new DatosNoValidosException("La especialidad es requerida");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new DatosNoValidosException("El email es requerido");
        }
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getEmail() {
        return email;
    }
}
