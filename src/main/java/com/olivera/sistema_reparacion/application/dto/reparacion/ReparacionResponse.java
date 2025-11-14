package com.olivera.sistema_reparacion.application.dto.reparacion;

import com.olivera.sistema_reparacion.domain.enums.Estado;

import java.time.LocalDate;

public class ReparacionResponse {
    private Long id;
    private String descripcionProblema;
    private String diagnostico;
    private Estado estado;
    private LocalDate fechaIngreso;
    private LocalDate fechaEntrega;
    private Double costo;
    private Long empleadoId;
    private Long equipoId;

    public ReparacionResponse(Long id, String descripcionProblema, String diagnostico, Estado estado, LocalDate fechaIngreso, LocalDate fechaEntrega, Double costo, Long empleadoId, Long equipoId) {
        this.id = id;
        this.descripcionProblema = descripcionProblema;
        this.diagnostico = diagnostico;
        this.estado = estado;
        this.fechaIngreso = fechaIngreso;
        this.fechaEntrega = fechaEntrega;
        this.costo = costo;
        this.empleadoId = empleadoId;
        this.equipoId = equipoId;
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

    public Long getEmpleadoId() {
        return empleadoId;
    }

    public Long getEquipoId() {
        return equipoId;
    }
}
