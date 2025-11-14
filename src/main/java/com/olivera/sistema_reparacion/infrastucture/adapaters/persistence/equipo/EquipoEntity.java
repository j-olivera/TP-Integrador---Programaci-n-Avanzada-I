package com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.equipo;

import com.olivera.sistema_reparacion.domain.enums.ModeloEquipo;
import com.olivera.sistema_reparacion.domain.enums.tipoEquipo;
import com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.reparacion.ReparacionEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "equipos")
public class EquipoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private tipoEquipo tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ModeloEquipo modelo;

    @Column(nullable = false, unique = true)
    private String numeroSerie;

    @Column(nullable = false)
    private String nombreCliente;

    @Column(nullable = false)
    private String telefonoCliente;

    @OneToMany(mappedBy = "equipo", fetch = FetchType.LAZY)
    private List<ReparacionEntity> reparaciones = new ArrayList<>();

    public EquipoEntity(Long id, tipoEquipo tipo, ModeloEquipo modelo, String numeroSerie, String nombreCliente, String telefonoCliente, List<ReparacionEntity> reparaciones) {
        this.id = id;
        this.tipo = tipo;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.nombreCliente = nombreCliente;
        this.telefonoCliente = telefonoCliente;
        this.reparaciones = reparaciones;
    }
    public EquipoEntity() {}

    public Long getId() {
        return id;
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

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public List<ReparacionEntity> getReparaciones() {
        return reparaciones;
    }
}
