package com.example.concesionario.basedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static String URL = "jdbc:sqlite:DataBase/concesionario.db";
    private static Connection connection;

    public Conexion() {
    }

    public static Connection getInstance() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL);
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return connection;
    }

    public static void main(String[] args) {
        getInstance();
    }
}
