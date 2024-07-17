package com.lambda.pharmacymangementsystem.controller;

import com.lambda.pharmacymangementsystem.model.entities.SupplierEntity;
import com.lambda.pharmacymangementsystem.model.functions.SupplierFunctions;
import com.lambda.pharmacymangementsystem.utils.TableActionButtons;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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
                suppliers.setAll(suppliersList);
            }
        } catch (Exception e) {
            System.out.println("Error loading suppliers: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @FXML
    private void showAddSupplier() {
        System.out.println("Load supplier view");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lambda/pharmacymangementsystem/view/add-supplier-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Add Supplier");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));

            // wait for user input
            stage.showAndWait();

            //reload data
            loadSuppliers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleEdit(SupplierEntity supplier) {
        try {
            // load edit supplier page and load the controller
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lambda/pharmacymangementsystem/view/update-supplier.fxml"));
            Parent root = loader.load();

            UpdateSupplierController controller = loader.getController();

            // pass supplier data into the loadSupplier function
            controller.loadSupplier(supplier);

            // create a new stage and load into view
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setAlwaysOnTop(false);
            stage.setTitle("Update Supplier");

            // wait for user input
            stage.showAndWait();

            // refresh list
            loadSuppliers();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
