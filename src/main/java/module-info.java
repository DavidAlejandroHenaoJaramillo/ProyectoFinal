module com.example.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.example.proyectofinal;


    exports com.example.proyectofinal.App;
    opens com.example.proyectofinal.App to javafx.fxml;

    exports com.example.proyectofinal.Controllers;
    opens com.example.proyectofinal.Controllers to javafx.fxml;

    exports com.example.proyectofinal.Models;
    opens com.example.proyectofinal.Models to javafx.fxml;
}