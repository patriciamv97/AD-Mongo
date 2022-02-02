package com.ejercicios.MongoPostgres;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mongo {

    public static MongoClient client;
    public static MongoDatabase database;
    public static MongoCollection<Document> colecion;

    public static void conectar_a_servidor() {
        client = new MongoClient("localhost", 27017);

    }

    public static void conectar_a_base(String nomebase) {
        database = client.getDatabase(nomebase);
    }

    public static void conectar_a_unha_colecion(String coleccion) {
        colecion = database.getCollection(coleccion);
    }

    public static void reservaAvion(String id, String dni, int idvooida, int idvoovolta, float prezo, int confirmado) throws SQLException {
        Document engadir = new Document("_id", id)
                .append("dni", dni)
                .append("idvooida", idvooida)
                .append("idvoovolta", idvoovolta)
                .append("prezo", prezo)
                .append("confirmado", confirmado);

        colecion.insertOne(engadir);
        Postgres.aumentarReservas(dni);
    }

    public static void confirmar(String _id) throws SQLException {
        BasicDBObject condicion = new BasicDBObject("_id", _id);
        BasicDBObject campos = new BasicDBObject();
        Document d = colecion.find(condicion).projection(campos).first();
        int idvooida= d.getInteger("idvooida");
        int idvoovolta = d.getInteger("idvoovolta");
        int prezo = Postgres.prezo(idvooida, idvoovolta);
        colecion.updateOne(new BasicDBObject("_id", _id), new BasicDBObject("$set", new BasicDBObject("confirmado", 1)));
        colecion.updateOne(new BasicDBObject("_id", _id), new BasicDBObject("$set", new BasicDBObject("prezoreserva", prezo)));

    }

}
