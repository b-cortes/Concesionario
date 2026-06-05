    package com.example.concesionario;

    import javafx.application.Application;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Scene;
    import javafx.scene.image.Image;
    import javafx.stage.Stage;

    import java.io.IOException;
    import java.util.Objects;


    /**
     * Clase de aplicación principal del sistema del concesionario.
     * Se encarga de inicializar el entorno de ejecución de JavaFX, preparar el escenario,
     * y desplegar la pantalla inicial de inicio de sesiónn (login).
     * @author Brian
     * @version 1.0
     */
    public class LoginAplication extends Application {


        /**
         * Punto de arranque del ciclo de vida de la interfaz de JavaFX.
         * Carga la vista FXML del login, define las dimensiones de la escena y muestra la ventana.
         * @param stage El escenario principal (ventana) provisto por la plataforma-
         * @throws IOException Si ocurre un error al intentar leer o cargar el archivo 'login.view.fxml'
         */
        @Override
        public void start(Stage stage) throws IOException {

            // Inicializa el cargador apuntando el archivo FXML de la vista de inicio de sesión
            FXMLLoader fxmlLoader = new FXMLLoader(LoginAplication.class.getResource("login-view.fxml"));

            // Crea la escena cargando los componentes visuales del FXML y fijando un tamaño
            Scene scene = new Scene(fxmlLoader.load(), 500, 500);

            // Configura los metadatos de la ventana principal
            stage.setTitle("Aura Motors"); // Título personalizado de la app
            stage.setScene(scene); // Asigna la escena al escenario

            //Cargamos el logo del conesionario
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/Aura_Motors_Logo.png"))));

            //Hace visible la ventana para el usuario
            stage.show();
        }
    }
