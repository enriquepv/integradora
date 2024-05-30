package org.ilozano.proyecto_dam_daw_2.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "administradores")
@Entity
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idAdministrador;

    @NotBlank
    private String username;

    @NotBlank
    private String password;



}
