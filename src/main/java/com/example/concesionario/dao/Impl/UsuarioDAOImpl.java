package com.example.concesionario.dao.Impl;

import BaseDatos.Conexion;
import com.example.concesionario.dao.UsuarioDAO;
import com.example.concesionario.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementación de la interfaz {@link UsuarioDAO} utilizando JDBC para la persistencia en Base de Datos.
 * Gestiona las operaciones de autenticación e inserción directamente en la tabla 'login'.
 * @author Brian
 * @version 1.0
 */
public class UsuarioDAOImpl implements UsuarioDAO{
    //Conexión activa a la base de datos compartida por la instancia
    private Connection connection;

    /**
     * Constructor que inyecta la conexión a la base de datos.
     * Permite reutilizar una única conexión o gestionar transacciones externamente.
     * @param connection Conexión activa a la base de datos.
     */

    public UsuarioDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Valida las credenciales del usuario consultando la base de datos.
     * Usa un PreparedStatement para evitar ataques de Inyección SQL (SQL Injección).
     * @param user Nombre de usuario ingresado en el login.
     * @param password Contraseña ingresada en el login.
     * @return Un objeto {@link Usuario} si coincide la combinación; {@code null} si las credenciales
     * son incorrectas o hay un error.
     */

    @Override
    public Usuario login(String user, String password) {
        //Consulta SQL limpia utilizando Bloques de Texto.
        String sql = """
                SELECT * FROM login
                WHERE user = ? AND password = ?;
                """;
        //Uso de try-with-resources para asegurar el cierre automático del PreparedStatement
        try (
                PreparedStatement pState = connection.prepareStatement(sql);
        ) {
            //Seteo de parámetros seguro
            pState.setString(1, user);
            pState.setString(2, password);
            //Ejecución de la consulta y procesamiento del ResultSet
            ResultSet resultSet = pState.executeQuery();
            if (resultSet.next()) {
                //Si existe el registro, mapea los datos de las columnas al objeto Modelo
                return new Usuario(resultSet.getString("user"), resultSet.getString("password"));
            }

        } catch (SQLException e) {
            //Imprime el stacktrace del error en el canal de error estándar
            System.err.println(e);
        }
        return null;
    }

    /**
     * Insertar un nuevo registro de usuario en la tabla 'login'.
     * @param usuario Objeto que contiene las credenciales del nuevo usuario.
     */
    @Override
    public void insertar(Usuario usuario) {
        //Obtención de la conexión mediante el patrón Singleton de la clase Conexion
        Connection connection = Conexion.getInstance();
        String sql = """
                    
                INSERT INTO login VALUES (?,?);
                    """;
        //try-with-resources para garantizar la liberación del PreparedStatement
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql) ) {
            //Asignación de los valores del objeto usuario de los placeholders '?' de la consulta
            preparedStatement.setString(1, usuario.getUser());
            preparedStatement.setString(2, usuario.getPassword());
            //Ejecuta la sentencia DMl (INSERT) en la base de datos
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}

