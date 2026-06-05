package BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase encargada de gestionar la conexión centralizada a la base de datos SQLite.
 * Implementa el patrón de diseño Singleton para garantizar que existia una única
 * instancia de la conexión activa durante el ciclo de vida de la apicación.
 * @author Brian
 * @version 1.0
 */
public class Conexion {
    //Ruta de conexión hacia el archivo de la base de datos SQLite local
    private static String URL = "jdbc:sqlite:DataBase/concesionario.db";
    //Instancia única de la conexión (Singleton)
    private static Connection connection;

    /**
     * Constructor predeterminado de la clase.
     * Nota de arquitectura: Para un patrón Singleton estricto, este constructor
     * debería ser privado (private) para evitar que se creen instancias con 'new'.
     */

    public Conexion() {
    }

    /**
     * Proporciona acceso a la instancia única de la conexión a la base de datos.
     * Si la conexión no existe (es null), la inicializa; si ya existe, devuelve la activa.
     * @return Objeto {@link Connection} listo para realizar consultas SQL.
     */

    public static Connection getInstance() {
        if (connection == null) {
            try {
                // Establece la conexión con SQLite utilizando el Driver Manager
                connection = DriverManager.getConnection(URL);
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return connection;
    }

    /**
     * Método de entrada principal (Main) utilizando exclusivamente para pruebas de desarrollo.
     * Permite verificar rápidamente si los drivers y la ruta del archivo '.db' son correctos.
     * @param args Argumentos de la línea de comandos (no utilizados).
     */

    public static void main(String[] args) {
        // Intenta levantar la conexión para comprobar que todo funcione
        getInstance();
    }
}
