package com.example.concesionario.Controllers;

import com.example.concesionario.LoginAplication;
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

public class ListaController implements Initializable {
    @FXML
    private TableView<Concesionario> tablaCoches;
    @FXML private TableColumn<Concesionario, Integer> colBastidor;
    @FXML private TableColumn<Concesionario, String> colMarca;
    @FXML private TableColumn<Concesionario, LocalDate> colAnno;

    private ConcesionarioDAO cocheDAO = new ConcesionarioDAOImp();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configurar las columnas para que lean las propiedades de la clase Coche
        colBastidor.setCellValueFactory(new PropertyValueFactory<>("numBastidor"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colAnno.setCellValueFactory(new PropertyValueFactory<>("anno"));

        cargarDatos();
    }

    private void cargarDatos() {
        List<Concesionario> lista = cocheDAO.getAllCoches();
        ObservableList<Concesionario> obsList = FXCollections.observableArrayList(lista);
        tablaCoches.setItems(obsList);
    }

    @FXML
    protected void onVolverButtonClick(ActionEvent event) {
        cambiarEscena(event, "crud-view.fxml");
    }

    // Copiar el método cambiarEscena aquí también

    private void cambiarEscena(ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LoginAplication.class.getResource(fxmlFile));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println(e);;
        }
    }
}
