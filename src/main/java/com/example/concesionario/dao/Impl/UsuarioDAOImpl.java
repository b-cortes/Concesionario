package com.example.concesionario.dao.Impl;

import BaseDatos.Conexion;
import com.example.concesionario.dao.UsuarioDAO;
import com.example.concesionario.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAOImpl implements UsuarioDAO{
    private Connection connection;

    public UsuarioDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Usuario login(String user, String password) {

        String sql = """
                SELECT * FROM login
                WHERE user = ? AND password = ?;
                """;
        try (
                PreparedStatement pState = connection.prepareStatement(sql);
        ) {
            pState.setString(1, user);
            pState.setString(2, password);

            ResultSet resultSet = pState.executeQuery();
            if (resultSet.next()) {
                return new Usuario(resultSet.getString("user"), resultSet.getString("password"));
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
            preparedStatement.setString(1, usuario.getUser());
            preparedStatement.setString(2, usuario.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e)
;
        }
    }
}

