package com.lambda.pharmacymangementsystem.controller;

import com.lambda.pharmacymangementsystem.model.entities.SupplierEntity;
import com.lambda.pharmacymangementsystem.model.functions.SupplierFunctions;
import com.lambda.pharmacymangementsystem.utils.DatabaseErrorSanitization;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AddSupplierController {

    @FXML
    public AnchorPane dialogRoot;

    @FXML
    public TextField nameField;
    @FXML
    public TextField contactField;
    @FXML
    public TextField locationField;

    @FXML
    Button addButton;

    @FXML
    public void initialize() {
        addButton.setOnAction(actionEvent -> handleSave(actionEvent));
    }


    public void handleSave(ActionEvent actionEvent) {
        if (!validateInput()) {
            showAlert("Validation Error", "Please fill in all fields correctly.", Alert.AlertType.ERROR);
            return;
        }
        try {
            SupplierEntity supplier = new SupplierEntity(nameField.getText(), Integer.parseInt(contactField.getText()), locationField.getText());
            SupplierFunctions.addOneSupplier(supplier);
            showAlert("Success", "Supplier added successfully.", Alert.AlertType.INFORMATION);
            closeDialog();
        } catch (SQLException e) {
            String message = DatabaseErrorSanitization.getErrorMessage(e);
            showAlert("Could not add supplier", message, Alert.AlertType.ERROR);
        }
    }

    private boolean validateInput() {
        return !nameField.getText().isEmpty() && !contactField.getText().isEmpty() && !locationField.getText().isEmpty();
    }

    private void closeDialog() {
        Stage stage = (Stage) dialogRoot.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

