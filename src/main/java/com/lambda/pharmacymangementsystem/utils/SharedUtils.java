package com.lambda.pharmacymangementsystem.utils;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class SharedUtils {
    public static void showErrorAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        // Customize the alert
        alert.initStyle(StageStyle.UTILITY);

        alert.showAndWait();

    }
}
