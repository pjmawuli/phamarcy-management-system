package com.lambda.pharmacymangementsystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class SplashScreenController {

    @FXML
    private Button startButton;

    @FXML
    public void initialize() {
        // Initialization logic here...
    }

    @FXML
    private void handleStartButtonAction(ActionEvent event) throws IOException {
        Parent pageLayoutParent = FXMLLoader.load(getClass().getResource("/com/lambda/pharmacymangementsystem/view/fxml/page-layout2.fxml"));
        Scene pageLayoutScene = new Scene(pageLayoutParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(pageLayoutScene);
        window.show();
    }
}