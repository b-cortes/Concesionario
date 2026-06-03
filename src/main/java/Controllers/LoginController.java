package Controllers;

import com.example.concesionario.LoginAplication;
import dao.Impl.UsuarioDAOImpl;
import dao.UsuarioDAO;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Usuario;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField txtUser;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblError;
    private UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    @FXML
    protected void onLoginButtonClick() {
        String user = txtUser.getText();
        String pass = txtPassword.getText();

        Usuario loggedUser = usuarioDAO.login(user, pass);

        if (loggedUser != null) {
            // Usuario correcto: Cargar escena CRUD
            cambiarEscena();
        } else {
            lblError.setText("Este usuario o contraseña no es correcto");
        }
    }

    @FXML
    protected void onSalirButtonClick() {
        Platform.exit();
    }

    // Método de utilidad para cambiar de escena
    private void cambiarEscena() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LoginAplication.class.getResource("crud-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 500);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
