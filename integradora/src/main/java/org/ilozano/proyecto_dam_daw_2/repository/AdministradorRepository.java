package org.ilozano.proyecto_dam_daw_2.repository;


import org.ilozano.proyecto_dam_daw_2.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    Optional<Administrador> findByUsername(String username);
}
