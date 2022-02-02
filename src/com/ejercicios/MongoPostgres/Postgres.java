package com.ejercicios.MongoPostgres;

import java.sql.*;

public class Postgres {
    static  String driver = "jdbc:postgresql:";
    static  String host = "//localhost:"; // tamen poderia ser una ip como "192.168.1.14"
    static  String porto = "5432";
    static  String sid = "postgres";
    static  String usuario = "oracle";
    static  String password = "oracle";
    static  String url = driver + host+ porto + "/" + sid;
    static Connection conn;
    static String codigo, descricion, data;
    static int prezo;

    public static Connection conexion() throws SQLException {

        conn=  (Connection) DriverManager.getConnection(url,usuario,password);

        if (conn!=null){
            // System.out.println("Conexión a base de datos "+url+" ... Ok");
            System.out.println("");
        }else{
            System.out.println("Conexión fallida");
        }
        return conn;
    }

    public static void aumentarReservas(String dni) throws SQLException {
        Connection conexion = conexion();
        String sql = "UPDATE pasaxeiros SET nreservas=nreservas+1 WHERE dni =?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, dni);
        ps.executeUpdate();

    }

    public static int prezo(int idvooida, int idvoovolta) throws SQLException {
        int prezoIda;
        int prezoVolta;
        Connection conexion = conexion();
        String sql = "SELECT prezo FROM voos WHERE voo=?";
        PreparedStatement ps = conexion().prepareStatement(sql);
        ps.setInt(1,idvooida);
        ResultSet rs = ps.executeQuery();
        rs.next();
        prezoIda = rs.getInt(1);
        PreparedStatement ps2 = conexion().prepareStatement(sql);
        ps.setInt(1,idvoovolta);
        ResultSet rs2 = ps.executeQuery();
        rs2.next();
        prezoVolta = rs2.getInt(1);

        return prezoIda+prezoVolta;
    }

}
