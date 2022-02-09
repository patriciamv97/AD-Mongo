package com.baseRelacionaltUDT;

import org.postgresql.util.PGobject;

import java.sql.*;

public class Main {
    static String driver = "jdbc:postgresql:";
    static String host = "//localhost:"; // tamen poderia ser una ip como "192.168.1.14"
    static String porto = "5432";
    static String sid = "postgres";
    static String usuario = "oracle";
    static String password = "oracle";
    static String url = driver + host + porto + "/" + sid;
    static Connection conn;
    static String codigo, descricion, data;
    static int prezo;

    public static Connection conexion() throws SQLException {

        conn = (Connection) DriverManager.getConnection(url, usuario, password);

        if (conn != null) {
            // System.out.println("Conexión a base de datos "+url+" ... Ok");
            System.out.println("");
        } else {
            System.out.println("Conexión fallida");
        }
        return conn;
    }


    public static void main(String[] args) throws SQLException {
        Connection con = conexion();
        Statement stmt = con.createStatement();
        // String cadea = "INSERT INTO produtost VALUES ('p2', 'escoba', 15, null, ('lugo',4))";
        // String cadea = "UPDATE  produtost SET ci.cidade='barna' WHERE codigo = 'p1'";
        /*
        Solucionar esto
        String cadea2 = "UPDATE  produtost SET ci.cp=(ci.cp)+1 WHERE (ci).cidade='barna'";
        stmt.executeUpdate(cadea2);

         */

        String cadea = "SELECT *, (ci).cidade , (ci).cp FROM produtost";
        ResultSet r = stmt.executeQuery(cadea);
        while (r.next()) {
            String cidade = r.getString("cidade");
            String codigo = r.getString("codigo");
            String descricion = r.getString("descricion");
            Double prezo = r.getDouble("prezo");
            int cp = r.getInt("cp");
            System.out.println(cidade + " , " + cp);
        }

        con.close();


    }


}
