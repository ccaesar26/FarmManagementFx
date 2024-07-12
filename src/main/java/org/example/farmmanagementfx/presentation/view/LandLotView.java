package org.example.farmmanagementfx.presentation.view;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import org.example.farmmanagementfx.data.entity.LandLot;
import org.example.farmmanagementfx.presentation.model.LandLotModel;
import org.example.farmmanagementfx.presentation.util.ResourceUtil;
import org.example.farmmanagementfx.presentation.util.ViewUtil;

import java.util.List;

public class LandLotView extends BorderPane implements Observer {

    private final LandLotModel model;

    private final TableView<LandLot> tableView = new TableView<>();
    private final TableColumn<LandLot, String> locationColumn = new TableColumn<>("Location");
    private final TableColumn<LandLot, Double> areaColumn = new TableColumn<>("Area");
    private final TableColumn<LandLot, String> plantingDateColumn = new TableColumn<>("Planting Date");
    private final TableColumn<LandLot, String> harvestDateColumn = new TableColumn<>("Harvest Date");
    private final TableColumn<LandLot, String> plantedCultureColumn = new TableColumn<>("Assigned Culture");

    private final TextField locationTextField = new TextField();
    private final Spinner<Double> areaSpinner = new Spinner<>();
    private final DatePicker plantingDatePicker = new DatePicker();
    private final DatePicker harvestDatePicker = new DatePicker();
    private final ComboBox<String> plantedCultureNameComboBox = new ComboBox<>();

    private final Label formTitleLabel = new Label("Add land lot");
    private final Button deleteButton = new Button("Delete");
    private final Button saveButton = new Button("Save");
    private final Button discardButton = new Button("Discard");

    private ChangeListener<LandLot> onRowSelected;

    public LandLotView(LandLotModel model) {
        this.model = model;

        var leftPane = ViewUtil.createTitlePane(
                "Land Lots",
                "LandLot.png"
        );
        var centerPane = createMainPane();

        setLeft(leftPane);
        setCenter(centerPane);

        initializeData();
    }

    private Region createMainPane() {
        var mainPane = new BorderPane();

        mainPane.setCenter(ViewUtil.createTablePane(
                "View land lots",
                deleteButton,
                tableView,
                locationColumn,
                areaColumn,
                plantingDateColumn,
                harvestDateColumn,
                plantedCultureColumn
        ));

        mainPane.setBottom(ViewUtil.createFormPane(
                "land lot",
                formTitleLabel,
                saveButton,
                discardButton,
                List.of("location", "area", "planting date", "harvest date", "planted culture"),
                locationTextField,
                areaSpinner,
                plantingDatePicker,
                harvestDatePicker,
                plantedCultureNameComboBox
        ));

        mainPane.getStyleClass().add("main-container");
        mainPane.getStylesheets().add(ResourceUtil.getStyleSheetPath("style.css"));

        return mainPane;
    }

    private void initializeData() {
        tableView.setItems(model.getLandLots());
        locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        areaColumn.setCellValueFactory(cellData -> cellData.getValue().areaProperty().asObject());
        plantingDateColumn.setCellValueFactory(cellData -> cellData.getValue().plantingDatePropertyAsString());
        harvestDateColumn.setCellValueFactory(cellData -> cellData.getValue().harvestDatePropertyAsString());
        plantedCultureColumn.setCellValueFactory(cellData -> cellData.getValue().plantedCultureProperty().get().nameProperty());

        areaSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, Double.MAX_VALUE, 0.0, 10.0));
        areaSpinner.getEditor().clear();
        plantedCultureNameComboBox.setItems(model.getCultureNames());

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (onRowSelected != null) {
                onRowSelected.changed(observable, oldValue, newValue);
                model.setSelectedLandLot(newValue);
            }
        });
    }

    public TableView<LandLot> getTableView() {
        return tableView;
    }

    public TextField getLocationTextField() {
        return locationTextField;
    }

    public Spinner<Double> getAreaSpinner() {
        return areaSpinner;
    }

    public DatePicker getPlantingDatePicker() {
        return plantingDatePicker;
    }

    public DatePicker getHarvestDatePicker() {
        return harvestDatePicker;
    }

    public ComboBox<String> getPlantedCultureNameComboBox() {
        return plantedCultureNameComboBox;
    }

    public Label getFormTitleLabel() {
        return formTitleLabel;
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

    public void setOnRowSelected(ChangeListener<LandLot> onRowSelected) {
        this.onRowSelected = onRowSelected;
    }

    // Observer pattern method

    @Override
    public void updateTableView() {
        tableView.refresh();
    }
}
