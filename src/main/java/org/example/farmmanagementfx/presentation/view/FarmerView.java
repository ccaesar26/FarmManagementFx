package org.example.farmmanagementfx.presentation.view;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.example.farmmanagementfx.data.entity.Farmer;
import org.example.farmmanagementfx.presentation.model.FarmerModel;
import org.example.farmmanagementfx.presentation.util.ResourceUtil;
import org.example.farmmanagementfx.presentation.util.ViewUtil;

import java.util.List;

public class FarmerView extends BorderPane implements Observer {

    private final FarmerModel model;

    private final TableView<Farmer> tableView = new TableView<>();
    private final TableColumn<Farmer, String> nameColumn = new TableColumn<>("Name");
    private final TableColumn<Farmer, String> assignedCultureColumn = new TableColumn<>("Assigned Culture");
    private final TableColumn<Farmer, String> employmentDateColumn = new TableColumn<>("Employment Date");

    private final TextField nameTextField = new TextField();
    private final ComboBox<String> assignedCultureNameComboBox = new ComboBox<>();
    private final DatePicker employmentDatePicker = new DatePicker();

    private final Label formTitleLabel = new Label("Add farmer");
    private final Button deleteButton = new Button("Delete");
    private final Button saveButton = new Button("Save");
    private final Button discardButton = new Button("Discard");

    private ChangeListener<Farmer> onRowSelected;

    public FarmerView(FarmerModel model) {
        this.model = model;

        var leftPane = ViewUtil.createTitlePane(
                "Farmers",
                "Farmer.png"
        );

        var centerPane = createMainPane();

        setLeft(leftPane);
        setCenter(centerPane);

        initializeData();
    }

    private Region createMainPane() {
        var mainPane = new BorderPane();

        mainPane.setCenter(ViewUtil.createTablePane(
                "View farmers",
                deleteButton,
                tableView,
                nameColumn,
                assignedCultureColumn,
                employmentDateColumn
        ));

        mainPane.setBottom(ViewUtil.createFormPane(
                "farmer",
                formTitleLabel,
                saveButton,
                discardButton,
                List.of("name", "assigned culture", "employment date"),
                nameTextField,
                assignedCultureNameComboBox,
                employmentDatePicker
        ));

        mainPane.getStyleClass().add("main-container");
        mainPane.getStylesheets().add(ResourceUtil.getStyleSheetPath("style.css"));

        return mainPane;
    }

    private void initializeData() {
        tableView.setItems(model.getFarmers());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        assignedCultureColumn.setCellValueFactory(cellData -> cellData.getValue().assignedCultureProperty().get().nameProperty());
        employmentDateColumn.setCellValueFactory(cellData -> cellData.getValue().employmentDateAsString());

        assignedCultureNameComboBox.setItems(model.getCultureNames());

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (onRowSelected != null) {
                        onRowSelected.changed(observable, oldValue, newValue);
                        model.setSelectedFarmer(newValue);
                    }
                }
        );
    }

    public TableView<Farmer> getTableView() {
        return tableView;
    }

    public TextField getNameTextField() {
        return nameTextField;
    }

    public ComboBox<String> getAssignedCultureNameComboBox() {
        return assignedCultureNameComboBox;
    }

    public DatePicker getEmploymentDatePicker() {
        return employmentDatePicker;
    }

    public Label getFormTitleLabel() {
        return formTitleLabel;
    }

    public ChangeListener<Farmer> getOnRowSelected() {
        return onRowSelected;
    }

    public void setOnRowSelected(ChangeListener<Farmer> onRowSelected) {
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

    // Observer methods

    @Override
    public void updateTableView() {
        tableView.refresh();
    }
}
