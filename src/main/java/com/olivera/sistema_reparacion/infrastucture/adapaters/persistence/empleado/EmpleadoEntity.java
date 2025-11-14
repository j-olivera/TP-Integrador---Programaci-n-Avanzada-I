package com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.empleado;

import com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.reparacion.ReparacionEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "empleados")
public class EmpleadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String especialidad;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "empleado", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReparacionEntity> reparaciones = new ArrayList<>();

    public EmpleadoEntity() {
    }

    public EmpleadoEntity(Long id, String nombre, String apellido,
                          String especialidad, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getEmail() {
        return email;
    }

    public List<ReparacionEntity> getReparaciones() {
        return reparaciones;
    }
}