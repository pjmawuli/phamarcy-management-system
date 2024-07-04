package com.lambda.pharmacymangementsystem.controller;

import com.lambda.pharmacymangementsystem.model.entities.SupplierEntity;
import com.lambda.pharmacymangementsystem.model.functions.SupplierFunctions;
import com.lambda.pharmacymangementsystem.utils.TableActionButtons;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class SupplierController {

    @FXML
    private TableView<SupplierEntity> datatable;

    @FXML
    private TableColumn<SupplierEntity, String> nameColumn;

    @FXML
    private TableColumn<SupplierEntity, String> contactColumn;

    @FXML
    private TableColumn<SupplierEntity, String> locationColumn;

    @FXML
    private TableColumn<SupplierEntity, LocalDateTime> createdAtColumn;

    @FXML
    private TableColumn<SupplierEntity, Void> actionColumn;


    private ObservableList<SupplierEntity> suppliers = FXCollections.observableArrayList();

    // Initialization method
    @FXML
    private void initialize() {
        datatable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // bind tableview to observable list to allow for change listening
        datatable.setItems(suppliers);

        // Initialize columns
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        createdAtColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        // Setup action column using the utility method
        TableColumn<SupplierEntity, Void> actionColumn = TableActionButtons.createActionColumn(
                this::handleEdit,
                this::handleDelete
        );
        datatable.getColumns().add(actionColumn);

        // load data into table view
        loadSuppliers();
    }

    public void loadSuppliers() {
        try {
            List<SupplierEntity> suppliersList = SupplierFunctions.getAllSuppliers();
            if (!suppliersList.isEmpty()) {
                suppliers.addAll(suppliersList);
            }
        } catch (Exception e) {
            System.out.println("Error loading suppliers: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private void handleEdit(SupplierEntity supplier) {
        // Implement edit logic here
        System.out.println("Editing supplier: " + supplier.getId());

        // load edit supplier page and load the controller

        // pass supplier data into the loadSupplier function

        // create a new stage and load into view

        // refresh list
        loadSuppliers();
    }

    private void handleDelete(SupplierEntity supplier) {
        // Implement delete logic here
        try {
            SupplierFunctions.deleteOneSupplier(supplier.getId());

            //refresh list
            suppliers.remove(supplier);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
