package com.olivera.sistema_reparacion.domain.enums;

public enum Estado {
    NO_INICIO,
    DIAGNOSTICO,
    EN_ESPERA, // <- en espera de piezas
    EN_PROCESO, //<- en arreglo
    LISTO
}
