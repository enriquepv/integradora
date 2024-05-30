package org.ilozano.proyecto_dam_daw_2.model;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.management.monitor.Monitor;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Document(collection = "productos")
public class Producto {

    @Id
    private String id;
    private String descripcion;
    private double precio;
    private List<String> materiales;
    private String categoria;
    private Map<String, String> dimensiones;
    private Map<String, Object> camposAdicionales; // Campos adicionales
}