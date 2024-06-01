package org.ilozano.proyecto_dam_daw_2.servicioLigero;

import org.ilozano.proyecto_dam_daw_2.model.auxiliares.enums.TipoDocumento;
import org.ilozano.proyecto_dam_daw_2.repository.TipoDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoDocumentoService {

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    public List<TipoDocumento> obtenerTodosLosTiposDocumento() {
        return tipoDocumentoRepository.findAll();
    }

    public TipoDocumento obtenerTipoDocumentoPorId(Long id) {
        Optional<TipoDocumento> optionalTipoDocumento = tipoDocumentoRepository.findById(id);
        return optionalTipoDocumento.orElse(null); // Retorna el tipo de documento si existe, o null si no se encuentra
    }
}

