module com.example.concesionario {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;

    exports com.example.concesionario;
    exports com.example.concesionario.Controllers;
    exports com.example.concesionario.model;
    opens com.example.concesionario.Controllers to javafx.fxml;
    opens com.example.concesionario.model to javafx.base;
}