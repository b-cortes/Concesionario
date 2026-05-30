package com.example.concesionario.dao.impl;

import com.example.concesionario.basedatos.Conexion;
import com.example.concesionario.dao.UsuarioDAO;
import com.example.concesionario.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public Usuario login(String username, String password) {

        String sql = """
                SELECT * FROM login
                WHERE username = ? AND password = ?;
                """;
        try (
            Connection connection = Conexion.getInstance();
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
}
