package com.olivera.sistema_reparacion.infrastucture.config.empleado;

import com.olivera.sistema_reparacion.application.ports.in.empleado.*;
import com.olivera.sistema_reparacion.application.ports.in.equipo.*;
import com.olivera.sistema_reparacion.application.ports.in.reparacion.*;
import com.olivera.sistema_reparacion.application.ports.out.EmpleadoRepositoryPort;
import com.olivera.sistema_reparacion.application.ports.out.EquipoRepositoryPort;
import com.olivera.sistema_reparacion.application.ports.out.ReparacionRepositoryPort;
import com.olivera.sistema_reparacion.application.usecases.empleado.*;
import com.olivera.sistema_reparacion.application.usecases.equipo.*;
import com.olivera.sistema_reparacion.application.usecases.reparacion.*;
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
    //use case empleado
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
    @Bean
    public VerificarSiExisteEmpleadoPorId verificarSiExisteEmpleadoPorId(EmpleadoRepositoryPort empleadoRepositoryPort) {
        return new VerificarSiExisteEmpleadoImpl(empleadoRepositoryPort);
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
    public ListarTodasReparaciones listarTodasReparaciones(ReparacionRepositoryPort reparacionRepositoryPort,  ReparacionMapper reparacionMapper) {
        return new ListarTodasReparacionImpl(reparacionRepositoryPort, reparacionMapper);
    }
    //buscar por empleado
    @Bean
    public BuscarReparacionPorEmpleado buscarReparacionPorEmpleado(ReparacionRepositoryPort reparacionRepositoryPort, ReparacionMapper reparacionMapper) {
        return new BuscarReparacionPorEmpleadoImpl(reparacionRepositoryPort, reparacionMapper);
    }
    //actualizar estado reparacion
    @Bean
    public ActualizarEstadoReparacion actualizarEstadoReparacion(ReparacionRepositoryPort reparacionRepositoryPort,ReparacionMapper reparacionMapper) {
        return new ActualizarEstadoReparacionImpl(reparacionMapper, reparacionRepositoryPort);
    }
    //buscar reparacion por estado
    public BuscarReparacionPorEstado buscarReparacionPorEstado(ReparacionRepositoryPort reparacionRepositoryPort, ReparacionMapper reparacionMapper) {
        return new BuscarReparacionPorEstadoImpl(reparacionRepositoryPort, reparacionMapper);
    }
    //eliminar
    public EliminarReparacionPorId eliminarReparacionPorId(ReparacionRepositoryPort reparacionRepositoryPort) {
        return new EliminarReparacionPorIdImpl(reparacionRepositoryPort);
    }
    //Equipo use case
    @Bean
    RegistrarEquipo registrarEquipo(EquipoRepositoryPort equipoRepositoryPort, EquipoMapper equipoMapper) {
        return new RegistrarEquipoImpl(equipoRepositoryPort, equipoMapper);
    }
    @Bean
    BuscarEquipoPorId buscarEquipoPorId(EquipoRepositoryPort equipoRepositoryPort, EquipoMapper equipoMapper) {
        return new BuscarEquipoPorIdImpl(equipoRepositoryPort, equipoMapper);
    }
    @Bean
    BuscarEquipoPorNumeroSerie buscarEquipoPorNumeroSerie(EquipoRepositoryPort equipoRepositoryPort, EquipoMapper equipoMapper) {
        return new BuscarEquipoPorNumeroSerieImpl(equipoRepositoryPort, equipoMapper);
    }
    @Bean
    EliminarEquipoPorId eliminarEquipoPorId(EquipoRepositoryPort equipoRepositoryPort) {
        return new EliminarEquipoPorIdImpl(equipoRepositoryPort);
    }
    @Bean
    EquipoExistePorId equipoExistePorId(EquipoRepositoryPort equipoRepositoryPort) {
        return new EquipoExistePorIdImpl(equipoRepositoryPort);
    }
    @Bean
    ListarTodosLosEquipos listarTodasLosEquipos(EquipoRepositoryPort equipoRepositoryPort,  EquipoMapper equipoMapper) {
        return new ListarTodosLosEquiposImpl(equipoRepositoryPort, equipoMapper);
    }

}
