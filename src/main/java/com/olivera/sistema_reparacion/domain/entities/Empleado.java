package com.olivera.sistema_reparacion.domain.entities;

import com.olivera.sistema_reparacion.domain.exceptions.DatosNoValidosException;
import com.olivera.sistema_reparacion.domain.exceptions.empleado.EmailNoValidoException;

public class Empleado {

    private Long id;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String email;

    private Empleado(Long id, String nombre, String apellido, String especialidad, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.email = email;
    }

    public Empleado crearEmpleado(Long id, String nombre, String apellido, String especialidad, String email){
        validarDatos(id,nombre,apellido,especialidad,email);
        validarEmail(email);
        return new Empleado(id, nombre, apellido, especialidad, email);
    }

    private void validarEmail(String email) {
        if(!email.contains("@") || !email.contains(".")) throw new EmailNoValidoException("El email es invalido");
    }

    private void validarDatos(Long id, String nombre, String apellido, String especialidad, String email) {
        if(id == null || nombre == null || apellido == null || especialidad == null || email == null)
            throw new DatosNoValidosException("Datos no validos");
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
