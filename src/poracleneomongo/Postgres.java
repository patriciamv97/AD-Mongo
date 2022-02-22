package poracleneomongo;

import java.sql.*;

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

    public static void mostrarDatos() throws SQLException {
        Connection con = conexion();
        String cadea = "SELECT * FROM PRATOS";
        PreparedStatement ps = con.prepareStatement(cadea);
        ResultSet  rs =ps.getResultSet();
        while(rs.next()){

        }
        ps.executeQuery();
        con.close();

    }
}
