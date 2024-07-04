package com.lambda.pharmacymangementsystem.controller;

import com.lambda.pharmacymangementsystem.model.entities.SupplierEntity;
import com.lambda.pharmacymangementsystem.model.functions.SupplierFunctions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AddSupplierController {

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
        } catch (SQLException e) {
            e.printStackTrace();
            // show error label
        }
    }
}
