package org.ilozano.proyecto_dam_daw_2.component;

import org.ilozano.proyecto_dam_daw_2.model.Administrador;
import org.ilozano.proyecto_dam_daw_2.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
@Component
public class DataInitializer implements CommandLineRunner {

    private final AdministradorRepository administradorRepository;

    @Autowired
    public DataInitializer(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    @Override
    public void run(String... args) {
        // Verificar si ya existen administradores en la base de datos
        if (administradorRepository.count() == 0) {
            // Crear los administradores predeterminados solo si no existen
            Administrador admin1 = new Administrador();
            admin1.setUsername("admin1");
            admin1.setPassword("admin1");

            Administrador admin2 = new Administrador();
            admin2.setUsername("admin2");
            admin2.setPassword("admin2");

            Administrador admin3 = new Administrador();
            admin3.setUsername("admin3");
            admin3.setPassword("admin3");

            Administrador admin4 = new Administrador();
            admin4.setUsername("admin4");
            admin4.setPassword("admin4");

            // Guardar los administradores en la base de datos
            administradorRepository.saveAll(Arrays.asList(admin1, admin2, admin3, admin4));
        }
    }
}
