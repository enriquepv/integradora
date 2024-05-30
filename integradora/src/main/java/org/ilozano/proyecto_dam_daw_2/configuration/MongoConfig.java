package org.ilozano.proyecto_dam_daw_2.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    public static MongoClient mongoClient = MongoClients.create("mongodb://root:alumno@172.19.0.2:27017/prueba?authSource=prueba");

    @Bean
    public static MongoCollection<Document> conexionMongo() {
        MongoDatabase mongoDatabase = mongoClient.getDatabase("prueba");
        MongoCollection<Document> collection = mongoDatabase.getCollection("productos");

        // Insertar un documento de prueba si la colección está vacía
        if (collection.countDocuments() == 0) {
            Document doc = new Document("nombre", "Producto de prueba")
                    .append("precio", 0.0)
                    .append("categoria", "playa");
            collection.insertOne(doc);
        }

        return collection;
    }
}
