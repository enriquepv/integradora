package org.ilozano.proyecto_dam_daw_2.model.auxiliares;


import lombok.Getter;

@Getter
public class DarBajaUsuarioRequest {
    // Getters y Setters
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }
}
