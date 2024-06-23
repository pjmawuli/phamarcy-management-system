package com.lambda.pharmacymangementsystem.controller;

import com.lambda.pharmacymangementsystem.model.entities.DrugViewEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ViewDrugsController {

    @FXML
    private TableView<DrugViewEntity> drugsTable;

    @FXML
    private TableColumn<DrugViewEntity, Integer> idColumn;

    @FXML
    private TableColumn<DrugViewEntity, String> nameColumn;

    @FXML
    private TableColumn<DrugViewEntity, String> drugCodeColumn;

    @FXML
    private TableColumn<DrugViewEntity, Integer> quantityColumn;

    @FXML
    private TableColumn<DrugViewEntity, Double> priceColumn;

    @FXML
    private TableColumn<DrugViewEntity, Integer> supplierIdColumn;

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private Button backButton;

    private PageLayoutController pageLayoutController;

    public ViewDrugsController() {
    }

    public ViewDrugsController(PageLayoutController pageLayoutController) {
        this.pageLayoutController = pageLayoutController;
    }

    @FXML
    public void initialize() {
        // Initialize TableView columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        drugCodeColumn.setCellValueFactory(new PropertyValueFactory<>("drugCode"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        supplierIdColumn.setCellValueFactory(new PropertyValueFactory<>("supplierId"));

        // Populate the TableView with dummy data (replace with actual data retrieval)
        ObservableList<DrugViewEntity> data = FXCollections.observableArrayList(getDummyData());
        drugsTable.setItems(data);
    }

    private List<DrugViewEntity> getDummyData() {
        // Replace this method with actual database retrieval logic
        List<DrugViewEntity> dummyData = new ArrayList<>();
        dummyData.add(new DrugViewEntity(1, "Drug A", "ABC123", 100, 10.5, LocalDateTime.now(), LocalDateTime.now(), 1, "Supplier A", "1234567890", "Location A", LocalDateTime.now(), LocalDateTime.now()));
        dummyData.add(new DrugViewEntity(2, "Drug B", "DEF456", 50, 20.0, LocalDateTime.now(), LocalDateTime.now(), 2, "Supplier B", "9876543210", "Location B", LocalDateTime.now(), LocalDateTime.now()));
        dummyData.add(new DrugViewEntity(3, "Drug C", "GHI789", 200, 5.0, LocalDateTime.now(), LocalDateTime.now(), 3, "Supplier C", "5556667777", "Location C", LocalDateTime.now(), LocalDateTime.now()));
        return dummyData;
    }

  /**
 * Handles the search functionality in the TableView.
 *
 * This method is triggered when the search button is clicked. It retrieves the text from the search field,
 * converts it to lower case, and uses it to filter the items in the TableView.
 *
 * The method first retrieves the current items in the TableView and creates a new FilteredList to hold the
 * filtered items. It then sets a predicate on the FilteredList, which determines whether an item should be
 * included in the filtered list based on whether the item's name or drug code contains the search text.
 *
 * Finally, the method updates the items in the TableView with the filtered list, effectively updating the
 * TableView to only display items that match the search criteria.
 */
@FXML
public void handleSearch() {
    String searchText = searchField.getText().toLowerCase();

    // Get the current items in the TableView
    ObservableList<DrugViewEntity> currentList = drugsTable.getItems();

    // Create a filtered list to hold filtered items
    FilteredList<DrugViewEntity> filteredList = new FilteredList<>(currentList);

    // Set predicate for filtering
    filteredList.setPredicate(drug -> {
        // Apply your search logic here based on drug attributes
        return drug.getName().toLowerCase().contains(searchText)
                || drug.getDrugCode().toLowerCase().contains(searchText);
    });

    // Update TableView with filtered list
    drugsTable.setItems(filteredList);
}
    @FXML
    public void handleBack() {
        pageLayoutController.loadPreviousPage();
    }
}
