package com.lambda.pharmacymangementsystem.controller;

import com.lambda.pharmacymangementsystem.model.entities.DrugEntity;
import com.lambda.pharmacymangementsystem.model.entities.DrugViewEntity;
import com.lambda.pharmacymangementsystem.model.functions.DrugFunctions;
import com.lambda.pharmacymangementsystem.utils.DataExport;
import com.lambda.pharmacymangementsystem.utils.TableActionButtons;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class DrugController {

    private final ObservableList<DrugViewEntity> drugs = FXCollections.observableArrayList();
    @FXML
    public TableColumn<DrugViewEntity, String> nameColumn;
    @FXML
    public TableColumn<DrugViewEntity, String> drugCodeColumn;
    @FXML
    public TableColumn<DrugViewEntity, Integer> quantityColumn;
    @FXML
    public TableColumn<DrugViewEntity, Double> priceColumn;
    @FXML
    public TableColumn<DrugViewEntity, String> supplierNameColumn;
    @FXML
    public TableColumn<DrugViewEntity, String> supplierLocationColumn;
    @FXML
    public TableColumn<DrugViewEntity, String> supplierContactColumn;
    @FXML
    private TableView<DrugViewEntity> datatable;
    @FXML
    private TableColumn<DrugViewEntity, Void> actionColumn;
    @FXML
    private Button addButton;

    // Initialization method
    @FXML
    private void initialize() {
        ControllerManager.getInstance().setDrugController(this);
        DataExport exportUtils = new DataExport();
        datatable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // bind tableview to observable list to allow for change listening
        datatable.setItems(drugs);

        // initialize columns
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        drugCodeColumn.setCellValueFactory(new PropertyValueFactory<>("drugCode"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        supplierNameColumn.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        supplierContactColumn.setCellValueFactory(new PropertyValueFactory<>("supplierContact"));
        supplierLocationColumn.setCellValueFactory(new PropertyValueFactory<>("supplierLocation"));

        // Setup action column using the utility method
        TableColumn<DrugViewEntity, Void> actionColumn = TableActionButtons.createActionColumn(
                this::handleEdit,
                this::handleDelete
        );
        datatable.getColumns().add(actionColumn);

        // load data into table view
        loadDrugs();

        // bind the action to the add drug button
//        addButton.setOnAction(actionEvent -> handleAdd());

    }

    public void loadDrugs() {
        try {
            List<DrugViewEntity> drugsList = DrugFunctions.getAllDrugs();
            if (!drugsList.isEmpty()) {
                drugs.addAll(drugsList);
            }
        } catch (Exception e) {
            System.out.println("Error loading drugs: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void refreshDrugsTable() {
        drugs.clear(); // Clear the current list
        loadDrugs(); // Reload drugs from the database
    }

    @FXML
    private void handleAdd() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lambda/pharmacymangementsystem/view/add-drug-view.fxml"));            Parent root = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);// Prevent user from interacting with other windows
            stage.setScene(new Scene(root));
            stage.setAlwaysOnTop(true);
            stage.setTitle("Add Drug");
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            //
        }
        System.out.println(drugs.get(0).getDrugCode());
    }

    @FXML
    private void handleEdit(DrugViewEntity drugViewEntity) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lambda/pharmacymangementsystem/view/update-drug.fxml"));
            Parent root = loader.load();

            UpdateDrugController controller = loader.getController();

            // Convert DrugViewEntity to DrugEntity if necessary or directly pass if they are effectively the same
            DrugEntity selectedDrug = convertToDrugEntity(drugViewEntity);
            controller.setDrug(selectedDrug);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);// Prevent user from interacting with other windows
            stage.setScene(new Scene(root));
            stage.setAlwaysOnTop(true);
            stage.setTitle("Update Drug");
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception (e.g., show an error dialog)
        }
    }

private DrugEntity convertToDrugEntity(DrugViewEntity drugViewEntity) {
    // This method assumes DrugViewEntity and DrugEntity have similar fields.
    // Implement conversion logic based on your application's requirements.
    return new DrugEntity(drugViewEntity.getId(), drugViewEntity.getName(), drugViewEntity.getDrugCode(), drugViewEntity.getQuantity(), drugViewEntity.getPrice(), drugViewEntity.getSupplierId(), drugViewEntity.getCreatedAt(), drugViewEntity.getUpdatedAt());
}

    private void handleDelete(DrugViewEntity drug) {
        // Implement delete logic here
        System.out.println("Deleting drug: " + drug.getName());
    }


}
