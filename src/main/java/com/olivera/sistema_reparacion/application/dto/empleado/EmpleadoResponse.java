package com.olivera.sistema_reparacion.application.dto.empleado;

public class EmpleadoResponse { //esto es el output
    private Long id;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String email;

    public EmpleadoResponse(Long id, String nombre, String apellido, String especialidad, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.email = email;
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

