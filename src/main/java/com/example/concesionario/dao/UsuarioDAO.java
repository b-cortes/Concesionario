package com.example.concesionario.dao;


import com.example.concesionario.model.Usuario;

/**
 * Interfaz que define las operaciones de acceso a datos (DAO) para la entidad {@Link Usuario}.
 * Proporciona el contrato para la abstracción de la lógica de persistencia (Base de Datos, Archivos, etc...).
 * @Author Brian
 * @version 1.0
 */

public interface UsuarioDAO {

    /**
     * Realiza la validación de credenciales para el inicio de sesión de un usuario.
     * @param user El nombre de usuario que intenta acceder.
     * @param password La contraseña proporcionada por el usuario.
     * @return Un objeto {@Link Usuario} completo si las credenciales son correctas y coincide
     * con los registros; de lo contrario, devuelve {@code null}.
     */
    Usuario login(String user, String password);

    /**
     * Registra un nuevo usuario en el sistema.
     * @param usuario El objeto {@Link Usuario} que contiene los datos de la cuenta a crear.
     */
    public void insertar (Usuario usuario);


}
