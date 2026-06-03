package com.example.concesionario.dao.Impl;

import BaseDatos.Conexion;
import com.example.concesionario.dao.ConcesionarioDAO;
import com.example.concesionario.model.Concesionario;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConcesionarioDAOImp implements ConcesionarioDAO {
    Connection conn = Conexion.getInstance();

    @Override
    public boolean addCoche(Concesionario coche) {
        String sql = "INSERT INTO concesionario VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, coche.getNumBastidor());
            pstmt.setString(2, coche.getMarca());
            pstmt.setString(3, coche.getAnno().toString());
            pstmt.executeUpdate();
            System.out.println("Se a introducido");
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteCoche(int numBastidor) {
        String sql = "DELETE FROM concesionario WHERE numBastidor = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, numBastidor);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e);;
            return false;
        }
    }

    @Override
    public boolean updateCoche(Concesionario coche) {
        String sql = "UPDATE concesionario SET marca = ?, anno = ? WHERE numBastidor = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, coche.getMarca());
            pstmt.setString(2, coche.getAnno().toString());
            pstmt.setInt(3, coche.getNumBastidor());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println(e);;
            return false;
        }
    }

    @Override
    public List<Concesionario> getAllCoches() {
        List<Concesionario> coches = new ArrayList<>();
        String sql = "SELECT * FROM concesionario";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Concesionario c = new Concesionario(
                        rs.getInt("numBastidor"),
                        rs.getString("marca"),
                        LocalDate.parse(rs.getString("anno"))
                );
                coches.add(c);
            }
        } catch (SQLException e) {
            System.err.println(e);;
        }
        return coches;
    }
}
