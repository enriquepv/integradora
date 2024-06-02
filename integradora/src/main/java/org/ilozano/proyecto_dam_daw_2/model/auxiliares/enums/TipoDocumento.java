package org.ilozano.proyecto_dam_daw_2.model.auxiliares.enums;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tipo_documento")
public class TipoDocumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Override
    public String toString() {
        return this.nombre;
    }
    // Getters y setters
}
