package com.olivera.sistema_reparacion.domain.entities;

import com.olivera.sistema_reparacion.domain.enums.Estado;
import com.olivera.sistema_reparacion.domain.exceptions.DatosNoValidosException;
import com.olivera.sistema_reparacion.domain.exceptions.reparacion.FechaNoValidaException;
import com.olivera.sistema_reparacion.domain.exceptions.reparacion.LongitudNoValidaException;

import java.time.LocalDate;

public class Reparacion {
    private Long id;
    private String descripcionProblema;
    private String diagnostico;
    private Estado estado;
    private LocalDate fechaIngreso;
    private LocalDate fechaEntrega;
    private Double costo;
    private Long empleadoId;
    private Long equipoId;

    public Reparacion(Long id, String descripcionProblema, String diagnostico, Estado estado, LocalDate fechaIngreso, LocalDate fechaEntrega, Double costo, Long empleadoId, Long equipoId) {
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

    public static Reparacion crearReparacion(String descripcionProblema, String diagnostico, Estado estado, LocalDate fechaIngreso, LocalDate fechaEntrega, Double costo,  Long empleadoId, Long equipoId) {
        validarDatos(descripcionProblema,diagnostico,estado,fechaIngreso,fechaEntrega,costo);
        validarFechaIngreso(fechaIngreso);
        validarDescripcion_Diagnostico(descripcionProblema,diagnostico);
        return new Reparacion(null,descripcionProblema,diagnostico, estado, fechaIngreso, null, costo,empleadoId,equipoId);
    }

    public static Reparacion reconstituir(Long id, String descripcionProblema,
                                          String diagnostico, Estado estado,
                                          LocalDate fechaIngreso, LocalDate fechaEntrega,
                                          Double costo, Long empleadoId, Long equipoId) {
        return new Reparacion(id, descripcionProblema, diagnostico, estado,
                fechaIngreso, fechaEntrega, costo, empleadoId, equipoId);
    }

    private static void validarDescripcion_Diagnostico(String descripcionProblema, String diagnostico) {
        //la descripcion no puede ser corta, tampoco muy larga, al igual que el diagnostico
        if(descripcionProblema.isBlank() || descripcionProblema.length()>200 || descripcionProblema.length()<40 || diagnostico.length()<40|| diagnostico.isBlank() || diagnostico.length()>200){
                throw new LongitudNoValidaException("Debe precisar información exacta");
        }
    }

    private static void validarDatos(String descripcionProblema, String diagnostico, Estado estado, LocalDate fechaIngreso, LocalDate fechaEntrega, Double costo) {
        if( descripcionProblema ==null || diagnostico==null || estado==null || fechaIngreso==null || costo==null){}
            throw new DatosNoValidosException("Datos no validos");
    }

    private static void validarFechaIngreso(LocalDate fechaIngreso) {
        if(fechaIngreso.isBefore(LocalDate.now())) throw new FechaNoValidaException("No se puede registrar una fecha antes que la actual");
    }

    public Long getEmpleadoId() {
        return empleadoId;
    }

    public Long getEquipoId() {
        return equipoId;
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

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
