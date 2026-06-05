package com.example.concesionario;

import com.example.concesionario.model.Usuario;

/**
 * Clase utilitaria que gestiona el estado de la sesión actual del usuario autenticado.
 * Permite almacenar la información del usuario logueado para acceder a ella
 * desde cualquier parte de la aplicación sin necesidad de parámetros.
 *
 * Proporciona métodos para:
 * - Iniciar sesión con un usuario autenticado
 * - Obtener el ID del usuario actual para filtrar datos por usuario
 * - Obtener información del usuario logueado
 *
 * No se puede instanciar: utiliza únicamente miembros estáticos.
 *
 * @author Juan
 * @version 1.0
 */
public final class Sesion {

    // Usuario actualmente logueado en la aplicación
    private static Usuario usuarioActual;

    /**
     * Constructor privado para impedir la instanciación externa.
     */
    private Sesion() {
    }

    /**
     * Inicia una nueva sesión con los datos del usuario autenticado.
     * Se ejecuta después de un login exitoso en LoginController.
     *
     * @param usuario Objeto Usuario con los datos del usuario que inicia sesión
     */
    public static void iniciar(Usuario usuario) {
        usuarioActual = usuario;
    }

    /**
     * Obtiene el usuario actualmente logueado.
     * @return Objeto Usuario con los datos del usuario en sesión, o null si no hay sesión activa
     */
    public static Usuario getUsuario() {
        return usuarioActual;
    }

    /**
     * Obtiene el ID del usuario actualmente logueado.
     * En este caso, se utiliza el nombre de usuario como identificador único.
     * Se utiliza principalmente para:
     * - Filtrar los coches que pertenecen al usuario (ListaController)
     * - Asignar la propiedad userid a los coches nuevos (CrudController)
     *
     * @return String con el identificador del usuario en sesión, o null si no hay sesión activa
     */
    public static String getUserId() {
        return usuarioActual != null ? usuarioActual.getUser() : null;
    }
}
