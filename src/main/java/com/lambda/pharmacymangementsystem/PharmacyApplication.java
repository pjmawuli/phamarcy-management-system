package com.lambda.pharmacymangementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PharmacyApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PharmacyApplication.class.getResource("view/fxml/splash-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        String css = String.valueOf(getClass().getResource("/com/lambda/pharmacymangementsystem/view/css/style.css"));
        scene.getStylesheets().add(css);
        stage.setTitle("Lambda Pharmacy Manager");
        stage.setScene(scene);
        stage.show();
    }
}