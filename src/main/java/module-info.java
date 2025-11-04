module com.example.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyectofinal to javafx.fxml;
    exports com.example.proyectofinal;
    exports com.example.proyectofinal.Controllers;
    opens com.example.proyectofinal.Controllers to javafx.fxml;
    exports com.example.proyectofinal.App;
    opens com.example.proyectofinal.App to javafx.fxml;
}