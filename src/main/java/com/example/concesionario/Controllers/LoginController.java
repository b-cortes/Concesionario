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

public class LoginController implements Initializable {

    @FXML private TextField txtUser;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblError;

    // Ya no lo instanciamos aquí directamente para evitar el NullPointerException
    private UsuarioDAO usuarioDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializamos la conexión y el DAO de forma segura al cargar la vista
        Connection conn = Conexion.getInstance();
        this.usuarioDAO = new UsuarioDAOImpl(conn);
    }

    @FXML
    protected void onLoginButtonClick(ActionEvent event) { // Añadimos el ActionEvent para poder cambiar de ventana
        String user = txtUser.getText();
        String pass = txtPassword.getText();

        // Evitamos comprobar en la BD si los campos están vacíos
        if (user.isEmpty() || pass.isEmpty()) {
            lblError.setText("Por favor, rellena todos los campos.");
            return;
        }

        Usuario loggedUser = usuarioDAO.login(user, pass);

        if (loggedUser != null) {
            // Usuario correcto: Cambiamos a la escena CRUD pasando el evento actual
            cambiarEscena(event, "/com/example/concesionario/crud-view.fxml");
        } else {
            lblError.setText("Este usuario o contraseña no es correcto.");
        }
    }

    @FXML
    protected void onSalirButtonClick() {
        Platform.exit();
    }

    // Método de utilidad universal para cambiar de escena reutilizando la misma ventana
    private void cambiarEscena(ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(fxmlLoader.load());

            // Esto consigue la ventana actual del botón que se ha pulsado y cambia su contenido
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            lblError.setText("Error al cargar la vista siguiente.");
            e.printStackTrace();
        }
    }
}