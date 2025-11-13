package com.olivera.sistema_reparacion.domain.entities;

import com.olivera.sistema_reparacion.domain.enums.ModeloEquipo;
import com.olivera.sistema_reparacion.domain.enums.tipoEquipo;
import com.olivera.sistema_reparacion.domain.exceptions.DatosNoValidosException;
import com.olivera.sistema_reparacion.domain.exceptions.equipo.NumerosNoValidosExceptions;

public class Equipo {
    private Long id;
    private tipoEquipo tipo;
    private ModeloEquipo modelo;
    private Integer numeroSerie;
    private String nombreCliente;
    private Integer numeroCliente;

    public Equipo(Long id, tipoEquipo tipo, ModeloEquipo modelo, Integer numeroSerie, String nombreCliente, Integer numeroCliente) {
        this.id = id;
        this.tipo = tipo;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.nombreCliente = nombreCliente;
        this.numeroCliente = numeroCliente;
    }

    public Equipo crearEquipo(Long id, tipoEquipo tipo, ModeloEquipo modelo, Integer numeroSerie, String nombreCliente, Integer numeroCliente){
        validarDatos(id,tipo,modelo,numeroSerie,nombreCliente,numeroCliente);
        validarEnteros(numeroSerie,numeroCliente);
        return new Equipo(id, tipo, modelo, numeroSerie, nombreCliente, numeroCliente);
    }

    private void validarEnteros(Integer numeroSerie, Integer numeroCliente) {
        if (numeroSerie <= 0 || numeroCliente <= 0 ) throw new NumerosNoValidosExceptions("El numero no es valido");
    }

    private void validarDatos(Long id, tipoEquipo tipo, ModeloEquipo modelo, Integer numeroSerie, String nombreCliente, Integer numeroCliente) {
        if(id==null || tipo==null || modelo==null || numeroSerie==null || nombreCliente==null || numeroCliente==null || nombreCliente.isBlank()){
            throw new DatosNoValidosException("Datos no validos.");
        }
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
