package poracleneomongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

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

    public static void crearDocumento(String codp, String nomp, Double graxasTotais, Double prezo){
        Document engadir = new Document("_id", codp)
                .append("nomp", nomp)
                .append("graxasTotais", graxasTotais)
                .append("prezo", prezo);
        colecion.insertOne(engadir);


    }
}
