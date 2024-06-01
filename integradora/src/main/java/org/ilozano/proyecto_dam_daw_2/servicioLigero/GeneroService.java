package org.ilozano.proyecto_dam_daw_2.servicioLigero;

import org.ilozano.proyecto_dam_daw_2.model.auxiliares.enums.Genero;
import org.ilozano.proyecto_dam_daw_2.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    public List<Genero> obtenerTodosLosGeneros() {
        return generoRepository.findAll();
    }

    public Genero obtenerGeneroPorId(Long id) {
        Optional<Genero> optionalGenero = generoRepository.findById(id);
        return optionalGenero.orElse(null); // Retorna el género si existe, o null si no se encuentra
    }
}


