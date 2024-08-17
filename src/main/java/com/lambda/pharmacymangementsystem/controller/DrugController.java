package com.lambda.pharmacymangementsystem.controller;

import com.lambda.pharmacymangementsystem.model.entities.DrugEntity;
import com.lambda.pharmacymangementsystem.model.entities.DrugViewEntity;
import com.lambda.pharmacymangementsystem.model.functions.DrugFunctions;
import com.lambda.pharmacymangementsystem.utils.DataExport;
import com.lambda.pharmacymangementsystem.utils.TableActionButtons;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DrugController {

    private final ObservableList<DrugViewEntity> drugs = FXCollections.observableArrayList();
    private FilteredList<DrugViewEntity> filteredDrugs;
    private SortedList<DrugViewEntity> sortedDrugs;

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
    @FXML
    private TextField searchField;

    private List<DrugViewEntity> allDrugs = new ArrayList<>();

    @FXML
    private void initialize() {
        ControllerManager.getInstance().setDrugController(this);
        DataExport exportUtils = new DataExport();
        datatable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Bind tableview to observable list to allow for change listening
        datatable.setItems(drugs);

        // Initialize columns
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        drugCodeColumn.setCellValueFactory(new PropertyValueFactory<>("drugCode"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        supplierNameColumn.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        supplierContactColumn.setCellValueFactory(new PropertyValueFactory<>("supplierContact"));
        supplierLocationColumn.setCellValueFactory(new PropertyValueFactory<>("supplierLocation"));

        // Setup action column using the utility method
        actionColumn = TableActionButtons.createActionColumn(
                this::handleEdit,
                this::handleDelete
        );

        datatable.getColumns().add(actionColumn);

        // Initialize FilteredList
        filteredDrugs = new FilteredList<>(drugs, p -> true);
        // Initialize SortedList
        sortedDrugs = new SortedList<>(filteredDrugs);
        sortedDrugs.comparatorProperty().bind(datatable.comparatorProperty());

        // Add listener to the search field
        searchField.textProperty().addListener((observable, oldValue, newValue) -> searchDrugs(newValue));

        // Load data into table view
        loadDrugs();

        // Set default sort
        nameColumn.setSortType(TableColumn.SortType.ASCENDING);
        datatable.getSortOrder().add(nameColumn);
    }

    public void loadDrugs() {
        try {
            List<DrugViewEntity> drugsList = DrugFunctions.getAllDrugs();
            allDrugs.clear();
            allDrugs.addAll(drugsList);
            if (!allDrugs.isEmpty()) {
                allDrugs.sort(Comparator.comparing(drug -> drug.getName().toLowerCase()));
            }
            refreshDrugsTable();
        } catch (Exception e) {
            System.out.println("Error loading drugs: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void refreshDrugsTable() {
        drugs.clear();
        drugs.addAll(allDrugs);
        datatable.setItems(drugs);
    }

    private void searchDrugs(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            refreshDrugsTable();
            return;
        }

        searchTerm = searchTerm.toLowerCase();

        // Use linear search to find matching drugs
        List<DrugViewEntity> matchedDrugs = new ArrayList<>();
        for (DrugViewEntity drug : allDrugs) {
            if (drug.getName() != null && drug.getName().toLowerCase().startsWith(searchTerm)) {
                matchedDrugs.add(drug);
            }
        }

        // Update the observable list
        drugs.setAll(matchedDrugs);
    }


    @FXML
    private void handleAdd() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lambda/pharmacymangementsystem/view/add-drug-view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setAlwaysOnTop(true);
            stage.setTitle("Add Drug");
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
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
            stage.initModality(Modality.APPLICATION_MODAL); // Prevent user from interacting with other windows
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
