package org.ilozano.proyecto_dam_daw_2.model.auxiliares;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BloqueoUsuarioRequest {
    private String email;
    private boolean bloqueado;
    private String motivoBloqueo;
    private Integer numeroUsuario;
    private String fechaDesbloqueo;
}
