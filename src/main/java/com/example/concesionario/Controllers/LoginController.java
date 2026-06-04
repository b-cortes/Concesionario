package com.example.concesionario.Controllers;



import BaseDatos.Conexion;
import com.example.concesionario.dao.Impl.UsuarioDAOImpl;
import com.example.concesionario.model.Usuario;
import com.example.concesionario.dao.UsuarioDAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

/**
 * Controlador de JavaFX para la pantalla de inicio de sesión (Login).
 * Se encarga de capturar las credenciales del usuario, validarlas a través de la capa DAO
 * y permitir el acceso seguro al panel principal de la aplicación (CRUD).
 * @author Brian
 * @version 1.0
 */
public class LoginController implements Initializable {

    // ==========================================
    // Componentes FXML Inyectados desde la Vista
    // ==========================================
    @FXML private TextField txtUser;
    @FXML private PasswordField txtPassword; // Oculta los caracteres de la contraseña en la UI
    @FXML private Label lblError; // Etiqueta dinámica para mostrar mensajes de error

    // Interfaz del DAO de usuarios
    private UsuarioDAO usuarioDAO;

    /**
     * Método de inicialización del ciclo de vida de JavaFX.
     * Recupera de forma segura la conexión de la base de datos e instancia la implementación del DAO
     * garantizando que los recursos estén listos antes de cualquier interacción del usuario.
     * @param url Ubicación para resolver rutas relativas.
     * @param resourceBundle Recursos de localización.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Obtención segura de la conexión para inyectarla en el constructor de la implementación
        Connection conn = Conexion.getInstance();
        this.usuarioDAO = new UsuarioDAOImpl(conn);
    }

    /**
     * Gestiona el evento de clic en el botón de inicio de sesión.
     * Valída que los campos no estén vacíos, procesa la autenticación contra el DAO
     * y redirige el panel CRUD si las credenciales son válidas.
     * @param event Evento de acción del botón que permite rastrear la ventana actual.
     */

    @FXML
    protected void onLoginButtonClick(ActionEvent event) {
        String user = txtUser.getText();
        String pass = txtPassword.getText();

        // Validación preventiva en el cliente: evita consultas innecesarias a la BD si faltan datos
        if (user.isEmpty() || pass.isEmpty()) {
            lblError.setText("Por favor, rellena todos los campos.");
            return;
        }

        // Intenta realizar el login mapeando el resultado en un objeto de tipo Usuario
        Usuario loggedUser = usuarioDAO.login(user, pass);

        if (loggedUser != null) {

            // Autenticación exitosa: se limpia cualquier error previo y se cambia de pantalla
            cambiarEscena(event, "/com/example/concesionario/crud-view.fxml");
        } else {

            // Credenciales inválidas o usuario inexistente
            lblError.setText("Este usuario o contraseña no es correcto.");
        }
    }

    /**
     * Cierra de manera inmediata y limpia la iterfaz de usurio de JavaFX.
     */

    @FXML
    protected void onSalirButtonClick() {
        Platform.exit();
    }

    /**
     * Método utilitario diseñado para gestionar la navegacón entre escenas de la aplicación.
     * Reutiliza la ventana (Stage) actual sustituyendo el contenedor visual por el nuevo FXML:
     * @param event Evento que desencadena el cambio (sirve para localizar el Stage raíz).
     * @param fxmlFile Ruta absoluta o relativa del archivo FXML de la nueva vista.
     */

    private void cambiarEscena(ActionEvent event, String fxmlFile) {
        try {
            // Carga de la estructura de la nueva interfaz gráfica
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(fxmlLoader.load());

            // Localización de la ventana (Stage) actual a través del nodo del componente que disparó el evento
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Transición de la escena en pantalla
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {

            // Manejo de error visual si el archivo FXML destino está corrupto o mal direccionado
            lblError.setText("Error al cargar la vista siguiente.");
            e.printStackTrace();
        }
    }
}