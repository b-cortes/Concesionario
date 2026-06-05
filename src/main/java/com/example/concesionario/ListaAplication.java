package com.example.concesionario;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Punto de entrada alternativo o de prueba para la vista del listado de coches.
 * Esta clase hereda de {@link Application} y permite inicializar y probar de forma
 * aislada el comportamiento de la pantalla de visualización de datos (TableView).
 * @author Brian
 * @version 1.0
 */
public class ListaAplication extends Application {


    /**
     * Método principal (Main) que arranca la ejecución de esta clase.
     * Llama el método estático {@code launch()} para inicializar el framework de JavaFX.
     * @param args Argumentos recibidos desde la consola del sistema.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Configura y despliega el escenario principal para la vista de la lista.
     * Este método se ejeuta en el hilo principal de la interfaz de usuario de JavaFX.
     * @param primaryStage  El escenario base sobre el cual se cargará la escena del listado.
     */

    @Override
    public void start(Stage primaryStage) {

        /**
         * Implementar la carga del FXML correspondiente a la vista de la lista.
         */
    }
}

