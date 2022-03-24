package pvehiculos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Postgres {
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
    public static  void inserir(Double _id, String dni, String nomec, String nomeveh, int pf) throws SQLException {
        Connection con = conexion();
        String cadea = "INSERT INTO finalveh VALUES (?, ?,?,(?,?))";
        PreparedStatement ps = con.prepareStatement(cadea);
        ps.setDouble(1,_id);
        ps.setString(2,dni);
        ps.setString(3,nomec);
        ps.setString(4,nomeveh);
        ps.setInt(5,pf);
        ps.executeUpdate();
        con.close();

    }
}
