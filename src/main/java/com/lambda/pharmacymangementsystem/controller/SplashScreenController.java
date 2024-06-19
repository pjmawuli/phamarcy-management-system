package com.lambda.pharmacymangementsystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SplashScreenController {

    @FXML
    private Button startButton; // Define the startButton

    @FXML
    private void handleStartButton() {
        // Load the main dashboard
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("com/lambda/pharmacymangementsystem/view/dashboard-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) startButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
