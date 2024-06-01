package org.ilozano.proyecto_dam_daw_2.repository;

import org.ilozano.proyecto_dam_daw_2.model.auxiliares.enums.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {
    // Puedes agregar métodos personalizados si necesitas consultas específicas
}
