package com.lambda.pharmacymangementsystem.controller;

import com.lambda.pharmacymangementsystem.model.entities.SupplierEntity;
import com.lambda.pharmacymangementsystem.model.functions.SupplierFunctions;
import com.lambda.pharmacymangementsystem.utils.DatabaseErrorSanitization;
import com.lambda.pharmacymangementsystem.utils.SharedUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
        try {
            SupplierEntity supplier = new SupplierEntity(nameField.getText(), Integer.parseInt(contactField.getText()), locationField.getText());
            SupplierFunctions.addOneSupplier(supplier);
            System.out.println("Supplier added successfully.");
            closeDialog();
        } catch (SQLException e) {
            String message = DatabaseErrorSanitization.getErrorMessage(e);
            SharedUtils.showErrorAlert("Error", "Could not add drug", message);
        }
    }

    private void closeDialog() {
        Stage stage = (Stage) dialogRoot.getScene().getWindow();
        stage.close();
    }
}

