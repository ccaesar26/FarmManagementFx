package org.example.farmmanagementfx.presentation.controller;

import org.example.farmmanagementfx.data.entity.LandLot;
import org.example.farmmanagementfx.data.util.DateUtil;
import org.example.farmmanagementfx.presentation.model.LandLotModel;
import org.example.farmmanagementfx.presentation.view.LandLotView;

public class LandLotController {

    private final LandLotView landLotView;
    private final LandLotModel landLotModel;

    public LandLotController(LandLotView landLotView, LandLotModel landLotModel) {
        this.landLotView = landLotView;
        this.landLotModel = landLotModel;

        landLotModel.attach(landLotView);

        initialize();
    }

    private void initialize() {
        landLotView.setOnRowSelected((observable, oldValue, newValue) -> {
            landLotModel.setSelectedLandLot(newValue);
            if (newValue != null) {
                landLotView.getLocationTextField().setText(newValue.getLocation());
                landLotView.getAreaSpinner().getValueFactory().setValue(newValue.getArea());
                landLotView.getPlantingDatePicker().setValue(DateUtil.convertToLocalDate(newValue.getPlantingDate()));
                landLotView.getHarvestDatePicker().setValue(DateUtil.convertToLocalDate(newValue.getHarvestDate()));
                landLotView.getPlantedCultureNameComboBox().getSelectionModel().select(newValue.getPlantedCulture().getName());
            }
        });

        landLotView.getSaveButton().setOnAction(actionEvent -> {
            if (landLotModel.getSelectedLandLot() == null) {
                var location = landLotView.getLocationTextField().getText();
                var area = landLotView.getAreaSpinner().getValue();
                var plantingDate = DateUtil.convertToDate(landLotView.getPlantingDatePicker().getValue());
                var harvestDate = DateUtil.convertToDate(landLotView.getHarvestDatePicker().getValue());
                var plantedCultureName = landLotView.getPlantedCultureNameComboBox().getSelectionModel().getSelectedItem();
                var plantedCulture = landLotModel.getCultures().stream()
                        .filter(culture -> culture.getName().equals(plantedCultureName))
                        .findFirst()
                        .orElse(null);

                landLotModel.addLandLot(new LandLot(
                        0,
                        location,
                        area,
                        plantingDate,
                        harvestDate,
                        plantedCulture,
                        true
                ));
            } else {
                var selectedLandLot = landLotModel.getSelectedLandLot();
                selectedLandLot.location().set(landLotView.getLocationTextField().getText());
                selectedLandLot.area().set(landLotView.getAreaSpinner().getValue());
                selectedLandLot.plantingDate().set(DateUtil.convertToDate(landLotView.getPlantingDatePicker().getValue()));
                selectedLandLot.harvestDate().set(DateUtil.convertToDate(landLotView.getHarvestDatePicker().getValue()));
                selectedLandLot.plantedCulture().set(landLotModel.getCultures().stream()
                        .filter(culture -> culture.getName().equals(landLotView.getPlantedCultureNameComboBox().getSelectionModel().getSelectedItem()))
                        .findFirst()
                        .orElse(null));
                landLotModel.updateLandLot(selectedLandLot);
            }

            deselectLandLot();
        });

        landLotView.getDeleteButton().setOnAction(actionEvent -> {
            var selectedLandLot = landLotModel.getSelectedLandLot();
            if (selectedLandLot == null) {
                return;
            }
            landLotModel.deleteLandLot(selectedLandLot);
            deselectLandLot();
        });

        landLotView.getDiscardButton().setOnAction(actionEvent -> deselectLandLot());
    }

    private void deselectLandLot() {
        landLotModel.setSelectedLandLot(null);
        landLotView.getTableView().getSelectionModel().clearSelection();
        landLotView.getLocationTextField().clear();
        landLotView.getAreaSpinner().getValueFactory().setValue(null);
        landLotView.getPlantingDatePicker().setValue(null);
        landLotView.getHarvestDatePicker().setValue(null);
        landLotView.getPlantedCultureNameComboBox().getSelectionModel().clearSelection();
    }
}
