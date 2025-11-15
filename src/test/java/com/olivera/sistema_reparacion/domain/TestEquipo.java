package com.olivera.sistema_reparacion.domain;

import com.olivera.sistema_reparacion.domain.entities.Equipo;
import com.olivera.sistema_reparacion.domain.enums.ModeloEquipo;
import com.olivera.sistema_reparacion.domain.enums.TipoEquipo;
import com.olivera.sistema_reparacion.domain.exceptions.DatosNoValidosException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestEquipo {
    @Test
    void TestConstructor(){
        TipoEquipo tipoEquipo = TipoEquipo.NOTEBOOK;
        ModeloEquipo modeloEquipo = ModeloEquipo.ASUS;
        String numeroSerie = "AVFDE-123FDS-S";
        String nombreCliente = "Juan";
        String numeroCliente = "+12345678910234";
        //
        Equipo equipo = Equipo.crearEquipo(tipoEquipo, modeloEquipo, numeroSerie, nombreCliente, numeroCliente);
        //
        Assertions.assertNotNull(equipo);
        Assertions.assertEquals(tipoEquipo, equipo.getTipo());
        Assertions.assertEquals(modeloEquipo, equipo.getModelo());
        Assertions.assertEquals(numeroSerie, equipo.getNumeroSerie());
        Assertions.assertEquals(nombreCliente, equipo.getNombreCliente());
    }

    @Test
    void TestValidarDatos(){
        TipoEquipo tipoEquipo = TipoEquipo.NOTEBOOK;
        ModeloEquipo modeloEquipo = ModeloEquipo.ASUS;
        String numeroSerie = "";
        String nombreCliente = "Juan";
        String numeroCliente = "+12345678910234";
        //
        Assertions.assertThrows(DatosNoValidosException.class, ()->Equipo.crearEquipo(tipoEquipo, modeloEquipo, numeroSerie, nombreCliente, numeroCliente));
    }



}
