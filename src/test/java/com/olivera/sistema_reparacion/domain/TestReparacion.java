package com.olivera.sistema_reparacion.domain;

import com.olivera.sistema_reparacion.domain.entities.Reparacion;
import com.olivera.sistema_reparacion.domain.enums.Estado;
import com.olivera.sistema_reparacion.domain.exceptions.DatosNoValidosException;
import com.olivera.sistema_reparacion.domain.exceptions.reparacion.LongitudNoValidaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TestReparacion {
    @Test
    public void TestConstructor(){
        String descripcionProblema = "La batería se descarga muy rápido y el botón de encendido falla.";
        String diagnostico = "Se requiere reemplazo de batería y reparación del circuito de encendido.";
        Estado estado = Estado.NO_INICIO;
        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFin = null;
        Double costo = 312.11;
        Long idEmpleado = 21L;
        Long idEquipo = 12L;
        //
        Reparacion rep = Reparacion.crearReparacion(descripcionProblema,diagnostico, estado, fechaInicio, fechaFin, costo, idEmpleado, idEquipo);
        //
        Assertions.assertNotNull(rep);
        Assertions.assertEquals(diagnostico, rep.getDiagnostico());
        //etc etc
    }

    @Test
    void TestValidarDatos(){
        String descripcionProblema = "La batería se descarga muy rápido y el botón de encendido falla.";
        String diagnostico = "Se requiere reemplazo de batería y reparación del circuito de encendido.";
        Estado estado = null;
        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFin = null;
        Double costo = 312.11;
        Long idEmpleado = 21L;
        Long idEquipo = 12L;
        //
        Assertions.assertThrows(DatosNoValidosException.class, ()-> Reparacion.crearReparacion(descripcionProblema,diagnostico, estado, fechaInicio, fechaFin, costo, idEmpleado, idEquipo));
    }
    @Test
    void ValidarDescripcion(){
        String descripcionProblema = "la bateria no anda";
        String diagnostico = "Se requiere reemplazo de batería y reparación del circuito de encendido.";
        Estado estado = Estado.NO_INICIO;
        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFin = null;
        Double costo = 312.11;
        Long idEmpleado = 21L;
        Long idEquipo = 12L;
        //
        Assertions.assertThrows(LongitudNoValidaException.class, ()-> Reparacion.crearReparacion(descripcionProblema,diagnostico, estado, fechaInicio, fechaFin, costo, idEmpleado, idEquipo));
    }
    @Test
    void TestDiagnoisticos(){
        String descripcionProblema = "La batería se descarga muy rápido y el botón de encendido falla.";
        String diagnostico = "reemplazo";
        Estado estado = Estado.NO_INICIO;
        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFin = null;
        Double costo = 312.11;
        Long idEmpleado = 21L;
        Long idEquipo = 12L;
        //
        Assertions.assertThrows(LongitudNoValidaException.class, ()-> Reparacion.crearReparacion(descripcionProblema,diagnostico, estado, fechaInicio, fechaFin, costo, idEmpleado, idEquipo));

    }

}
