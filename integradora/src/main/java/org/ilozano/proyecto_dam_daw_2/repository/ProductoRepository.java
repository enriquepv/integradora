package org.ilozano.proyecto_dam_daw_2.repository;


import java.util.List;
import org.bson.Document;
import org.ilozano.proyecto_dam_daw_2.model.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoRepository extends MongoRepository<Producto, String> {

    List<Producto> findByCategoria(String categoria);

    List<Producto> findByPrecioBetween(double min, double max);
}

