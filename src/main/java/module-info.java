module com.example.concesionario {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.concesionario to javafx.fxml;
    exports com.example.concesionario;
}