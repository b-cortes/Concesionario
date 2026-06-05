package com.example.concesionario;

import javafx.application.Application;

/**
 * Clase de lanzamiento alternativa para la aplicación.
 * @author Brian
 * @version 1.0
 */
public class Launcher {

    /**
     * Punto de entrada principal definitivo para el sistema de ejecución.
     * Redirige el flujo de arranque hacia la clase contenedora de la interfaz gráfica.
     * @param args Argumentos de la línea de comandos pasados al iniciar el programa.
     */

    public static void main(String[] args) {

        // Lanza la aplicación JavaFX especificando que la vista inicial será gestionada
        Application.launch(LoginAplication.class, args);
    }
}
