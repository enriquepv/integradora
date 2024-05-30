package org.ilozano.proyecto_dam_daw_2.configuration;

import org.ilozano.proyecto_dam_daw_2.model.Producto;
import org.ilozano.proyecto_dam_daw_2.repository.ProductoRepository;
import org.ilozano.proyecto_dam_daw_2.servicioLigero.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Map;

@Configuration
public class InitDatabase {

    @Autowired
    private ProductoService productoService;

    @Bean
    CommandLineRunner initProductos() {
        return args -> {
            Producto toalla = new Producto();
            toalla.setDescripcion("Toalla de playa grande");
            toalla.setPrecio(20.0);
            toalla.setMateriales(Arrays.asList("Algodón"));
            toalla.setCategoria("playa");
            toalla.setDimensiones(Map.of("largo", "200cm", "ancho", "100cm"));
            productoService.guardarProducto(toalla);

            Producto flotador = new Producto();
            flotador.setDescripcion("Flotador de piscina");
            flotador.setPrecio(15.0);
            flotador.setMateriales(Arrays.asList("Plástico"));
            flotador.setCategoria("piscina");
            flotador.setDimensiones(Map.of("diámetro", "100cm"));
            productoService.guardarProducto(flotador);

            Producto sombrilla = new Producto();
            sombrilla.setDescripcion("Sombrilla de playa");
            sombrilla.setPrecio(30.0);
            sombrilla.setMateriales(Arrays.asList("Metal", "Poliéster"));
            sombrilla.setCategoria("playa");
            sombrilla.setDimensiones(Map.of("altura", "220cm", "diámetro", "180cm"));
            productoService.guardarProducto(sombrilla);

            Producto gafasDeSol = new Producto();
            gafasDeSol.setDescripcion("Gafas de sol polarizadas");
            gafasDeSol.setPrecio(25.0);
            gafasDeSol.setMateriales(Arrays.asList("Plástico", "Cristal"));
            gafasDeSol.setCategoria("accesorios");
            gafasDeSol.setDimensiones(Map.of("anchoLente", "5.5cm", "altoLente", "4.5cm"));
            productoService.guardarProducto(gafasDeSol);

            Producto chanclas = new Producto();
            chanclas.setDescripcion("Chanclas de playa");
            chanclas.setPrecio(10.0);
            chanclas.setMateriales(Arrays.asList("Goma"));
            chanclas.setCategoria("playa");
            chanclas.setDimensiones(Map.of("talla", "42"));
            productoService.guardarProducto(chanclas);
        };
    }
}
