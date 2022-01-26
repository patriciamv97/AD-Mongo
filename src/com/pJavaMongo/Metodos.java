package com.pJavaMongo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class Metodos {

    public static MongoClient client;
    public static MongoDatabase database;

    public static void conectar_a_servidor(){
        client= new MongoClient("localhost", 27017);

    }

    public static void conectar_a_base(String nomebase){
        database= client.getDatabase(nomebase);
    }

    public static void conectar_a_unha_colecion(String coleccion){
         database.getCollection(coleccion);
    }
}
