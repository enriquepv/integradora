package org.ilozano.proyecto_dam_daw_2.model;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import jakarta.persistence.*;
import org.ilozano.proyecto_dam_daw_2.model.auxiliares.Auditoria;
import org.ilozano.proyecto_dam_daw_2.validaciones.login.ValidacionLogin1;
import org.ilozano.proyecto_dam_daw_2.validaciones.login.ValidacionLogin2;
import org.ilozano.proyecto_dam_daw_2.validaciones.registro.ValidacionRegistro;
import org.ilozano.proyecto_dam_daw_2.validations.userLoginValidations.CuentaUsuario;
import org.ilozano.proyecto_dam_daw_2.validations.userLoginValidations.ExisteUsuario;
import org.ilozano.proyecto_dam_daw_2.validations.registerValidations.MismaClave;
import org.ilozano.proyecto_dam_daw_2.validations.registerValidations.UsuarioRegistrado;

import java.time.LocalDate;
import java.util.UUID;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
@MismaClave(groups = ValidacionRegistro.class)
@CuentaUsuario(groups = ValidacionLogin2.class)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idUsuario; //Para que la UUID no de error hay que poner GenerationType.UUID.

    @NotBlank(groups = ValidacionRegistro.class)
    @UsuarioRegistrado(groups = ValidacionRegistro.class)
    @ExisteUsuario(groups = ValidacionLogin1.class)
    private String email;


    @NotBlank(groups = ValidacionRegistro.class)
    private String clave;

    @Transient
    private String confirmarClave;

    @Embedded
    private Auditoria auditoria;

    @NotBlank(groups = ValidacionRegistro.class)
    private String preguntaRecuperacion;

    @NotBlank(groups = ValidacionRegistro.class)
    private String respuestaRecuperacion;

    private LocalDate fechaUltimaConexion;

    private LocalDate fechaDesbloqueo;

    private String motivoBloqueo;

    private boolean bloqueado;

    private boolean bajaLogica;

    @OneToOne
    private Cliente cliente;
}
