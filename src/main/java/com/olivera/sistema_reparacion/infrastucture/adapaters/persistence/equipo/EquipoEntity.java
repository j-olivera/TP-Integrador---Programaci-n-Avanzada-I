package com.olivera.sistema_reparacion.infrastucture.adapaters.persistence.equipo;

import com.olivera.sistema_reparacion.domain.enums.ModeloEquipo;
import com.olivera.sistema_reparacion.domain.enums.TipoEquipo;
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
    private TipoEquipo tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ModeloEquipo modelo;

    @Column(nullable = false, unique = true)
    private String numeroSerie;

    @Column(nullable = false)
    private String nombreCliente;

    @Column(nullable = false)
    private String numeroCliente;

    @OneToMany(mappedBy = "equipo", fetch = FetchType.LAZY)
    private List<ReparacionEntity> reparaciones = new ArrayList<>();

    public EquipoEntity(Long id, TipoEquipo tipo, ModeloEquipo modelo, String numeroSerie, String nombreCliente, String numeroCliente) {
        this.id = id;
        this.tipo = tipo;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.nombreCliente = nombreCliente;
        this.numeroCliente = numeroCliente;
    }
    public EquipoEntity() {}

    public Long getId() {
        return id;
    }

    public TipoEquipo getTipo() {
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

    public List<ReparacionEntity> getReparaciones() {
        return reparaciones;
    }
}
