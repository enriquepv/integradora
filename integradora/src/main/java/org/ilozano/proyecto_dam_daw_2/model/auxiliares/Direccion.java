package org.ilozano.proyecto_dam_daw_2.model.auxiliares;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Direccion {
    @Column(name = "tipoVia_")
    private String tipoVia;

    @Column(name = "numero_")
    private Integer numero;

    @Column(name = "portal_")
    private String portal;

    @Column(name = "planta_")
    private String planta;

    @Column(name = "puerta")
    private String puerta;

    @Column(name = "localidad_")
    private String localidad;

    @Column(name = "region_")
    private String region;

    @Column(name = "codigoPostal_")
    private String codigoPostal;
}
