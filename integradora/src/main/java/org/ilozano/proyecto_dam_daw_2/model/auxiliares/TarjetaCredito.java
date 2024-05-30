package org.ilozano.proyecto_dam_daw_2.model.auxiliares;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TarjetaCredito {

    private Integer numero;
    private String cvv;
    private LocalDate fechaCaducidad;

}
