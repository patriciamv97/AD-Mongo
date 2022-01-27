package com.pJavaMongo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.logging.Logger;
import java.util.logging.Level;

public class Main {
    public static MongoClient client;
    public static MongoDatabase database;
    public static MongoCollection<Document> colecion;

    public static void main(String[] args) {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
        conectar_a_servidor();
        conectar_a_base("training");
        conectar_a_unha_colecion("scores");
        /*
        Document engadir = new Document("kind", "taller")
                .append("scores", 61.0)
                .append("enderezo", new Document()
                        .append("rua", "urzaiz")
                        .append("numero", 4)
                        .append("cidade", "vigo"));

        colecion.insertOne(engadir);

        */

        //  colecion.updateOne(new BasicDBObject("kind", "taller"),new BasicDBObject("$set", new BasicDBObject("scores", 98.0)));
        // colecion.deleteOne(new BasicDBObject("kind", "taller"));
        //consultar_por_id(new ObjectId("4c90f2543d937c033f424721"));
        //consultar_por_campo_valor("student", 99.0);
        //  actualizar_por_id(new ObjectId("4c90f2543d937c033f424721"), "score",85.0 );

        client.close();


    }


    public static void conectar_a_servidor() {
        client = new MongoClient("localhost", 27017);

    }

    public static void conectar_a_base(String nomebase) {
        database = client.getDatabase(nomebase);
    }

    public static void conectar_a_unha_colecion(String coleccion) {
        colecion = database.getCollection(coleccion);
    }

    public static void consultar_por_id(ObjectId _id) {
        BasicDBObject condicion = new BasicDBObject("_id", _id);
       /*
        Document d = colecion.find(condicion).projection(Projections.include("_id", "kind", "score")).first();
        ObjectId objectId = d.getObjectId("_id");
        String king = d.getString("kind");
        Double score = d.getDouble("score");
        System.out.printf("_id: "+objectId+" king: "+king+" scores: "+score);

        */
        BasicDBObject campos = new BasicDBObject();
        campos.put("kind", 1);
        campos.put("score", 1);
        Document d = colecion.find(condicion).projection(campos).first();
        ObjectId idj = d.getObjectId("_id");
        String king = d.getString("kind");
        Double score = d.getDouble("score");
        System.out.printf("_id: " + idj + " king: " + king + " scores: " + score);


    }

    public static void consultar_por_campo_valor(String campo, Object valor) {
        BasicDBObject allquery = new BasicDBObject("kind", "essay")
                .append("student", new BasicDBObject("$gt", 0).
                        append("$lt", 3));
        //BasicDBObject condicion = new BasicDBObject(campo, valor);
        //FindIterable<Document> docs = colecion.find(condicion);
        FindIterable<Document> docs = colecion.find(allquery);

        MongoCursor<Document> iterator = docs.iterator();
        while (iterator.hasNext()) {
            Document d = iterator.next();
            ObjectId idj = d.getObjectId("_id");
            String kind = d.getString("kind");
            Double score = d.getDouble("score");
            Double student = d.getDouble("student");
            System.out.printf("_id : " + idj + " king: " + kind + " scores: " + score + " student: " + student + "\n");


        }


    }

    public static void actualizar_por_id(ObjectId _id, String campo, Object valor) {
        colecion.updateOne(new BasicDBObject("_id", _id), new BasicDBObject("$set", new BasicDBObject(campo, valor)));
    }

    public static void incrementar_por_id(ObjectId _id, String campo, Object valor) {
        colecion.updateOne(new BasicDBObject("_id", _id), new BasicDBObject("inc", new BasicDBObject(campo, valor)));
    }
}
