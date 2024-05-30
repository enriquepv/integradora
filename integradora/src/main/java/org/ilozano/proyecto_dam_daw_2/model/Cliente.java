package org.ilozano.proyecto_dam_daw_2.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import org.ilozano.proyecto_dam_daw_2.model.auxiliares.Direccion;
import org.ilozano.proyecto_dam_daw_2.model.auxiliares.TarjetaCredito;
import org.ilozano.proyecto_dam_daw_2.model.auxiliares.enums.Genero;
import org.ilozano.proyecto_dam_daw_2.model.auxiliares.enums.TipoDocumento;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
@AttributeOverride(name = "idCliente", column = @Column(name = "VIN"))
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idCliente;

    @NotNull(message = "El género es obligatorio")
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;

    private String paisNacimiento; //Esto es una clase pais

    @NotNull(message = "El tipo de documento es obligatorio")
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumentoCliente;

    @NotNull(message = "El documento es obligatorio")
    @NotBlank(message = "El documento no puede estar en blanco")
    private String documento;

    @NotNull(message = "El teléfono móvil es obligatorio")
    @NotBlank(message = "El teléfono móvil no puede estar en blanco")
    private String telefonoMovil;

    @NotNull(message = "El nombre es obligatorio")
    @NotBlank(message = "El nombre no puede estar en blanco")
    private String nombre;

    @NotNull(message = "Los apellidos son obligatorios")
    @NotBlank(message = "Los apellidos no pueden estar en blanco")
    private String apellidos;
    private String comentarios;

    private boolean aceptaLicencia;

    @Embedded
    private Direccion direccion;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "tipoVia", column = @Column(name = "tipoVia_envio")),
            @AttributeOverride(name = "numero", column = @Column(name = "numero_envio")),
            @AttributeOverride(name = "portal", column = @Column(name = "portal_envio")),
            @AttributeOverride(name = "planta", column = @Column(name = "planta_envio")),
            @AttributeOverride(name = "puerta", column = @Column(name = "puerta_envio")),
            @AttributeOverride(name = "localidad", column = @Column(name = "localidad_evio")),
            @AttributeOverride(name = "region", column = @Column(name = "region_envio")),
            @AttributeOverride(name = "codigoPostal", column = @Column(name = "codigoPostal_envio"))
    })
    private Direccion direccionEnvio;

    private LocalDate fechaAlta;

    @Embedded
    private TarjetaCredito tarjetaCredito;



    @OneToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
}
