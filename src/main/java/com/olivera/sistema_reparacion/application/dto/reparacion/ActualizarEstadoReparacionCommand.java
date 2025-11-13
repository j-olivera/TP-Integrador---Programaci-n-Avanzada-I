package com.olivera.sistema_reparacion.application.dto.reparacion;

import com.olivera.sistema_reparacion.domain.enums.Estado;
import com.olivera.sistema_reparacion.domain.exceptions.DatosNoValidosException;

import java.time.LocalDate;

public class ActualizarEstadoReparacionCommand {
    private Long id;
    private String descripcionProblema;
    private String diagnostico;
    private Estado estado;
    private LocalDate fechaIngreso;
    private LocalDate fechaEntrega;
    private Double costo;

    public ActualizarEstadoReparacionCommand(Long id, String descripcionProblema, String diagnostico, Estado estado, LocalDate fechaIngreso, LocalDate fechaEntrega, Double costo) {
        this.id = id;
        this.descripcionProblema = descripcionProblema;
        this.diagnostico = diagnostico;
        this.estado = estado;
        this.fechaIngreso = fechaIngreso;
        this.fechaEntrega = fechaEntrega;
        this.costo = costo;
    }

    public void validar(){
        if(descripcionProblema==null || descripcionProblema.trim().isBlank()){
            throw new DatosNoValidosException("Descripción obligatoria");
        }
        if(diagnostico==null || diagnostico.trim().isBlank()){
            throw new DatosNoValidosException("Diagnostico obligatorio");
        }
        if(costo==null || costo<=0.0){
            throw new DatosNoValidosException("Costo invalido");
        }
        //formato
        if(diagnostico.length()<40){
            throw new DatosNoValidosException("El diagnostico debe tener 40 al menos characteres");
        }
        if(descripcionProblema.length()<40){
            throw new DatosNoValidosException("La descripcion debe tener al menos 40 caracteres");
        }

    }

    public Long getId() {
        return id;
    }

    public String getDescripcionProblema() {
        return descripcionProblema;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public Estado getEstado() {
        return estado;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public Double getCosto() {
        return costo;
    }
}
