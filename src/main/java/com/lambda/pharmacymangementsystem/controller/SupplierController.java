package com.lambda.pharmacymangementsystem.controller;

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import com.lambda.pharmacymangementsystem.model.entities.SupplierEntity;
import com.lambda.pharmacymangementsystem.model.functions.SupplierFunctions;
import com.lambda.pharmacymangementsystem.utils.TableActionButtons;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    }

    private void handleDelete(SupplierEntity supplier) {
        // Implement delete logic here
        System.out.println("Deleting supplier: " + supplier.getName());
    }


    public void showAddSupplier(ActionEvent actionEvent) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lambda/pharmacymangementsystem/view/forms/add-supplier-view.fxml"));
//            Scene scene = new Scene(loader.load());
//
//            Stage stage = new Stage();
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.setScene(scene);
//
//            stage.showAndWait();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        SupplierEntity drug = new SupplierEntity();
//
        Form form = Form.of(
                Group.of(
                        Field.ofStringType("").label("Name").placeholder("Enter supplier name").required(true),
                        Field.ofStringType("").label("Contact").placeholder("Enter supplier contact"),
                        Field.ofStringType("").label("Location").placeholder("Enter supplier location")
                )
        ).title("Add New Supplier");

        FormRenderer formRenderer = new FormRenderer(form);

        VBox formContainer = new VBox(formRenderer);
        Scene scene = new Scene(formContainer);

        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initStyle(StageStyle.UTILITY);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();

        if (form.isValid()) {
            System.out.println("Success");
//            saveDrug(drug);
        }
    }
}
