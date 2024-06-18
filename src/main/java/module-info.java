module com.lambda.pharmacymangementsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.lambda.pharmacymangementsystem to javafx.fxml;
    exports com.lambda.pharmacymangementsystem;
}