package com.lambda.pharmacymangementsystem.controller;

import com.lambda.pharmacymangementsystem.model.entities.DrugViewEntity;
import com.lambda.pharmacymangementsystem.model.functions.DrugFunctions;
import com.lambda.pharmacymangementsystem.utils.Drug;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.sql.SQLException;
import java.util.List;

public class AddPurchasesController {

    @FXML
    private ComboBox<String> drugComboBox;

    @FXML
    private TextField quantityField;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private TextField customerNameField;

    @FXML
    public void Initialize() throws SQLException {
        List<DrugViewEntity> drugs = DrugFunctions.getAllDrugs();
        for (DrugViewEntity drug: drugs ) {
            drugComboBox.getItems().add(drug.getName());
        }

    }



    @FXML
    public void updateTotalPrice(ActionEvent event) {

    }

    public void addPurchase(ActionEvent event) {

    }
}
