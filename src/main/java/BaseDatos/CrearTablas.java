package BaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearTablas {
    public static void main(String[] args) {
        Connection connection = Conexion.getInstance();

        String login  = """
                        CREATE TABLE login (
                        user TEXT NOT NULL PRIMARY KEY,
                        password TEXT NOT NULL);
                """;

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(login);
        } catch (SQLException e) {
            System.err.println(e);
        }

        String concesionario = """
                               CREATE TABLE concesionario (
                               numBastidor NUMBER NOT NULL PRIMARY KEY,
                               marca TEXT NOT NULL,
                               anno DATE NOT NULL
                               ); 
                 """;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(concesionario);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }


}
