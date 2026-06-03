package dao.Impl;

import BaseDatos.Conexion;
import dao.UsuarioDAO;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAOImpl {
    Connection connection = Conexion.getInstance();

    @Override
    public Usuario login(String username, String password) {

        String sql = """
                SELECT * FROM login
                WHERE username = ? AND password = ?;
                """;
        try (
                PreparedStatement pState = connection.prepareStatement(sql);
        ) {
            pState.setString(1, username);
            pState.setString(2, password);

            ResultSet resultSet = pState.executeQuery();
            if (resultSet.next()) {
                return new Usuario(resultSet.getString("username"), resultSet.getString("password"));
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    @Override
    public void insertar(Usuario usuario) {
        Connection connection = Conexion.getInstance();
        String sql = """
                    
                INSERT INTO login VALUES (?,?);
                    """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql) ) {
            preparedStatement.setString(1, usuario.getUsername());
            preparedStatement.setString(2, usuario.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e)
;
        }
    }
}

