package org.ilozano.proyecto_dam_daw_2.component;

import jakarta.transaction.Transactional;
import org.ilozano.proyecto_dam_daw_2.model.Administrador;
import org.ilozano.proyecto_dam_daw_2.model.auxiliares.enums.Genero;
import org.ilozano.proyecto_dam_daw_2.model.auxiliares.enums.TipoDocumento;
import org.ilozano.proyecto_dam_daw_2.repository.AdministradorRepository;
import org.ilozano.proyecto_dam_daw_2.repository.GeneroRepository;
import org.ilozano.proyecto_dam_daw_2.repository.TipoDocumentoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
@Component
public class DataInitializer implements CommandLineRunner {

    private final AdministradorRepository administradorRepository;
    private final GeneroRepository generoRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;

    public DataInitializer(AdministradorRepository administradorRepository,
                           GeneroRepository generoRepository,
                           TipoDocumentoRepository tipoDocumentoRepository) {
        this.administradorRepository = administradorRepository;
        this.generoRepository = generoRepository;
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
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

        // Inicializar datos estáticos
        inicializarGeneros();
        inicializarTiposDocumento();
    }

    @Transactional
    public void inicializarGeneros() {
        // Verificar si ya existen registros en la tabla de generos
        if (generoRepository.count() == 0) {
            Genero masculino = new Genero();
            masculino.setNombre("Masculino");
            generoRepository.save(masculino);

            Genero femenino = new Genero();
            femenino.setNombre("Femenino");
            generoRepository.save(femenino);

            Genero otro = new Genero();
            otro.setNombre("Otro");
            generoRepository.save(otro);
        }
    }

    @Transactional
    public void inicializarTiposDocumento() {
        // Verificar si ya existen registros en la tabla de tipos de documento
        if (tipoDocumentoRepository.count() == 0) {
            TipoDocumento dni = new TipoDocumento();
            dni.setNombre("DNI");
            tipoDocumentoRepository.save(dni);

            TipoDocumento nie = new TipoDocumento();
            nie.setNombre("NIE");
            tipoDocumentoRepository.save(nie);

            TipoDocumento pasaporte = new TipoDocumento();
            pasaporte.setNombre("Pasaporte");
            tipoDocumentoRepository.save(pasaporte);

            TipoDocumento docSeguridadSocial = new TipoDocumento();
            docSeguridadSocial.setNombre("DocSeguridadSocial");
            tipoDocumentoRepository.save(docSeguridadSocial);
        }
    }
}