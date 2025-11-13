package com.olivera.sistema_reparacion.application.dto.empleado;

import com.olivera.sistema_reparacion.domain.exceptions.DatosNoValidosException;
import com.olivera.sistema_reparacion.domain.exceptions.empleado.EmailNoValidoException;

public class RegistrarEmpleadoCommand {
        //no id, lo genera la bd
    private String nombre;
    private String apellido;
    private String especialidad;
    private String email;

    public RegistrarEmpleadoCommand(String nombre, String apellido, String especialidad, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.email = email;
    }

    //validaciones -> .validar()
    public void validar(){
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
        if (!email.contains("@") || !email.contains(".")) {
            throw new EmailNoValidoException("El formato del email es inválido");
        }
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
