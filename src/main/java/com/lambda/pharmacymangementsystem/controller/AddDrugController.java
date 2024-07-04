package com.lambda.pharmacymangementsystem.controller;

import com.lambda.pharmacymangementsystem.model.entities.DrugEntity;
import com.lambda.pharmacymangementsystem.model.entities.SupplierEntity;
import com.lambda.pharmacymangementsystem.model.functions.DrugFunctions;
import com.lambda.pharmacymangementsystem.model.functions.SupplierFunctions;
import com.lambda.pharmacymangementsystem.utils.Drug;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.util.HashMap;
import java.util.List;

public class AddDrugController {

    @FXML
    public TextField nameField;

    @FXML
    public TextField quantityField;

    @FXML
    public ComboBox<String> supplierComboBox;

    @FXML
    public Button addButton;

    private final HashMap<String, Integer> supplierMap = new HashMap<>();

    @FXML
    public void initialize() {
        loadSuppliers();
        addButton.setOnAction(e -> addDrug());
    }

    private void loadSuppliers() {
        try {
            List<SupplierEntity> suppliers = SupplierFunctions.getAllSuppliers();
            for (SupplierEntity supplier : suppliers) {
                supplierComboBox.getItems().add(supplier.getName());
                supplierMap.put(supplier.getName(), supplier.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Handle error
        }
    }

    public void addDrug() {
        try {
            String name = nameField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            int supplierId = supplierMap.get(supplierComboBox.getValue());
            String lastDrugCode = DrugFunctions.getRecentDrugCode();
            String drugCode = Drug.generateNextDrugCode(lastDrugCode);
            double price = 0; // This should be replaced with actual price retrieval logic

            DrugEntity newDrug = new DrugEntity(name, drugCode, quantity, price, supplierId);
            DrugFunctions.addOneDrug(newDrug);

            DrugController drugController = ControllerManager.getInstance().getDrugController();
            if (drugController != null) {
                drugController.refreshDrugsTable();
            }
            // Show success message
        } catch (Exception e) {
            e.printStackTrace();
            // Show error message
        }
    }
}