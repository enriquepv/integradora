package org.ilozano.proyecto_dam_daw_2.controller;

import org.ilozano.proyecto_dam_daw_2.model.Producto;
import org.ilozano.proyecto_dam_daw_2.servicioLigero.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoRest {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/listado")
    public List<Producto> obtenerProductos() {
        return productoService.obtenerTodosLosProductos();
    }

    @PostMapping("/añadir")
    public Producto añadirProducto(@RequestBody Producto nuevoProducto) {
        return productoService.guardarProducto(nuevoProducto);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarProducto(@PathVariable String id) {
        productoService.eliminarProducto(id);
    }
}
