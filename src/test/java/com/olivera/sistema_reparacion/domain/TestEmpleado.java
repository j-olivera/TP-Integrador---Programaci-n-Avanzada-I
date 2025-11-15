package com.olivera.sistema_reparacion.domain;

import com.olivera.sistema_reparacion.domain.entities.Empleado;
import com.olivera.sistema_reparacion.domain.exceptions.DatosNoValidosException;
import com.olivera.sistema_reparacion.domain.exceptions.empleado.EmailNoValidoException;
import jakarta.validation.constraints.Email;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestEmpleado {
    @Test
    void TestConstructor() {
        //
        String nombre = "Juan";
        String apellido = "Apellido";
        String especialidad = "Programador";
        String email = "juan@gmail.com";
        Empleado jn = Empleado.crearEmpleado(nombre, apellido, especialidad, email);
        //
        Assertions.assertNotNull(jn);
        Assertions.assertEquals(nombre, jn.getNombre());


    }
    @Test
    void TestValidarDatos(){
        //
        String nombre = "Juan";
        String apellido = "";
        String especialidad = "Programador";
        String email = "juan@gmail.com";
        //
        Assertions.assertThrows(DatosNoValidosException.class, ()->Empleado.crearEmpleado(nombre, apellido, especialidad, email));
    }
    @Test
    void TestValidarEmail(){
        String nombre = "Juan";
        String apellido = "Apellido";
        String especialidad = "Programador";
        String email = "juan.com";
        //
        Assertions.assertThrows(EmailNoValidoException.class, ()-> Empleado.crearEmpleado(nombre, apellido, especialidad, email));

    }
}
