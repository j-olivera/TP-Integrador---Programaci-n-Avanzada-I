package com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.reparacion;

import com.olivera.sistema_reparacion.domain.enums.Estado;
import com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.empleado.EmpleadoEntity;
import com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.equipo.EquipoEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

    @Entity
    @Table(name = "reparaciones")
    public class ReparacionEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(nullable = false)
        private String descripcionProblema;
        @Column(nullable = false)
        private String diagnostico;
        @Enumerated(EnumType.STRING) //esto no entendia asi que aqui entro la ia
        @Column(nullable = false)
        private Estado estado;
        @Column(nullable = false)
        private LocalDate fechaIngreso;
        @Column()
        private LocalDate fechaEntrega;
        @Column(nullable = false)
        private Double costo;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "empleado_id", nullable = false)
        private EmpleadoEntity empleado;
        //fechtype.lazy para optimización en la carga de bd
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "equipo_id", nullable = false)
        private EquipoEntity equipo;

        public ReparacionEntity(Long id, String descripcionProblema, String diagnostico, Estado estado, LocalDate fechaIngreso, LocalDate fechaEntrega, Double costo, EmpleadoEntity empleado, EquipoEntity equipo) {
            this.id = id;
            this.descripcionProblema = descripcionProblema;
            this.diagnostico = diagnostico;
            this.estado = estado;
            this.fechaIngreso = fechaIngreso;
            this.fechaEntrega = fechaEntrega;
            this.costo = costo;
            this.empleado = empleado;
            this.equipo = equipo;
        }

        public ReparacionEntity() {}

        public Long getId() {
            return id;
        }

        public String getDescripcionProblema() {
            return descripcionProblema;
        }

        public String getDiagnostico() {
            return diagnostico;
        }

        public Estado getEstado() {
            return estado;
        }

        public LocalDate getFechaIngreso() {
            return fechaIngreso;
        }

        public LocalDate getFechaEntrega() {
            return fechaEntrega;
        }

        public Double getCosto() {
            return costo;
        }

        public EmpleadoEntity getEmpleado() {
            return empleado;
        }

        public EquipoEntity getEquipo() {
            return equipo;
        }

        public void setEmpleado(EmpleadoEntity empleado) {
            this.empleado = empleado;
        }
        public void setEquipo(EquipoEntity equipo) {
            this.equipo = equipo;
        }

        public void setEstado(Estado estado) {
            this.estado = estado;
        }
    }


