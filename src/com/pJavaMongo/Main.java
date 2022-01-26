package com.pJavaMongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import javax.swing.text.Document;

public class Main {

    public static void main(String[] args) {

        conectar_a_servidor();
        conectar_a_base("training");
        conectar_a_unha_colecion("scores");
        Document engadir = new Document("kind", "taller")
                .append("score", 61.0)
                .append("enderezo", new Document()
                        .append("rua", "urzaiz")
                        .append("numero", 4)
                        .append("cidade", "vigo"));

        colecion.insertOne(engadir);
        client.close();
    }

    public static MongoClient client;
    public static MongoDatabase database;
    public static
    public static void conectar_a_servidor() {
        client = new MongoClient("localhost", 27017);

    }

    public static void conectar_a_base(String nomebase) {
        database = client.getDatabase(nomebase);
    }

    public static void conectar_a_unha_colecion(String coleccion) {
        colecion =database.getCollection(coleccion);
    }
}
