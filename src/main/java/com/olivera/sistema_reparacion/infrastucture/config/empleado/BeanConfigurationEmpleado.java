package com.olivera.sistema_reparacion.infrastucture.config.empleado;

import com.olivera.sistema_reparacion.application.ports.in.empleado.*;
import com.olivera.sistema_reparacion.application.ports.out.EmpleadoRepositoryPort;
import com.olivera.sistema_reparacion.application.ports.out.ReparacionRepositoryPort;
import com.olivera.sistema_reparacion.application.usecases.empleado.*;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.empleado.EmpleadoMapper;
import com.olivera.sistema_reparacion.infrastucture.adapaters.mappers.reparacion.ReparacionMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigurationEmpleado {
    // Mapper
    @Bean
    public EmpleadoMapper empleadoMapper() {
        return new EmpleadoMapper();
    }

    @Bean ReparacionMapper reparacionMapper() {
        return new ReparacionMapper();
    }
    // Use Cases
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
}
