package com.olivera.sistema_reparacion.application.dto.equipo;

import com.olivera.sistema_reparacion.domain.enums.ModeloEquipo;
import com.olivera.sistema_reparacion.domain.enums.tipoEquipo;

public class EquipoResponse {
    private Long id;
    private tipoEquipo tipo;
    private ModeloEquipo modelo;
    private Integer numeroSerie;
    private String nombreCliente;
    private Integer numeroCliente;

    public EquipoResponse(Long id, tipoEquipo tipo, ModeloEquipo modelo, Integer numeroSerie, String nombreCliente, Integer numeroCliente) {
        this.id = id;
        this.tipo = tipo;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.nombreCliente = nombreCliente;
        this.numeroCliente = numeroCliente;
    }

    public Long getId() {
        return id;
    }

    public tipoEquipo getTipo() {
        return tipo;
    }

    public ModeloEquipo getModelo() {
        return modelo;
    }

    public Integer getNumeroSerie() {
        return numeroSerie;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public Integer getNumeroCliente() {
        return numeroCliente;
    }
}
