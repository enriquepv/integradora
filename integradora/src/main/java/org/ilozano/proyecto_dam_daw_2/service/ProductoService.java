package org.ilozano.proyecto_dam_daw_2.service;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.ilozano.proyecto_dam_daw_2.model.Producto;
import org.ilozano.proyecto_dam_daw_2.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    public Producto obtenerProductoPorId(String id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void eliminarProducto(String id) {
        productoRepository.deleteById(id);
    }

    public List<Producto> buscarPorCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    public List<Producto> buscarPorRangoPrecio(double min, double max) {
        return productoRepository.findByPrecioBetween(min, max);
    }
}
//    Coordina las operaciones entre el controlado y el repositorio

//    @Qualifier("documentoRepositoryImpl")
//    @Autowired
//    ProductoRepository repository;

//    void insertMany(List<Document> documents) {
//
//        repository.insertMany(documents);
//    }

//    public void deleteAll() {
//
//        repository.deleteAll();
//    }
//
//    void deleteOne(String codigo) {
//
//        repository.deleteOne(codigo);
//    }
//
//    List<Document> findAll() {
//            return null;
//    }
//
//    public void buscarProductoConFiltro(Bson filter) {
//    }
//
//    public List<Document> getProductos(Document document) {
//        return  repository.findAll();
//    }

