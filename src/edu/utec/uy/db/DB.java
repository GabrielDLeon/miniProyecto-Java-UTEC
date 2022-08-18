package edu.utec.uy.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    private static Connection connection;
    private static final String database = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String user = "C##JAVA";
    private static final String password = "java";

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Se encontró el Driver para Oracle DataBase");
            connection = DriverManager.getConnection(database, user, password);
            System.out.println("Se estableció la conexión con la Base de Datos");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Conexión cerrada correctamente!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static String getDatabase() {
        return database;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }
}
