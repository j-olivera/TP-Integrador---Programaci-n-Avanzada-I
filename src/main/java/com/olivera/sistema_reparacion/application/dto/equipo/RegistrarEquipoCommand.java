package com.olivera.sistema_reparacion.application.dto.equipo;

import com.olivera.sistema_reparacion.domain.enums.ModeloEquipo;
import com.olivera.sistema_reparacion.domain.enums.tipoEquipo;
import com.olivera.sistema_reparacion.domain.exceptions.DatosNoValidosException;
import com.olivera.sistema_reparacion.domain.exceptions.equipo.NumerosNoValidosExceptions;

public class RegistrarEquipoCommand {
    private tipoEquipo tipo;
    private ModeloEquipo modelo;
    private String numeroSerie;
    private String nombreCliente;
    private String numeroCliente;

    public RegistrarEquipoCommand(tipoEquipo tipo, ModeloEquipo modelo, String numeroSerie, String nombreCliente, String numeroCliente) {
        this.tipo = tipo;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.nombreCliente = nombreCliente;
        this.numeroCliente = numeroCliente;
    }

    public void validar(){
        //validaciones de datos obligatorios
        if(tipo==null || modelo==null || numeroSerie==null || nombreCliente==null || numeroCliente==null){
            throw new DatosNoValidosException("Todos los datos son obligatorios");
        }
        //validaciones de formato
        if(numeroSerie.contains("^[A-Za-z0-9-]+$")){
            throw new NumerosNoValidosExceptions("El numero de serie solo puede tener letrras, numeros y guiones");
        }
        if(numeroSerie.length()<6){
            throw new NumerosNoValidosExceptions("El numero de serie debe tener al menos 5 caracteres");
        }
        if(nombreCliente.length()<3){
            throw new DatosNoValidosException("El nombre del cliente debe tener al menos 3 caracteres");
        }
        if(numeroCliente.length()!=15){
            throw new NumerosNoValidosExceptions("El telefono debe incluir codigo de area y no exceder los 15 caracteres");
        }

    }

    public tipoEquipo getTipo() {
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
