package org.ilozano.proyecto_dam_daw_2.model.auxiliares;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
public class Auditoria {
    private LocalDate fechaAltaEntidad;
    private String adminQueRealizaElAlta;
    private LocalDate fechaUltimaModificacionEntidad;
    private String adminQueRealizaUltimaModificacion;
    private LocalDate fechaBorradoEntidad;
    private String adminQueRealizaBorrado;
}
