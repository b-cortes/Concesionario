package com.example.concesionario.dao;

import com.example.concesionario.model.Concesionario;

import java.util.List;

/**
 * Interfaz que define las operaciones CRUD
 * para la gestión de coches en el sistema de persistencia.
 * @author Brian
 * @version 1.0
 */

public interface ConcesionarioDAO {

    /**
     * Registra un nuevo coche en el sistema.
     * @param coche Objeto {@link Concesionario} con los datos del vehículo a registrar.
     * @return {@code true} si el coche se guardó correctamente; {@code false} en caso contrario.
     */
    boolean addCoche(Concesionario coche);

    /**
     * Elimina un coche del sistema utilizando su número de bastidor como identificador único.
     * @param numBastidor El número de bastidor del vehículo que se desea eliminar.
     * @return {@code true} si el vehículo fue eliminado exitosamente; {@code false} si no se encuentra
     * o no se puede eliminar.
     */
    boolean deleteCoche(int numBastidor);

    /**
     * Modifica los datos de un coche ya existente en el sistema.
     * El coche se identificará internamente por su número de bastidor para actualizar sus campos.
     * @param coche Objeto {@link Concesionario} que contiene la información actualizada.
     * @return {@code true} si la actualización fue exitosa; {@code false} si hubo un problema.
     */
    boolean updateCoche(Concesionario coche);

    /**
     * Recupera la lista completa de todos los coches registrados en el sistema.
     * @return Una lista ({@link List}) de objeto {@link Concesionario}.
     * Si no hay registros, devolverá una lista vacía.
     */
    List<Concesionario> getAllCoches();
}
