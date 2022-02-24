package poracleneomongo;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws SQLException {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
        Mongo.conectar_a_servidor();
        Mongo.conectar_a_base("poracleneomongo");
        Mongo.conectar_a_unha_colecion("totalgraxas");
        Postgres.mostrarDatos();
    }
}
