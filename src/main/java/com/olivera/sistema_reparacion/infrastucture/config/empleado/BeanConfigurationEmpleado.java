package com.olivera.sistema_reparacion.infrastucture.config.empleado;

import com.olivera.sistema_reparacion.application.ports.in.empleado.*;
import com.olivera.sistema_reparacion.application.ports.in.equipo.RegistrarEquipo;
import com.olivera.sistema_reparacion.application.ports.in.reparacion.BuscarReparacionPorId;
import com.olivera.sistema_reparacion.application.ports.in.reparacion.ListarTodasReparaciones;
import com.olivera.sistema_reparacion.application.ports.in.reparacion.RegistrarReparacion;
import com.olivera.sistema_reparacion.application.ports.out.EmpleadoRepositoryPort;
import com.olivera.sistema_reparacion.application.ports.out.EquipoRepositoryPort;
import com.olivera.sistema_reparacion.application.ports.out.ReparacionRepositoryPort;
import com.olivera.sistema_reparacion.application.usecases.empleado.*;
import com.olivera.sistema_reparacion.application.usecases.equipo.RegistrarEquipoImpl;
import com.olivera.sistema_reparacion.application.usecases.reparacion.BuscarReparacionPorIdImpl;
import com.olivera.sistema_reparacion.application.usecases.reparacion.ListarTodasReparacionImpl;
import com.olivera.sistema_reparacion.application.usecases.reparacion.RegistrarReparacionImpl;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.empleado.EmpleadoMapper;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.equipo.EquipoMapper;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.reparacion.ReparacionMapper;
import com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.equipo.EquipoJpaMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigurationEmpleado {
    //Mapper
    @Bean
    public EmpleadoMapper empleadoMapper() {
        return new EmpleadoMapper();
    }

    @Bean ReparacionMapper reparacionMapper() {
        return new ReparacionMapper();
    }
    @Bean
    public EquipoMapper equipoMapper() {
        return new EquipoMapper();
    }
    //Use Cases
    @Bean
    public RegistrarEmpleado registrarEmpleado(EmpleadoRepositoryPort empleadoRepository,
                                               EmpleadoMapper empleadoMapper) {
        return new RegistrarEmpleadoImpl(empleadoRepository, empleadoMapper);
    }

    @Bean
    public BuscarEmpleadoPorId buscarEmpleadoPorId(EmpleadoRepositoryPort empleadoRepository,
                                                   EmpleadoMapper empleadoMapper) {
        return new BuscarEmpleadoPorIdImpl(empleadoRepository, empleadoMapper);
    }

    @Bean
    public ListarTodosLosEmpleados listarTodosLosEmpleados(EmpleadoRepositoryPort empleadoRepository,
                                                           EmpleadoMapper empleadoMapper) {
        return new ListarTodosLosEmpleadosImpl(empleadoRepository, empleadoMapper);
    }

    @Bean
    public ActualizarInformacionEmpleado actualizarInformacionEmpleado(
            EmpleadoRepositoryPort empleadoRepository,
            EmpleadoMapper empleadoMapper) {
        return new ActualizarInformacionEmpleadoImpl(empleadoRepository, empleadoMapper);
    }

    @Bean
    public EliminarEmpleado eliminarEmpleado(EmpleadoRepositoryPort empleadoRepository) {
        return new EliminarEmpleadoImpl(empleadoRepository);
    }
    @Bean
    public VerReparacionesAsignadasPorIdImpl verReparacionesAsignadasPorId(EmpleadoRepositoryPort empleadoRepositoryPort, ReparacionRepositoryPort reparacionRepositoryPort, ReparacionMapper reparacionMapper) {
        return new VerReparacionesAsignadasPorIdImpl(empleadoRepositoryPort, reparacionRepositoryPort, reparacionMapper);
    }

    //REPARACION USE CASE
    @Bean
    public RegistrarReparacion registrarReparacionPorId(ReparacionRepositoryPort reparacionRepositoryPort, ReparacionMapper reparacionMapper) {
        return new RegistrarReparacionImpl(reparacionRepositoryPort, reparacionMapper);
    }
    @Bean
    public BuscarReparacionPorId buscarReparacionPorId(ReparacionRepositoryPort reparacionRepositoryPort, ReparacionMapper reparacionMapper) {
        return new BuscarReparacionPorIdImpl(reparacionRepositoryPort, reparacionMapper);
    }
    @Bean
    ListarTodasReparaciones listarTodasReparaciones(ReparacionRepositoryPort reparacionRepositoryPort,  ReparacionMapper reparacionMapper) {
        return new ListarTodasReparacionImpl(reparacionRepositoryPort, reparacionMapper);
    }
    //Equipo use case
    @Bean
    RegistrarEquipo registrarEquipo(EquipoRepositoryPort equipoRepositoryPort, EquipoMapper equipoMapper) {
        return new RegistrarEquipoImpl(equipoRepositoryPort, equipoMapper);
    }
}
