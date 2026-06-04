package BaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase utilitaria encargada de la inicialización y creación del esquema de la base de datos.
 * Define y ejecuta las sentencias SQL necesarias para estructurar las tablas iniciales del sistema.
 * @author Brian
 * @version 1.0
 */

public class CrearTablas {

    /**
     * Método principal que ejecuta el script de creción de tablas.
     * Conecta a la base de datos  y genera las tablas 'login' y 'concesionario'.
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        // Obtiene la instancia única de la conexión centralizada
        Connection connection = Conexion.getInstance();
        // 1. Definición y creación de la tabla 'login'
        String login  = """
                        CREATE TABLE login (
                        user TEXT NOT NULL PRIMARY KEY,
                        password TEXT NOT NULL);
                """;
        // try-with-resources para asegurar el cierre del Statement tras ejecutar la consulta DDL
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(login);
        } catch (SQLException e) {
            System.err.println(e);
        }
        // 2. Definición y creación de la tabla 'concesionario'
        String concesionario = """
                               CREATE TABLE concesionario (
                               numBastidor NUMBER NOT NULL PRIMARY KEY,
                               marca TEXT NOT NULL,
                               anno DATE NOT NULL
                               );
                 """;
        // Se abre un nuevo Statement aislado para la segunda operación
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(concesionario);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }


}
