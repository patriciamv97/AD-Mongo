package poracleneomongo;

import java.sql.*;
import java.util.List;

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
        String cadea = "SELECT *, (tipo).nomeprato , (tipo).prezo FROM PRATO";
        PreparedStatement ps = con.prepareStatement(cadea);
        ResultSet  rs = ps.executeQuery();
        while(rs.next()){
                String codprato = rs.getString("codprato");
                String tipo_prato = rs.getString("nomeprato");
                Double prezo = rs.getDouble("prezo");
            System.out.println("CODIGO DO PRATO: "+codprato+"\nnome do prato: "+tipo_prato);
           int total_graxas_prato= Odb.mostrarObxetosOdb(codprato);
            System.out.println("prezo "+prezo);
           Mongo.crearDocumento(codprato, tipo_prato, (double) total_graxas_prato,prezo);
        }


       // ps.executeQuery();
        con.close();

    }
}
