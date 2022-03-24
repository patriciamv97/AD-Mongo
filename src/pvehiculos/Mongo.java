package pvehiculos;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
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

    public static void mostrarDatos(){

        BasicDBObject condicion = new BasicDBObject();

        FindIterable<Document> docs = colecion.find(condicion);

        MongoCursor<Document> iterator = docs.iterator();
        while (iterator.hasNext()) {
            Document d = iterator.next();
            Double _id = d.getDouble("_id");
            String dni = d.getString("dni");
            String codveh = d.getString("codveh");
            System.out.println(" _id : " + _id + " dni " + dni+" codveh"+codveh);
            Odb.obtenerDatosVehiculo(codveh);
            Odb.obtenerDatosCliente(dni);

        }

    }
}
