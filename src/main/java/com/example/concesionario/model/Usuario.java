package com.example.concesionario.model;

/**
 * Clase que representa el modelo de Usuario en el sistema de Concesionario.
 * Se utiliza principalmente para gestionar los credenciales de acceso y la autenticación.
 * @author Brian
 * @Version 1.0
 */

public class Usuario {
    //Atributos privados para mantener el encapsulamiento
    private String user;
    private String password;

    /**
     * Constructor completo para inicializar un objeto Usuario con sus credenciales.
     * @param user a, admin
     * @param password a, admin
     */

    public Usuario(String user, String password) {
        this.user = user;
        this.password = password;
    }

    /**
     * Obtiene la contraseña del usuario.
     * @return Cadena de texto con la contraseña actual.
     */

    public String getPassword() {
        return password;
    }

    /**
     * Actualiza la contraseña del usuario.
     * @param password Nueva contraseña que se asignará a la cuenta.
     */

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el nombre de usuario.
     * @return Cadena de texto con el nombre de usuario.
     */

    public String getUser() {
        return user;
    }

    /**
     * Modifica el nombre de usuario.
     * @param username Nuevo nombre de usuario que se asignará.
     */

    public void setUser(String username) {
        this.user = username;
    }
}
