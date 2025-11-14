package com.olivera.sistema_reparacion.application.dto.reparacion;

import com.olivera.sistema_reparacion.domain.enums.Estado;
import com.olivera.sistema_reparacion.domain.exceptions.DatosNoValidosException;

import java.time.LocalDate;

public class RegistrarReparacionCommand {
    private String descripcionProblema;
    private String diagnostico;
    private Estado estado;
    private LocalDate fechaIngreso;
    private LocalDate fechaEntrega;
    private Double costo;
    private Long empleadoId;
    private Long equipoId;

    public RegistrarReparacionCommand(String descripcionProblema, String diagnostico, Estado estado, LocalDate fechaIngreso, LocalDate fechaEntrega, Double costo, Long empleadoId, Long equipoId) {
        this.descripcionProblema = descripcionProblema;
        this.diagnostico = diagnostico;
        this.estado = estado;
        this.fechaIngreso = fechaIngreso;
        this.fechaEntrega = fechaEntrega;
        this.costo = costo;
        this.empleadoId = empleadoId;
        this.equipoId = equipoId;
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
        if(diagnostico.length()<20){
            throw new DatosNoValidosException("El diagnostico debe tener 20 al menos characteres");
        }
        if(descripcionProblema.length()<20){
            throw new DatosNoValidosException("La descripcion debe tener al menos 20 caracteres");
        }

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

    public Long getEmpleadoId() {
        return empleadoId;
    }

    public Long getEquipoId() {
        return equipoId;
    }
}
