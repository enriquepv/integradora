package org.ilozano.proyecto_dam_daw_2.repository;

import org.ilozano.proyecto_dam_daw_2.model.auxiliares.enums.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
    // Puedes agregar métodos personalizados si necesitas consultas específicas
}
