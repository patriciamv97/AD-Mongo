package pvehiculos;



import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
        Mongo.conectar_a_servidor();
        Mongo.conectar_a_base("test");
        Mongo.conectar_a_unha_colecion("vendas");
        Mongo.mostrarDatos();
    }


}
