module mociaka.calculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.scripting;


    opens calculator to javafx.fxml;
    exports calculator;


    exports calculator.controllers;
    opens calculator.controllers to javafx.fxml;
}