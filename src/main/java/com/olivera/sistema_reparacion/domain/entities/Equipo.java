package com.olivera.sistema_reparacion.domain.entities;

import com.olivera.sistema_reparacion.domain.enums.ModeloEquipo;
import com.olivera.sistema_reparacion.domain.enums.TipoEquipo;
import com.olivera.sistema_reparacion.domain.exceptions.DatosNoValidosException;

public class Equipo {
    private Long id;
    private TipoEquipo tipo;
    private ModeloEquipo modelo;
    private String numeroSerie;
    private String nombreCliente;
    private String numeroCliente;

    public Equipo(Long id, TipoEquipo tipo, ModeloEquipo modelo, String numeroSerie, String nombreCliente, String numeroCliente) {
        this.id = id;
        this.tipo = tipo;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.nombreCliente = nombreCliente;
        this.numeroCliente = numeroCliente;
    }

    public static Equipo crearEquipo(TipoEquipo tipo, ModeloEquipo modelo, String numeroSerie, String nombreCliente, String numeroCliente){
        validarDatos(tipo,modelo,numeroSerie,nombreCliente,numeroCliente);
        return new Equipo(null,tipo, modelo, numeroSerie, nombreCliente, numeroCliente);
    }

    public static Equipo reconstituir(Long id, TipoEquipo tipo, ModeloEquipo modelo,
                                      String numeroSerie, String nombreCliente,
                                      String telefonoCliente) {
        return new Equipo(id, tipo, modelo, numeroSerie, nombreCliente, telefonoCliente);
    }

    private static void validarDatos(TipoEquipo tipo, ModeloEquipo modelo, String numeroSerie, String nombreCliente, String numeroCliente) {
        if(tipo==null || modelo==null || numeroSerie==null || nombreCliente==null || numeroCliente==null || nombreCliente.isBlank()){
            throw new DatosNoValidosException("Datos no validos.");
        }
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
