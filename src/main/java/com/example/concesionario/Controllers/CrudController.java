package com.example.concesionario.Controllers;

import BaseDatos.Conexion;
import com.example.concesionario.LoginAplication;
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

public class CrudController {
    @FXML private TextField txtMarca;
    @FXML private DatePicker dpAnno;
    @FXML private TextField txtBastidor2;
    @FXML private TextField txtMarca2;
    @FXML private DatePicker dpAnno2;
    @FXML private TextField txtBastidor3;

    ConcesionarioDAO cocheDAO = new ConcesionarioDAOImp();

    @FXML
    protected void onAddButtonClick() {

        try {
            String marca = txtMarca.getText();

            LocalDate anno = dpAnno.getValue();

            Concesionario coche = new Concesionario(marca, anno);

            cocheDAO.addCoche(coche);

        } catch (NumberFormatException e) {

            System.err.println(e);

        }
    }

    @FXML
    protected void onDeleteButtonClick() {
        int id = Integer.parseInt(txtBastidor3.getText());
        cocheDAO.deleteCoche(id);
        limpiarCampos();
    }

    @FXML
    protected void onUpdateButtonClick() {
        int id = Integer.parseInt(txtBastidor2.getText());
        Concesionario modificado = new Concesionario(id, txtMarca2.getText(), dpAnno2.getValue());
        cocheDAO.updateCoche(modificado);
        limpiarCampos();
    }

    @FXML
    protected void onVerListaButtonClick(ActionEvent event) {
        cambiarEscena(event, "lista-view.fxml");
    }


    @FXML
    protected void onSalirButtonClick() { Platform.exit();
    }

    private void limpiarCampos() {
        txtMarca.clear();
        dpAnno.setValue(null);
    }

    private void cambiarEscena(ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LoginAplication.class.getResource(fxmlFile));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
