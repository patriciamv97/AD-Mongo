package pinvasoras1;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.sql.SQLException;

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

    public static void consultar_por_campo_valor(String campo, int valor, String nomz) throws SQLException {
        Double totalDanado = 0.0;
        Double totalExemplares = 0.0;
        Double codz = 0.0;
        BasicDBObject allquery = new BasicDBObject("codzona", valor);

        FindIterable<Document> docs = colecion.find(allquery);

        MongoCursor<Document> iterator = docs.iterator();
        while (iterator.hasNext()) {
            Document d = iterator.next();
            codz = d.getDouble("codzona");
            Double extensionada = d.getDouble("extensiondanada");
            Double exemplaresmetro = d.getDouble("exemplaresmetro");
            System.out.println(" extensionada: " + extensionada + " exemplaresmetro " + exemplaresmetro);
            totalDanado=totalDanado+extensionada;
            totalExemplares=extensionada*exemplaresmetro+ totalExemplares;

        }
        
        System.out.println("totalDandado= "+totalDanado+ " totalExemplares = "+totalExemplares);
        Postgress.inserir(valor, nomz, totalDanado, totalExemplares);
    }

}
