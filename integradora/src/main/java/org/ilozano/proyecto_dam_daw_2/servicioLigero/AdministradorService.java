package org.ilozano.proyecto_dam_daw_2.servicioLigero;

import org.ilozano.proyecto_dam_daw_2.model.Administrador;
import org.ilozano.proyecto_dam_daw_2.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdministradorService {

    private final AdministradorRepository administradorRepository;

    @Autowired
    public AdministradorService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    public boolean autenticarAdministrador(String username, String password) {
        Optional<Administrador> administradorOptional = administradorRepository.findByUsername(username);
        if (administradorOptional.isPresent()) {
            Administrador administrador = administradorOptional.get();
            // Verificar que la contraseña introducida coincida con la contraseña almacenada en la base de datos
            return administrador.getPassword().equals(password);
        } else {
            return false; // No se encontró ningún administrador con el nombre de usuario proporcionado
        }
    }

    public List<String> obtenerNombresDeUsuarios() {
        // Obtener todos los administradores de la base de datos
        List<Administrador> administradores = administradorRepository.findAll();

        // Extraer los nombres de usuario de los administradores y devolver la lista
        return administradores.stream()
                .map(Administrador::getUsername)
                .collect(Collectors.toList());
    }
}
