module com.lambda.pharmacymangementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.lambda.pharmacymangementsystem to javafx.fxml;
    exports com.lambda.pharmacymangementsystem;
    exports com.lambda.pharmacymangementsystem.controller;
    opens com.lambda.pharmacymangementsystem.controller to javafx.fxml;
}