package com.olivera.sistema_reparacion.application.dto.equipo;

import com.olivera.sistema_reparacion.domain.enums.ModeloEquipo;
import com.olivera.sistema_reparacion.domain.enums.TipoEquipo;

public class EquipoResponse {
    private Long id;
    private TipoEquipo tipo;
    private ModeloEquipo modelo;
    private String numeroSerie;
    private String nombreCliente;
    private String numeroCliente;

    public EquipoResponse(Long id, TipoEquipo tipo, ModeloEquipo modelo, String numeroSerie, String nombreCliente, String numeroCliente) {
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

    public TipoEquipo getTipo() {
        return tipo;
    }

    public ModeloEquipo getModelo() {
        return modelo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getNumeroCliente() {
        return numeroCliente;
    }
}
