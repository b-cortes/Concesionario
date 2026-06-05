package com.example.concesionario.Controllers;

import BaseDatos.Conexion;
import com.example.concesionario.LoginAplication;
import com.example.concesionario.Sesion;
import com.example.concesionario.dao.ConcesionarioDAO;
import com.example.concesionario.dao.Impl.ConcesionarioDAOImp;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.concesionario.model.Concesionario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


/**
 * Controlador de JavaFX para la vista de operaciones CRUD del concesionario.
 * Gestiona los eventos de la interfaz de usuario para añadir, actualizar,
 * eliminar y navegar por el inventario de coches.
 * @author Brian
 * @version 1.0
 */
public class CrudController {
    //============================================
    // Componentes FXML Inyectados desde la vista
    //============================================

    // Componentes para la sección "Añadir Coche"
    @FXML private TextField txtMarca;
    @FXML private DatePicker dpAnno;

    // Componentes para la sección "Actualizar Coche"
    @FXML private TextField txtBastidor2;
    @FXML private TextField txtMarca2;
    @FXML private DatePicker dpAnno2;

    // Componente para la sección "Eliminar Coche"
    @FXML private TextField txtBastidor3;

    // Instancia de la capa DAO para gestionar la persistencia de datos
    ConcesionarioDAO cocheDAO = new ConcesionarioDAOImp();

    /**
     * Gestiona el evento de clic en el botón para añadir un nuevo coche.
     * Recupera la información de los controles del formulario y la envía al DAO
     */

    @FXML
    protected void onAddButtonClick() {

        try {
            String marca = txtMarca.getText();

            LocalDate anno = dpAnno.getValue();

            // Instancia un nuevo coche con los datos capturados
            Concesionario coche = new Concesionario(marca, anno);
            coche.setUserid(Sesion.getUserId());

            // Persiste el coche en la base de datos
            cocheDAO.addCoche(coche);

        } catch (NumberFormatException e) {
            //Nota: Captura errores de conversión numérica si se añaden campos numéricos en el futuro
            System.err.println(e);

        }
    }

    /**
     * Gestiona el evento de clic en el botón para eliminar un coche.
     * Recupera el número de bastidor ingresado, ejecuta el borrado y limpia el formulario.
     */

    @FXML
    protected void onDeleteButtonClick() {
        // Convierte el texto del campo de bastidor a un tipo entero para el DAO
        int id = Integer.parseInt(txtBastidor3.getText());
        cocheDAO.deleteCoche(id);
        limpiarCampos();
    }

    /**
     * Gestiona el evento de clic en el botón para actualizar un coche existente.
     * Construye un objeto con los datos modificados y solicita la actualización al DAO.
     */

    @FXML
    protected void onUpdateButtonClick() {
        int id = Integer.parseInt(txtBastidor2.getText());

        //Mapea los nuevos campos al objeto de modelo incluyendo su Número de bastidor original
        Concesionario modificado = new Concesionario(id, txtMarca2.getText(), dpAnno2.getValue());
        cocheDAO.updateCoche(modificado);
        limpiarCampos();
    }

    /**
     * Redirecciona al usuario hacia la pantalla donse se muestra el listado de coches.
     * @param event Evento de acción disparado por el botón "Ver Lista".
     */

    @FXML
    protected void onVerListaButtonClick(ActionEvent event) {
        cambiarEscena(event, "lista-view.fxml");
    }

    /**
     * Cierra de manera segura toda la aplicación de JavaFX y finaliza los hilos activos.
     */

    @FXML
    protected void onSalirButtonClick() { Platform.exit();
    }

    /**
     * Restablece los campos de texto y selectores de fecha de la sección de inserción.
     */

    private void limpiarCampos() {
        txtMarca.clear();
        dpAnno.setValue(null);
    }

    /**
     * Método auxiliar genérico para realizar la navegación entre distintas escenas (.fxml).
     * @param event El evento que originó el cambio de pantalla (necesario para recuperar el Stage actual)
     * @param fxmlFile Nombre del archivo de la vista FXML a cargar.
     */

    private void cambiarEscena(ActionEvent event, String fxmlFile) {
        try {
            // Carga la nueva vista FXML utilizando el cargador de la aplicación principal
            FXMLLoader fxmlLoader = new FXMLLoader(LoginAplication.class.getResource(fxmlFile));
            Scene scene = new Scene(fxmlLoader.load());

            // Obtiene el Stage (Ventana) actual a través del nodo que disparó el evento
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Asigna la nueva escena a la ventana y la despliega
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
