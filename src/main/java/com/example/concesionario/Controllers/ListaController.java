package com.example.concesionario.Controllers;

import com.example.concesionario.LoginAplication;
import com.example.concesionario.Sesion;
import com.example.concesionario.dao.ConcesionarioDAO;
import com.example.concesionario.dao.Impl.ConcesionarioDAOImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import com.example.concesionario.model.Concesionario;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controlador de JavaFx para la vista del listado de vehículos.
 * Implementa {@link Initializable} para configurar la estructura de la tabla
 * y cargar los datos de manera automática al mostrar la pantalla.
 * @author Brian
 * @version 1.0
 */
public class ListaController implements Initializable {

    //==============================================
    // Componentes de la Tabla Inyectados desde FXML
    //==============================================
    @FXML private TableView<Concesionario> tablaCoches;
    @FXML private TableColumn<Concesionario, Integer> colBastidor;
    @FXML private TableColumn<Concesionario, String> colMarca;
    @FXML private TableColumn<Concesionario, LocalDate> colAnno;

    // Instancia de la capa de acceso a datos
    private ConcesionarioDAO cocheDAO = new ConcesionarioDAOImp();

    /**
     * Método del ciclo de vida de JavaFX. Se ejecuta automáticamente al cargar el FXML.
     * Vincula las columnas de la tabla con las propiedades lógicas del modelo
     * e inicia la carga del inventario.
     * @param url Ubicación utilizada para resolver rutas relativas del objeto raíz.
     * @param resourceBundle Los recursos utilizados para localizar el objeto raíz.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /** PropertyValueFactory mapea las columnas con los atributos de la clase Concesionario.
         * IMPORTANTE: Requiere estrictamente que la clase modelo tenga sus respectivos métodos
         * getter estándar (getNumBastidor(), getMarca(), getAnno()) para funcionar.
         */
        colBastidor.setCellValueFactory(new PropertyValueFactory<>("numBastidor"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colAnno.setCellValueFactory(new PropertyValueFactory<>("anno"));

        // Carga los registros de la base de datos en la interfaz gráfica
        cargarDatos();
    }

    /**
     * Consulta el DAO para obtener todos los coches y los envuelve en una
     * colección observable compatible con los componentes dinámicos de JavaFX.
     */

    private void cargarDatos() {

        // Recupera los coches del usuario logueado
        List<Concesionario> lista = cocheDAO.getCochesByUserId(Sesion.getUserId());

        // Convierte la List en un ObservableList para que la tabla pueda escuchar los cambios
        ObservableList<Concesionario> obsList = FXCollections.observableArrayList(lista);

        // Asigna los elementos a la vista del TableView
        tablaCoches.setItems(obsList);
    }

    /**
     * Gestiona el evento del botón para regresar a la pantalla de operaciones CRUD.
     * @param event Evento de acción disparado por el botón "Volver".
     */

    @FXML
    protected void onVolverButtonClick(ActionEvent event) {
        cambiarEscena(event, "crud-view.fxml");
    }

    /**
     * Método auxiliar utilizado para gestionar la navegación y el intercambio de escenas.
     * @param event El evento que desencadenó la acción.
     * @param fxmlFile Nombre del archivo .fxml de destino.
     */

    private void cambiarEscena(ActionEvent event, String fxmlFile) {
        try {

            // Carga la nueva interfaz desde el archivo de recursos
            FXMLLoader fxmlLoader = new FXMLLoader(LoginAplication.class.getResource(fxmlFile));
            Scene scene = new Scene(fxmlLoader.load());

            // Obtiene el Stage (Ventana de la aplicación) actual
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Remplaza la escena en pantalla
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println(e);;
        }
    }
}
