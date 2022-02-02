package com.ejercicios.MongoPostgres;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Principal {
    public static void main(String[] args) throws SQLException {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
        Mongo.conectar_a_servidor();
        Mongo.conectar_a_base("voos");
        Mongo.conectar_a_unha_colecion("reserva");
       /*

        Mongo.reservaAvion("1","361a", 1, 2, 0, 0);
        Mongo.reservaAvion("2","362b", 3, 4, 0, 0);
        Mongo.reservaAvion("3","361a", 5, 6, 0, 0);

        */
        Mongo.confirmar("1");
        Mongo.client.close();
    }
}
