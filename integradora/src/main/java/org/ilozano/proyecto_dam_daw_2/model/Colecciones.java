package org.ilozano.proyecto_dam_daw_2.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Colecciones {

    @Getter
    private final static Map<String, String> listaColores = new HashMap<String,String>();
    @Getter
    private final static Map<String, String> listaMateriales = new HashMap<String,String>();

    @Getter
    private final static Map<String, String> listageneros = new HashMap<String,String>();
    @Getter
    private final static Map<String, String> listaTallas= new HashMap<String,String>();




    static {
        listaColores.put("BL", "Blanco");
        listaColores.put("GR", "Gris");
        listaColores.put("V", "Verde");

        listaMateriales.put("PL", "plastico");
        listaMateriales.put("AL", "aluminio");
        listaMateriales.put("M", "metal");

        listageneros.put("M", "Masculino");
        listageneros.put("F", "Femenino");
        listageneros.put("O", "Otros");

        listaTallas.put("XS", "Extra Small");
        listaTallas.put("S", "Small");
        listaTallas.put("M", "Medium");
        listaTallas.put("L", "Large");
        listaTallas.put("XL", "Extra Large");
    }

}

