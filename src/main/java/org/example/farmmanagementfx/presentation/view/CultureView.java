package org.example.farmmanagementfx.presentation.view;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.example.farmmanagementfx.data.entity.Culture;
import org.example.farmmanagementfx.presentation.model.CultureModel;
import org.example.farmmanagementfx.presentation.util.ResourceUtil;
import org.example.farmmanagementfx.presentation.util.ViewUtil;

import java.util.List;

public class CultureView extends BorderPane {

    private final CultureModel model;

    private final TableView<Culture> tableView = new TableView<>();
    private final TableColumn<Culture, String> nameColumn = new TableColumn<>("Name");
    private final TextField nameTextField = new TextField();
    private final Label formTitleLabel = new Label("Add plantedCulture");
    private final Button deleteButton = new Button("Delete");
    private final Button saveButton = new Button("Save");
    private final Button discardButton = new Button("Discard");

    private ChangeListener<Culture> onRowSelected;

    public CultureView(CultureModel model) {
        this.model = model;

        var leftPane = ViewUtil.createTitlePane("Cultures", "Culture.png");
        var centerPane = createMainPane();

        setLeft(leftPane);
        setCenter(centerPane);

        initializeData();
    }

    private Region createMainPane() {
        var mainPane = new BorderPane();

        mainPane.setCenter(ViewUtil.createTablePane(
                "View cultures",
                deleteButton,
                tableView,
                nameColumn
        ));

        mainPane.setBottom(ViewUtil.createFormPane(
                "plantedCulture",
                formTitleLabel,
                saveButton,
                discardButton,
                List.of("name"),
                nameTextField
        ));

        mainPane.getStyleClass().add("main-container");
        mainPane.getStylesheets().add(ResourceUtil.getStyleSheetPath("style.css"));

        return mainPane;
    }

    private void initializeData() {
        tableView.setItems(model.getCultures());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (onRowSelected != null) {
                        onRowSelected.changed(observable, oldValue, newValue);
                        model.setSelectedCulture(newValue);
                    }
                }
        );
    }

    public TableView<Culture> getTableView() {
        return tableView;
    }

    public TextField getNameTextField() {
        return nameTextField;
    }

    public Label getFormTitleLabel() {
        return formTitleLabel;
    }

    public ChangeListener<Culture> getOnRowSelected() {
        return onRowSelected;
    }

    public void setOnRowSelected(ChangeListener<Culture> onRowSelected) {
        this.onRowSelected = onRowSelected;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getDiscardButton() {
        return discardButton;
    }
}
