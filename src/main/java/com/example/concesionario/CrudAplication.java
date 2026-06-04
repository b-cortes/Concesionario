package com.example.concesionario;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Punto de entrada secundario o principal para la sección de gestión (CRUD) del concesionario.
 * Esta clase hereda {@link Application} y maneja el ciclo de vida de la interfaz
 * gráfica de usuario (GUI) para las operaciones de los coches.
 * @author Brian
 * @version 1.0
 */
public class CrudAplication extends Application {

    /**
     * Método de ejecución principal (Main) estándar de Java.
     * Invoca internamente el método {@code launch()} para inicializar el entorno de JavaFX.
     * @param args Argumentos pasados desde la línea de comandos al arrancar la aplicación.
     */

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Inicializa y configura el escenario principal (Stage) para la vista del CRUD.
     * Este método es llamado automáticamente por el hilo de la aplicación JavaFX
     * una vez que el sistema está listo para pintar componentes en pantalla.
     * @param primaryStage El escenario principal proveído por la plataforma JavaFX
     * sobre el cual se montarán las escenas (.fxml).
     */

    @Override
    public void start(Stage primaryStage) {
        /** TODO: Implementar la carga del FXML correspondiente a la vista del CRUD.
         *
         */
    }
}
