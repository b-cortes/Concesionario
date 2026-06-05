package com.example.concesionario.dao.Impl;

import BaseDatos.Conexion;
import com.example.concesionario.dao.ConcesionarioDAO;
import com.example.concesionario.model.Concesionario;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz {@link ConcesionarioDAO}.
 * Realiza la gestión de persistencia para los coches en la base de datos
 * mediante operaciones CRUD utilizando JDBC estándar.
 * @author Brian
 * @version 1.0
 */

public class ConcesionarioDAOImp implements ConcesionarioDAO {
    //Conexión principal a la base de datos obtenida mediante el Singleton de Conexión
    Connection conn = Conexion.getInstance();

    /**
     * Genera un identificador único (número de bastidor) de manera manual e iterativa.
     * Recorre los registros existentes para encontrar el primer hueco numérico disponible.
     * @return Un entero que representa el próximo ID disponible para un coche.
     */

    public static int idAuto() {
        //Obtiene una conexión local para asegurar el aislamiento de este método estático
        Connection connection = Conexion.getInstance();

        int j = 0;
        String sql = "SELECT numBastidor FROM concesionario";

        try (Statement st = connection.createStatement()) {
            ResultSet rSt = st.executeQuery(sql);
            //Compara de forma secuencial los IDs de la BD con el contador 'j'
            while (rSt.next()) {
                int x = rSt.getInt(1);
                for (; j < 2147483646;) { //Límite cercano al valor máximo de un entero
                    if (j != x) {
                        return j; // Si encuentra un salto en la secuencia, reutiliza ese ID
                    }
                    break; // Si coinciden, rompe el for para avanzar al siguiente registro
                }
                j++;
            }
        }  catch (SQLException e){
            System.out.println(e);
        }

        return j; // Devuelve el último valor alcanzado si la secuencia estaba completa

    }

    /**
     * Inserta un nuevo coche en la base de datos.
     * Genera automáticamente el número de bastidor antes de realizar la inserción.
     * @param coche Objeto {@link Concesionario} con los datos de la marca y fecha.
     * @return {@code true} si el coche se guardó con éxito; {@code false} si ocurrió un error.
     */

    @Override
    public boolean addCoche(Concesionario coche) {
        String sql = "INSERT INTO concesionario VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            //Asignación de parámetros dinámicos
            pstmt.setInt(1, idAuto());
            pstmt.setString(2, coche.getMarca());
            pstmt.setString(3, coche.getAnno().toString());// Guarda la fecha como String
            pstmt.setString(4, coche.getUserid());
            pstmt.executeUpdate();
            System.out.println("Se a introducido");
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    /**
     * Elimina un coche de la base de datos utilizando su número de bastidor.
     * @param numBastidor Identificador único del coche a eliminar.
     * @return {@code true} si se eliminó al menos un registro; {@code false} en caso contrario.
     */

    @Override
    public boolean deleteCoche(int numBastidor) {
        String sql = "DELETE FROM concesionario WHERE numBastidor = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, numBastidor);
            int rowsAffected = pstmt.executeUpdate();
            //Retorna verdadero si la consulta realmente borró alguna fila
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e);;
            return false;
        }
    }

    /**
     * Actualiza la información de un coche existente basándose en su número de bastidor.
     * @param coche Objeto {@link Concesionario} que contiene los nuevos datos de marca y fecha.
     * @return {@code true} si la fila se actualizó correctamente; {@code false} si falló.
     */

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

    /**
     * Recupera todos los coches almacenados en la tabla 'concesionario'.
     * Convierte las fechas almacenadas en formato de texto de vuelta a objetos {@link LocalDate}.
     * @return Una lista conteniendo todos los coches; vacía si no hay registros.
     */
    @Override
    public List<Concesionario> getAllCoches() {
        List<Concesionario> coches = new ArrayList<>();
        String sql = "SELECT * FROM concesionario";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // Mapea cada fila del ResultSet a un objeto de nuestro modelo
            while (rs.next()) {
                Concesionario c = new Concesionario(
                        rs.getInt("numBastidor"),
                        rs.getString("marca"),
                        LocalDate.parse(rs.getString("anno")) // Conversión de String a LocalDate
                );
                c.setUserid(rs.getString("userid"));
                coches.add(c);
            }
        } catch (SQLException e) {
            System.err.println(e);;
        }
        return coches;
    }

    @Override
    public List<Concesionario> getCochesByUserId(String userId) {
        List<Concesionario> coches = new ArrayList<>();
        String sql = "SELECT * FROM concesionario WHERE userid = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Concesionario c = new Concesionario(
                        rs.getInt("numBastidor"),
                        rs.getString("marca"),
                        LocalDate.parse(rs.getString("anno"))
                );
                c.setUserid(rs.getString("userid"));
                coches.add(c);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return coches;
    }
}
