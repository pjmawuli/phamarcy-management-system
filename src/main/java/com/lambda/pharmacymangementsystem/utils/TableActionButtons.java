package com.lambda.pharmacymangementsystem.utils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class TableActions {

    public static Button createEditButton() {
        Button editButton = new Button("Edit");
        editButton.getStyleClass().add("edit-button");
        return editButton;
    }

    public static Button createDeleteButton() {
        Button deleteButton = new Button("Delete");
        deleteButton.getStyleClass().add("delete-button");
        return deleteButton;
    }

    public static HBox createActionBox(Button editButton, Button deleteButton) {
        HBox box = new HBox(5, editButton, deleteButton);
        box.setAlignment(Pos.CENTER_RIGHT);  // Align content to the right
        return box;
    }

    public static <T> void setupActionColumn(TableColumn<T, Void> actionColumn,
                                             EventHandler<ActionEvent> onEdit,
                                             EventHandler<ActionEvent> onDelete) {
        Callback<TableColumn<T, Void>, TableCell<T, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<T, Void> call(final TableColumn<T, Void> param) {
                return new TableCell<>() {
                    private final Button editButton = createEditButton();
                    private final Button deleteButton = createDeleteButton();
                    private final HBox box = createActionBox(editButton, deleteButton);

                    {
                        editButton.setOnAction(event -> onEdit.handle(event));
                        deleteButton.setOnAction(event -> onDelete.handle(event));
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(box);
                        }
                    }
                };
            }
        };

        actionColumn.setCellFactory(cellFactory);
    }
}
